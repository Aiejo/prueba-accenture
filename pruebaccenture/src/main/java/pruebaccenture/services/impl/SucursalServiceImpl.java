package pruebaccenture.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pruebaccenture.model.Producto;
import pruebaccenture.model.Sucursal;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.repositories.FranquiciaRepository;
import pruebaccenture.repositories.SucursalRepository;
import pruebaccenture.services.SucursalService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    private final FranquiciaRepository franquiciaRepository;

    @Override
    public Flux<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public Mono<Sucursal> findById(Long id){
        return sucursalRepository.findById(id);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return sucursalRepository.existsById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return sucursalRepository.deleteById(id);
    }

    @Override
    public Mono<Sucursal> save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Mono<Sucursal> agregarSucursal(AgregarSucursalDTO nuevaSucursal){
        return franquiciaRepository.existsById(nuevaSucursal.getIdFranquicia())
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new Exception("La franquicia no existe."));
                    }
                    Sucursal sucursal = new Sucursal();
                    sucursal.setNombre(nuevaSucursal.getNombre());
                    sucursal.setFranquiciaId(nuevaSucursal.getIdFranquicia());
                    return sucursalRepository.save(sucursal);
                });
    }

    @Override
    public Mono<Sucursal> actualizar(Long id, ActualizarDTO nuevaSucursal) {
        return sucursalRepository.findById(id)
                .flatMap(existingSucursal -> {
                    existingSucursal.setNombre(nuevaSucursal.getNuevoNombre());
                    return sucursalRepository.save(existingSucursal);
                });
    }
}
