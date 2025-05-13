package recuperacion.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recuperacion.edu.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(String orderId);
}
