package pruebaccenture.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pruebaccenture.model.Producto;
import pruebaccenture.model.Sucursal;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarProductoDTO;
import pruebaccenture.services.ProductoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/")
    public Flux<Producto> obtenerProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrada")));
    }

    @PostMapping("/")
    public Mono<ResponseEntity<Map<String, String>>> crearProducto(@RequestBody AgregarProductoDTO nuevoProducto) {
        return productoService.agregarProducto(nuevoProducto)
                .map(savedProducto -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Producto agregada exitosamente.");
                    return ResponseEntity.status(HttpStatus.CREATED).body(response);
                })
                .onErrorResume(e -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Error al añadir producto: " + e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
                });
    }

    @PatchMapping("/{id}")
    public Mono<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ActualizarDTO nuevoProducto) {
        return productoService.actualizar(id, nuevoProducto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Sucursal no encontrada")));
    }

/*

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        Map<String,String> response = new HashMap<>();
        try {
            if (productoService.existsById(id)) {
                productoService.deleteById(id);
                response.put("message", "Producto borrado exitosamente.");
                return ResponseEntity.status(201).body(response);
            } else {
                response.put("message", "Producto no encontrado.");
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error al borrar producto: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

     */
}
