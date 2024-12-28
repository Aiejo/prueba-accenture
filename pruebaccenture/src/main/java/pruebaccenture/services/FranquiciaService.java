package pruebaccenture.services;

import pruebaccenture.model.Franquicia;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.ProductoConSucursalDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FranquiciaService  {
    Flux<Franquicia> findAll();
    Mono<Franquicia> findById(Long id);
    Mono<Franquicia> save(Franquicia franquicia);
    Mono<Boolean> existsById(Long id);
    Mono<Void> deleteById(Long id);
    Mono<Franquicia> actualizar(Long id, ActualizarDTO nuevaFranquicia);
    Flux<ProductoConSucursalDTO> obtenerProductosConMasStock(Long idFranquicia);
}
