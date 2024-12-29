package pruebaccenture.modelTest;
import org.junit.jupiter.api.Test;
import pruebaccenture.model.Franquicia;
import static org.junit.jupiter.api.Assertions.*;

class FranquiciaTest {

    @Test
    void crearFranquiciaConDatosValidos() {
        Franquicia franquicia = new Franquicia(1L, "Euro la frontera");
        assertEquals(1L, franquicia.getId());
        assertEquals("Euro la frontera", franquicia.getNombre());
    }

    @Test
    void noCrearFranquiciaConNombreNuloOVacio() {
        assertThrows(IllegalArgumentException.class, () -> new Franquicia(1L, null));
        assertThrows(IllegalArgumentException.class, () -> new Franquicia(1L, ""));
    }
}