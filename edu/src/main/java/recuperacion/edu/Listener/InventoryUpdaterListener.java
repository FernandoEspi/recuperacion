package recuperacion.edu.Listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import recuperacion.edu.Event.OrderCreaterEvent;

@Component
public class InventoryUpdaterListener {
    private static final Logger logger = LoggerFactory.getLogger(InventoryUpdaterListener.class);

    @EventListener
    public void handleOrderCreated(OrderCreaterEvent event) {
        // Simulación de reducción de stock
        logger.info("Reduciendo stock para el pedido ID: {}", event.getOrderId());
    }
}
