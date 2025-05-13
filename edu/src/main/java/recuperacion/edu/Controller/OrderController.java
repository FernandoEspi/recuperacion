package recuperacion.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import recuperacion.edu.Dto.OrderRequest;
import recuperacion.edu.Entity.Order;
import recuperacion.edu.Event.OrderCreaterEvent;
import recuperacion.edu.Repository.OrderRepository;
import recuperacion.edu.Service.ProductoService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductoService productoService; // Inyección del servicio de productos

    public OrderController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        // Crear una nueva instancia de Order
        Order order = new Order();
        order.setOrderId(orderRequest.getOrderId());
        order.setEmail(orderRequest.getEmail());
        order.setProducts(orderRequest.getProducts());

        // Reducir stock del producto
        boolean stockReduced = productoService.reducirStock(orderRequest.getProducts(), 1); // Suponiendo que se reduce 1 unidad por pedido

        if (!stockReduced) {
            return ResponseEntity.badRequest().body("Stock insuficiente para el producto: " + orderRequest.getProducts());
        }

        // Guardar el pedido en la base de datos
        orderRepository.save(order);

        // Crear y publicar el evento con el nombre del producto y la cantidad
        OrderCreaterEvent event = new OrderCreaterEvent(
                orderRequest.getOrderId(),
                orderRequest.getEmail(),
                orderRequest.getProducts(),
                orderRequest.getProducts(), // Suponiendo que el nombre del producto es el mismo que el ID del producto
                1 // Cantidad reducida
        );
        eventPublisher.publishEvent(event);

        return ResponseEntity.ok("Pedido creado y evento publicado");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
