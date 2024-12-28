package pruebaccenture.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarProductoDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.modelDTO.ProductoConSucursalDTO;
import pruebaccenture.modelEntity.Franquicia;
import pruebaccenture.modelEntity.Producto;
import pruebaccenture.modelEntity.Sucursal;
import pruebaccenture.servicesInterfaces.IFranquiciaService;
import pruebaccenture.servicesInterfaces.IProductoService;
import pruebaccenture.servicesInterfaces.ISucursalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/franquicia")
public class FranquiciaController {

    @Autowired
    private IFranquiciaService franquiciaService;

    @GetMapping("/")
    public ResponseEntity<?> obtenerFranquicias() {
        try {
            List<Franquicia> franquicias = franquiciaService.findAll();
            return ResponseEntity.status(200).body(franquicias);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al obtener franquicias: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerFranquiciaPorId(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Franquicia franquicia = franquiciaService.findById(id);
            if(franquicia==null){
                response.put("message", "Franquicia no encontrada.");
                return ResponseEntity.status(404).body(response);
            }
            return ResponseEntity.status(200).body(franquicia);
        } catch (Exception e) {
            response.put("message", "Error al obtener franquicias: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

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

    @GetMapping("/{id}/max-stock-sucursal")
    public ResponseEntity<?> obtenerProductosConMasStock(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            List<ProductoConSucursalDTO> productosConMayorStock = franquiciaService.obtenerProductosConMasStock(id);
            return ResponseEntity.ok(productosConMayorStock);
        } catch (Exception e) {
            response.put("message", "Error al obtener productos con mayor stock: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }
}
