package recuperacion.edu.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import recuperacion.edu.Dto.OrderRequest;
import recuperacion.edu.Entity.Order;
import recuperacion.edu.Event.OrderCreaterEvent;
import recuperacion.edu.Repository.OrderRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    private OrderRepository orderRepository; // Inyección del repositorio

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

        // Guardar el pedido en la base de datos
        orderRepository.save(order);

        // Crear y publicar el evento
        OrderCreaterEvent event = new OrderCreaterEvent(orderRequest.getOrderId(), orderRequest.getEmail(), orderRequest.getProducts());
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
