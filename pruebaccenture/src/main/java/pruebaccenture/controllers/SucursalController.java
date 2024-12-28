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
/*
    @Autowired
    private ISucursalService sucursalService;


    @GetMapping("/")
    public ResponseEntity<?> obtenerSucursales() {
        try {
            List<Sucursal> sucursales = sucursalService.findAll();
            return ResponseEntity.status(200).body(sucursales);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al obtener sucursal: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSucursalPorId(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Sucursal sucursal = sucursalService.findById(id);
            if(sucursal==null){
                response.put("message", "Sucursal no encontrada.");
                return ResponseEntity.status(404).body(response);
            }
            return ResponseEntity.status(200).body(sucursal);
        } catch (Exception e) {
            response.put("message", "Error al obtener sucursal: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> crearSucursal(@RequestBody AgregarSucursalDTO nuevaSucursal) {
        Map<String, String> response = new HashMap<>();
        try {
            sucursalService.agregarSucursal(nuevaSucursal);
            response.put("message", "Sucursal añadida exitosamente.");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.put("message", "Error al añadir sucursal: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarSucursal(@PathVariable Long id, @RequestBody ActualizarDTO nuevaSucursal) {
        Map<String, String> response = new HashMap<>();
        try {
            Sucursal sucursalActualizada = sucursalService.actualizarNombre(id, nuevaSucursal);
            return ResponseEntity.ok(sucursalActualizada);
        } catch (Exception e) {
            response.put("message", "Error al actualizar sucursal: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
     */
}
