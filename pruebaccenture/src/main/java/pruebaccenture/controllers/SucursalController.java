package pruebaccenture.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pruebaccenture.model.Franquicia;
import pruebaccenture.model.Sucursal;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.services.SucursalService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

    private final SucursalService sucursalService;

    @GetMapping("/")
    public Flux<Sucursal> obtenerSucursales() {
        return sucursalService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Sucursal> obtenerSucursalPorId(@PathVariable Long id) {
        return sucursalService.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada")));
    }

    @PostMapping("/")
    public Mono<ResponseEntity<Map<String, String>>> crearSucursal(@RequestBody AgregarSucursalDTO nuevaSucursal) {
        return sucursalService.agregarSucursal(nuevaSucursal)
                .map(savedSucursal -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Sucursal agregada exitosamente.");
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .onErrorResume(e -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Error al añadir sucursal: " + e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
                });
    }

    @PatchMapping("/{id}")
    public Mono<Sucursal> actualizarSucursal(
            @PathVariable Long id,
            @RequestBody ActualizarDTO nuevaSucursal) {
        return sucursalService.actualizar(id, nuevaSucursal)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada")));
    }
}
