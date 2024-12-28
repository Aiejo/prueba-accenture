package pruebaccenture.services;

import org.springframework.stereotype.Service;
import pruebaccenture.model.Franquicia;
import pruebaccenture.model.Producto;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarProductoDTO;
import pruebaccenture.repositories.ProductoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductoService  {
    Flux<Producto> findAll();
    Mono<Producto> findById(Long id);
    Mono<Producto> save(Producto producto);
    Mono<Boolean> existsById(Long id);
    Mono<Void> deleteById(Long id);
    Mono<Producto> agregarProducto(AgregarProductoDTO nuevoProducto);
    Mono<Producto> actualizar(Long id, ActualizarDTO nuevoProducto);
}


