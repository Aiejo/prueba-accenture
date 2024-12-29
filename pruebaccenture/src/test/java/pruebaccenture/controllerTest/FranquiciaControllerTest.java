package pruebaccenture.controllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pruebaccenture.controllers.FranquiciaController;
import pruebaccenture.model.Franquicia;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.ProductoConSucursalDTO;
import pruebaccenture.services.FranquiciaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FranquiciaControllerTest {


    @Mock
    private FranquiciaService franquiciaService;

    @InjectMocks
    private FranquiciaController franquiciaController;

    @Test
    void testObtenerFranquicias() {
        List<Franquicia> franquicias = List.of(
                new Franquicia(1L, "Franquicia A"),
                new Franquicia(2L, "Franquicia B")
        );
        when(franquiciaService.findAll()).thenReturn(Flux.fromIterable(franquicias));
        StepVerifier.create(franquiciaController.obtenerFranquicias())
                .expectNext(franquicias.get(0))
                .expectNext(franquicias.get(1))
                .verifyComplete();
    }

    @Test
    void testObtenerFranquiciaPorId_Existe() {
        Franquicia franquicia = new Franquicia(1L, "Franquicia A");
        when(franquiciaService.findById(1L)).thenReturn(Mono.just(franquicia));
        StepVerifier.create(franquiciaController.obtenerFranquiciaPorId(1L))
                .expectNext(franquicia)
                .verifyComplete();
    }

    @Test
    void testObtenerFranquiciaPorId_NoExiste() {
        when(franquiciaService.findById(1L)).thenReturn(Mono.empty());
        StepVerifier.create(franquiciaController.obtenerFranquiciaPorId(1L))
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException &&
                        ((ResponseStatusException) throwable).getStatusCode().equals(HttpStatus.NOT_FOUND))
                .verify();
    }

    @Test
    void testCrearFranquicia_Exito() {
        Franquicia franquicia = new Franquicia(null, "Franquicia A");
        when(franquiciaService.save(franquicia)).thenReturn(Mono.just(new Franquicia(1L, "Franquicia A")));

        StepVerifier.create(franquiciaController.crearFranquicia(franquicia))
                .assertNext(response -> {
                    assertEquals(HttpStatus.CREATED, response.getStatusCode());
                    assertEquals("Franquicia agregada exitosamente.", response.getBody().get("message"));
                })
                .verifyComplete();
    }

    @Test
    void testCrearFranquicia_Error() {
        Franquicia franquicia = new Franquicia(null, "Franquicia A");
        when(franquiciaService.save(franquicia)).thenReturn(Mono.error(new RuntimeException("Simulated Error")));

        StepVerifier.create(franquiciaController.crearFranquicia(franquicia))
                .assertNext(response -> {
                    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
                    assertTrue(response.getBody().get("message").contains("Simulated Error"));
                })
                .verifyComplete();
    }

    @Test
    void testActualizarFranquicia_Exito() {
        ActualizarDTO actualizarDTO = new ActualizarDTO("Franquicia Actualizada",null);
        Franquicia franquiciaActualizada = new Franquicia(1L, "Franquicia Actualizada");

        when(franquiciaService.actualizar(1L, actualizarDTO)).thenReturn(Mono.just(franquiciaActualizada));

        StepVerifier.create(franquiciaController.actualizarFranquicia(1L, actualizarDTO))
                .expectNext(franquiciaActualizada)
                .verifyComplete();
    }

    @Test
    void testActualizarFranquicia_NoExiste() {
        ActualizarDTO actualizarDTO = new ActualizarDTO("Franquicia Actualizada",null);

        when(franquiciaService.actualizar(1L, actualizarDTO)).thenReturn(Mono.empty());

        StepVerifier.create(franquiciaController.actualizarFranquicia(1L, actualizarDTO))
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException &&
                        ((ResponseStatusException) throwable).getStatusCode().equals(HttpStatus.NOT_FOUND))
                .verify();
    }

    @Test
    void testObtenerProductosConMasStock_Exito() {
        List<ProductoConSucursalDTO> productos = List.of(
                new ProductoConSucursalDTO(1L, "Producto A", 100, new ProductoConSucursalDTO.SucursalDTO(1L, "Sucursal 1")),
                new ProductoConSucursalDTO(2L, "Producto B", 200, new ProductoConSucursalDTO.SucursalDTO(2L, "Sucursal 2"))
        );

        when(franquiciaService.obtenerProductosConMasStock(1L)).thenReturn(Flux.fromIterable(productos));

        StepVerifier.create(franquiciaController.obtenerProductosConMasStock(1L))
                .expectNext(productos.get(0))
                .expectNext(productos.get(1))
                .verifyComplete();
    }

    @Test
    void testObtenerProductosConMasStock_NoExisten() {
        when(franquiciaService.obtenerProductosConMasStock(1L)).thenReturn(Flux.empty());

        StepVerifier.create(franquiciaController.obtenerProductosConMasStock(1L))
                .expectErrorMatches(throwable -> throwable instanceof ResponseStatusException &&
                        ((ResponseStatusException) throwable).getStatusCode().equals(HttpStatus.NOT_FOUND))
                .verify();
    }
}

