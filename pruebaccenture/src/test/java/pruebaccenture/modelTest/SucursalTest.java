package pruebaccenture.modelTest;
import org.junit.jupiter.api.Test;
import pruebaccenture.model.Franquicia;
import pruebaccenture.model.Sucursal;

import static org.junit.jupiter.api.Assertions.*;

class SucursalTest {

    @Test
    void crearSucursalConDatosValidos() {
        Sucursal sucursal = new Sucursal(1L, "Sucursal Centro", 1L);
        assertEquals(1L, sucursal.getId());
        assertEquals("Sucursal Centro", sucursal.getNombre());
        assertEquals(1L, sucursal.getFranquiciaId());
    }

    @Test
    void noCrearSucursalConNombreOFranquiciaIdNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Sucursal(1L, null, 1l));
        assertThrows(IllegalArgumentException.class, () -> new Sucursal(1L, "Sucursal centro", null));
    }
}