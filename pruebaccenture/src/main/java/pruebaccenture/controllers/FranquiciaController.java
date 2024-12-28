package pruebaccenture.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pruebaccenture.model.Franquicia;
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


    private Flux<Map<String, String>> buildErrorResponse(Exception e) {
        Map<String, String> errorResponse = Map.of("message", e.getMessage());
        return Flux.just(errorResponse);
    }
    
    @GetMapping
    public Flux<Franquicia> obtenerFranquicias() {
        return franquiciaService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Franquicia> obtenerFranquiciaPorId(@PathVariable Long id) {
        return franquiciaService.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquicia no encontrada")));
    }

    @PostMapping("/")
    public ResponseEntity<?> crearFranquicia(@RequestBody Franquicia franquicia) {
        Map<String, String> response = new HashMap<>();
        try {
            franquiciaService.save(franquicia);
            response.put("message", "Franquicia agregada exitosamente.");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.put("message", "Error al añadir franquicia " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarFranquicia(
            @PathVariable Long id,
            @RequestBody ActualizarDTO nuevaFranquicia) {
        Map<String, String> response = new HashMap<>();
        try {
            Franquicia franquiciaActualizada = franquiciaService.actualizarNombre(id, nuevaFranquicia);
            return ResponseEntity.ok(franquiciaActualizada);
        } catch (Exception e) {
            response.put("message", "Error al actualizar franquicia: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}


