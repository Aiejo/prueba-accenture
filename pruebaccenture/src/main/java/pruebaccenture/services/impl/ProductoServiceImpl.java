package pruebaccenture.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pruebaccenture.model.Producto;
import pruebaccenture.model.Sucursal;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarProductoDTO;
import pruebaccenture.repositories.ProductoRepository;
import pruebaccenture.repositories.SucursalRepository;
import pruebaccenture.services.ProductoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    private final SucursalRepository sucursalRepository;

    @Override
    public Flux<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Mono<Producto> findById(Long id){
        return productoRepository.findById(id);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return productoRepository.existsById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return productoRepository.deleteById(id);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Mono<Producto> agregarProducto(AgregarProductoDTO nuevoProducto){
        return sucursalRepository.existsById(nuevoProducto.getIdSucursal())
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new Exception("La sucursal no existe."));
                    }
                    Producto producto = new Producto();
                    producto.setNombre(nuevoProducto.getNombre());
                    producto.setCantidadStock(nuevoProducto.getCantidadStock());
                    producto.setSucursalId(nuevoProducto.getIdSucursal());
                    return productoRepository.save(producto);
                });
    }

    @Override
    public Mono<Producto> actualizar(Long id, ActualizarDTO nuevoProducto) {
        return productoRepository.findById(id)
                .flatMap(existingProducto -> {
                    if(nuevoProducto.getNuevoNombre() != null){
                        existingProducto.setNombre(nuevoProducto.getNuevoNombre());
                    }
                    if(nuevoProducto.getNuevaCantidadStock() != null){
                        existingProducto.setCantidadStock(nuevoProducto.getNuevaCantidadStock());
                    }
                    return productoRepository.save(existingProducto);
                });
    }
}
