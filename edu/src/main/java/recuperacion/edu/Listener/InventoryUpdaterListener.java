package recuperacion.edu.Listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import recuperacion.edu.Event.OrderCreaterEvent;
import recuperacion.edu.Service.ProductoService;

@Component
public class InventoryUpdaterListener {
    private static final Logger logger = LoggerFactory.getLogger(InventoryUpdaterListener.class);

    @Autowired
    private ProductoService productoService; // Inyección del servicio de productos

    @EventListener
    public void handleOrderCreated(OrderCreaterEvent event) {
        // Reducir el stock del producto
        boolean stockReduced = productoService.reducirStock(event.getProductoNombre(), event.getCantidad());

        if (stockReduced) {
            logger.info("Stock reducido para el pedido ID: {}, Producto: {}, Cantidad: {}",
                    event.getOrderId(), event.getProductoNombre(), event.getCantidad());
        } else {
            logger.warn("No se pudo reducir el stock para el pedido ID: {}, Producto: {}, Cantidad: {}",
                    event.getOrderId(), event.getProductoNombre(), event.getCantidad());
        }
    }
}

