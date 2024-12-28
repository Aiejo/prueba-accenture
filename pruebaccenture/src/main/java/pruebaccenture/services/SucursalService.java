package pruebaccenture.services;


import pruebaccenture.model.Sucursal;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SucursalService  {
    Flux<Sucursal> findAll();
    Mono<Sucursal> findById(Long id);
    Mono<Sucursal> save(Sucursal sucursal);
    Mono<Boolean> existsById(Long id);
    Mono<Void> deleteById(Long id);
    Mono<Sucursal> agregarSucursal(AgregarSucursalDTO nuevaSucursal);
    Mono<Sucursal> actualizar(Long id, ActualizarDTO nuevaSucursal);
}
