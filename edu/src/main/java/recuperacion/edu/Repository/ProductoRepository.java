package recuperacion.edu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recuperacion.edu.Entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByNombre(String nombre);
}
