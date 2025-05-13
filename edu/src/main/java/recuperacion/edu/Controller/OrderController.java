package recuperacion.edu.Controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recuperacion.edu.Dto.OrderRequest;
import recuperacion.edu.Event.OrderCreaterEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ApplicationEventPublisher eventPublisher;
    private final List<OrderRequest> orders = new ArrayList<>(); // Almacenamiento en memoria

    public OrderController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        // Crear el evento
        OrderCreaterEvent event = new OrderCreaterEvent(orderRequest.getOrderId(), orderRequest.getEmail(), orderRequest.getProducts());

        eventPublisher.publishEvent(event);

        orders.add(orderRequest);

        return ResponseEntity.ok("Pedido creado y evento publicado");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderRequest> getOrder(@PathVariable String orderId) {
        // Buscar el pedido en la lista
        Optional<OrderRequest> order = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst();

        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
