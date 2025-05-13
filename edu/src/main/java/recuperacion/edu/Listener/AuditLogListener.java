package recuperacion.edu.Listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import recuperacion.edu.Event.OrderCreaterEvent;

@Component
public class AuditLogListener {
    private static final Logger logger = LoggerFactory.getLogger(AuditLogListener.class);

    @EventListener
    public void handleOrderCreated(OrderCreaterEvent event) {
        // Registro del pedido en los logs
        logger.info("Registro de pedido: ID: {}, Email: {}, Productos: {}, Producto: {}, Cantidad: {}",
                event.getOrderId(), event.getEmail(), event.getProducts(), event.getProductoNombre(), event.getCantidad());
    }
}

