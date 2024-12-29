package pruebaccenture.modelTest;
import org.junit.jupiter.api.Test;
import pruebaccenture.model.Producto;

import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {
    @Test
    void crearProductoConDataValida() {
        Producto producto = new Producto(1L, "Hamburguesa", 100, 1L);
        assertEquals(1L, producto.getId());
        assertEquals("Hamburguesa", producto.getNombre());
        assertEquals(100, producto.getCantidadStock());
        assertEquals(1L, producto.getSucursalId());
    }

    @Test
    void noCrearProductoConStockNegativvo() {
        assertThrows(IllegalArgumentException.class, () -> new Producto(null, "Coca Cola", -5, 1L));
    }
}
