package recuperacion.edu.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID único generado automáticamente

    @Column(nullable = false, unique = true)
    private String orderId; // Identificador del pedido

    @Column(nullable = false)
    private String email; // Correo electrónico del cliente

    @Column(nullable = false)
    private String products; // Lista de productos en el pedido

}
