package pruebaccenture.controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pruebaccenture.controllers.ProductoController;
import pruebaccenture.model.Producto;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarProductoDTO;
import pruebaccenture.services.ProductoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {


    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void testObtenerProductos() {
        List<Producto> productos = List.of(
                new Producto(1L, "Producto A", 3, 1L),
                new Producto(2L, "Producto B", 4, 2L)
        );
        when(productoService.findAll()).thenReturn(Flux.fromIterable(productos));
        StepVerifier.create(productoController.obtenerProductos())
                .expectNext(productos.get(0))
                .expectNext(productos.get(1))
                .verifyComplete();
    }

    @Test
    void testObtenerProductoPorId_Existe() {
        Producto producto = new Producto(1L, "Producto A", 3, 1L);
        when(productoService.findById(1L)).thenReturn(Mono.just(producto));
        StepVerifier.create(productoController.obtenerProductoPorId(1L))
                .expectNext(producto)
                .verifyComplete();
    }

    @Test
    void testObtenerProductoPorId_NoExiste() {
        when(productoService.findById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(productoController.obtenerProductoPorId(1L))
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException &&
                        ((ResponseStatusException) throwable).getStatusCode().equals(HttpStatus.NOT_FOUND))
                .verify();
    }

    @Test
    void testCrearProducto_Exito() {
        AgregarProductoDTO nuevoProducto = new AgregarProductoDTO(1L, "Producto A", 3);
        when(productoService.agregarProducto(nuevoProducto)).thenReturn(Mono.just(new Producto(1L, "Producto A", 3, 1L)));

        StepVerifier.create(productoController.crearProducto(nuevoProducto))
                .assertNext(response -> {
                    assertEquals(HttpStatus.CREATED, response.getStatusCode());
                    assertEquals("Producto agregado exitosamente.", response.getBody().get("message"));
                })
                .verifyComplete();
    }

    @Test
    void testCrearProducto_Error() {
        AgregarProductoDTO nuevoProducto = new AgregarProductoDTO(1L, "Producto A", 3);
        when(productoService.agregarProducto(nuevoProducto)).thenReturn(Mono.error(new RuntimeException("Simulated Error")));

        StepVerifier.create(productoController.crearProducto(nuevoProducto))
                .assertNext(response -> {
                    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                    assertTrue(response.getBody().get("message").contains("Simulated Error"));
                })
                .verifyComplete();
    }

    @Test
    void testActualizarProducto_Exito() {
        ActualizarDTO actualizarDTO = new ActualizarDTO("Producto Actualizad",9);
        Producto productoActualizado = new Producto(1L, "Producto Actualizad", 9, 1L);

        when(productoService.actualizar(1L, actualizarDTO)).thenReturn(Mono.just(productoActualizado));

        StepVerifier.create(productoController.actualizarProducto(1L, actualizarDTO))
                .expectNext(productoActualizado)
                .verifyComplete();
    }

    @Test
    void testActualizarProducto_NoExiste() {
        ActualizarDTO actualizarDTO = new ActualizarDTO("Producto Actualizad",9);

        when(productoService.actualizar(1L, actualizarDTO)).thenReturn(Mono.empty());

        StepVerifier.create(productoController.actualizarProducto(1L, actualizarDTO))
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException &&
                        ((ResponseStatusException) throwable).getStatusCode().equals(HttpStatus.NOT_FOUND))
                .verify();
    }

    @Test
    void testEliminarProducto_Exito() {
        Long productoId = 1L;
        when(productoService.existsById(productoId)).thenReturn(Mono.just(true));
        when(productoService.deleteById(productoId)).thenReturn(Mono.empty());

        StepVerifier.create(productoController.eliminarProducto(productoId))
                .assertNext(response -> {
                    assertEquals(HttpStatus.CREATED, response.getStatusCode());
                    assertEquals("Producto borrado exitosamente.", response.getBody().get("message"));
                })
                .verifyComplete();
    }

    @Test
    void testEliminarProducto_NoExiste() {
        Long productoId = 1L;
        when(productoService.existsById(productoId)).thenReturn(Mono.just(false));

        StepVerifier.create(productoController.eliminarProducto(productoId))
                .assertNext(response -> {
                    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                    assertEquals("Producto no encontrado.", response.getBody().get("message"));
                })
                .verifyComplete();
    }
}




