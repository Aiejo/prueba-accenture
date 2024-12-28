package pruebaccenture.controllers;

import org.springframework.web.bind.annotation.*;
import pruebaccenture.model.Sucursal;
import pruebaccenture.services.SucursalService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {
    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping("/")
    public Flux<Sucursal> getAllSucursales() {
        return sucursalService.getAllSucursales();
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
