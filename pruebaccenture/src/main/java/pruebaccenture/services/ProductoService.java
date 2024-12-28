package pruebaccenture.services;

import org.springframework.stereotype.Service;
import pruebaccenture.model.Producto;
import pruebaccenture.repositories.ProductoRepository;
import reactor.core.publisher.Flux;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Flux<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

/*
    public Flux<Producto> findAll(){
        try {
            return productoRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return Flux.error(new RuntimeException("Error al obtener productos."));
        }
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
    @Override
    public Producto findById(Long id){
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }
    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto agregarProducto(AgregarProductoDTO nuevoProducto){
        Sucursal sucursal = sucursalRepository.findById(nuevoProducto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no existe."));
        Producto producto = new Producto();
        producto.setNombre(nuevoProducto.getNombre());
        producto.setCantidadStock(nuevoProducto.getCantidadStock());
        producto.setSucursal(sucursal);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarNombre(Long id, ActualizarDTO nuevoProducto){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado."));
        if(nuevoProducto.getNuevoNombre() != null){
            producto.setNombre(nuevoProducto.getNuevoNombre());
        }
        if(nuevoProducto.getNuevaCantidadStock() != null){
            producto.setCantidadStock(nuevoProducto.getNuevaCantidadStock());
        }
        return productoRepository.save(producto);
    }

*/

}

