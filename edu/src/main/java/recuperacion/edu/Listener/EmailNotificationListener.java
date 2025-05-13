package recuperacion.edu.Listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import recuperacion.edu.Event.OrderCreaterEvent;

@Component
public class EmailNotificationListener {
    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationListener.class);

    @EventListener
    public void handleOrderCreated(OrderCreaterEvent event) {
        // Simulación de envío de correo
        logger.info("Enviando correo de confirmación a: {}", event.getEmail());
    }
}
