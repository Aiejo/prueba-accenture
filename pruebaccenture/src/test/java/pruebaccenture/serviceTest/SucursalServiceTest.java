package pruebaccenture.serviceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pruebaccenture.model.Sucursal;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.repositories.FranquiciaRepository;
import pruebaccenture.repositories.SucursalRepository;
import pruebaccenture.services.impl.SucursalServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {
    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private FranquiciaRepository franquiciaRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalService;

    @Test
    void testObtenerSucursales() {
        List<Sucursal> sucursales = List.of(
                new Sucursal(1L, "Sucursal A", 1L),
                new Sucursal(2L, "Sucursal B", 1L)
        );
        when(sucursalRepository.findAll()).thenReturn(Flux.fromIterable(sucursales));

        StepVerifier.create(sucursalService.findAll())
                .expectNext(sucursales.get(0))
                .expectNext(sucursales.get(1))
                .verifyComplete();
    }

    @Test
    void testObtenerSucuralPorId_Existe() {
        Sucursal sucursal = new Sucursal(1L, "Sucursal A", 1L);
        when(sucursalRepository.findById(1L)).thenReturn(Mono.just(sucursal));

        StepVerifier.create(sucursalService.findById(1L))
                .expectNext(sucursal)
                .verifyComplete();
    }

    @Test
    void testObtenerSucuralPorId_NoExiste() {
        when(sucursalRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(sucursalService.findById(1L))
                .verifyComplete();
    }

    @Test
    void testExisteSucursalPorId_True() {
        when(sucursalRepository.existsById(1L)).thenReturn(Mono.just(true));

        StepVerifier.create(sucursalService.existsById(1L))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void testExisteSucursalPorId_False() {
        when(sucursalRepository.existsById(1L)).thenReturn(Mono.just(false));

        StepVerifier.create(sucursalService.existsById(1L))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void testEliminarSucursalPorId() {
        when(sucursalRepository.deleteById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(sucursalService.deleteById(1L))
                .verifyComplete();
    }

    @Test
    void testGuardarSucursal() {
        Sucursal sucursal = new Sucursal(null, "Sucursal Nueva", 1L);
        Sucursal savedSucursal = new Sucursal(1L, "Sucursal Nueva", 1L);
        when(sucursalRepository.save(sucursal)).thenReturn(Mono.just(savedSucursal));

        StepVerifier.create(sucursalService.save(sucursal))
                .expectNext(savedSucursal)
                .verifyComplete();
    }

    @Test
    void testAgregarSucursal_Exito() {
        AgregarSucursalDTO dto = new AgregarSucursalDTO(1L,"Sucursal Nueva" );
        Sucursal sucursal = new Sucursal(null, "Sucursal Nueva", 1L);
        Sucursal savedSucursal = new Sucursal(1L, "Sucursal Nueva", 1L);

        when(franquiciaRepository.existsById(1L)).thenReturn(Mono.just(true));
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(Mono.just(savedSucursal));

        StepVerifier.create(sucursalService.agregarSucursal(dto))
                .assertNext(result -> {
                    assertEquals(savedSucursal.getId(), result.getId());
                    assertEquals(savedSucursal.getNombre(), result.getNombre());
                    assertEquals(savedSucursal.getFranquiciaId(), result.getFranquiciaId());
                })
                .verifyComplete();
    }

    @Test
    void testAgregarSucursal_FranquiciaNoExiste() {
        AgregarSucursalDTO dto = new AgregarSucursalDTO(1L,"Sucursal Nueva" );

        when(franquiciaRepository.existsById(1L)).thenReturn(Mono.just(false));

        StepVerifier.create(sucursalService.agregarSucursal(dto))
                .expectErrorMatches(throwable -> throwable instanceof Exception &&
                        throwable.getMessage().equals("La franquicia no existe."))
                .verify();
    }

    @Test
    void testActualizarSucursal_Exito() {
        Sucursal existingSucursal = new Sucursal(1L, "Sucursal Antiguo Nombre", 1L);
        Sucursal updatedSucursal = new Sucursal(1L, "Sucursal Nuevo Nombre", 1L);
        ActualizarDTO actualizarDTO = new ActualizarDTO("Sucursal Nuevo Nombre");

        when(sucursalRepository.findById(1L)).thenReturn(Mono.just(existingSucursal));
        when(sucursalRepository.save(existingSucursal)).thenReturn(Mono.just(updatedSucursal));

        StepVerifier.create(sucursalService.actualizar(1L, actualizarDTO))
                .assertNext(result -> {
                    assertEquals(updatedSucursal.getId(), result.getId());
                    assertEquals(updatedSucursal.getNombre(), result.getNombre());
                })
                .verifyComplete();
    }

    @Test
    void testActualizarSucursal_NoExiste() {
        ActualizarDTO actualizarDTO = new ActualizarDTO("Sucursal Nuevo Nombre");

        when(sucursalRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(sucursalService.actualizar(1L, actualizarDTO))
                .verifyComplete();
    }
}
