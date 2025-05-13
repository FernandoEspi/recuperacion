package recuperacion.edu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recuperacion.edu.Entity.Producto;
import recuperacion.edu.Repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto obtenerProducto(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    public boolean reducirStock(String nombre, int cantidad) {
        Producto producto = obtenerProducto(nombre);
        if (producto != null && producto.getStock() >= cantidad) {
            producto.setStock(producto.getStock() - cantidad);
            productoRepository.save(producto);
            return true; // Stock reducido exitosamente
        }
        return false; // No se pudo reducir el stock
    }
}
