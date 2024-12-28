package pruebaccenture.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarProductoDTO;
import pruebaccenture.modelEntity.Producto;
import pruebaccenture.servicesInterfaces.IProductoService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/")
    public ResponseEntity<?> obtenerProductos(){
        try{
            List<Producto> productos = productoService.findAll();
            return ResponseEntity.status(200).body(productos);
        }catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al obtener productos: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            System.out.println(id);
            Producto producto = productoService.findById(id);
            if(producto==null){
                response.put("message", "Producto no encontrada.");
                return ResponseEntity.status(404).body(response);
            }
            return ResponseEntity.status(200).body(producto);
        } catch (Exception e) {
            response.put("message", "Error al obtener producto: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> crearProducto(@RequestBody AgregarProductoDTO nuevoProducto) {
        Map<String, String> response = new HashMap<>();
        try {
            productoService.agregarProducto(nuevoProducto);
            response.put("message", "Producto añadido exitosamente.");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.put("message", "Error al añadir producto: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ActualizarDTO nuevoProducto) {
        Map<String, String> response = new HashMap<>();
        try {
            Producto productoActualizado = productoService.actualizarNombre(id, nuevoProducto);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            response.put("message", "Error al actualizar producto: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

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
}
