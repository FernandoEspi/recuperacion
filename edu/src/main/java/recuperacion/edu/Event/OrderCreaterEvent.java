package recuperacion.edu.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class OrderCreaterEvent {
    private final String orderId;
    private final String email;
    private final String products;
    private final String productoNombre; // Nuevo campo para el nombre del producto
    private final int cantidad; // Nuevo campo para la cantidad del producto

    // Constructor modificado para incluir el nombre del producto y la cantidad
    public OrderCreaterEvent(String orderId, String email, String products, String productoNombre, int cantidad) {
        this.orderId = orderId;
        this.email = email;
        this.products = products;
        this.productoNombre = productoNombre;
        this.cantidad = cantidad;
    }
}
