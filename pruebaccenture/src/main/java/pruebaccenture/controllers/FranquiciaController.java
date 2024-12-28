package pruebaccenture.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pruebaccenture.model.Franquicia;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.ProductoConSucursalDTO;
import pruebaccenture.services.FranquiciaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/franquicia")
public class FranquiciaController {

    private final FranquiciaService franquiciaService;

    @GetMapping("/")
    public Flux<Franquicia> obtenerFranquicias() {
        return franquiciaService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Franquicia> obtenerFranquiciaPorId(@PathVariable Long id) {
        return franquiciaService.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquicia no encontrada")));
    }

    @PostMapping("/")
    public Mono<ResponseEntity<Map<String, String>>> crearFranquicia(@RequestBody Franquicia franquicia) {
        return franquiciaService.save(franquicia)
                .map(savedFranquicia -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Franquicia agregada exitosamente.");
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .onErrorResume(e -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Error al añadir franquicia: " + e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
                });
    }

    @PatchMapping("/{id}")
    public Mono<Franquicia> actualizarFranquicia(
            @PathVariable Long id,
            @RequestBody ActualizarDTO nuevaFranquicia) {
        return franquiciaService.actualizar(id, nuevaFranquicia)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquicia no encontrada")));
    }

    @GetMapping("/{id}/max-stock-sucursal")
    public Flux<ProductoConSucursalDTO> obtenerProductosConMasStock(@PathVariable Long id) {
        return franquiciaService.obtenerProductosConMasStock(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquicia no encontrada")));
    }
}



