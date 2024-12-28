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
        Mono<Boolean> franquicia = franquiciaRepository.existsById(nuevaSucursal.getIdFranquicia());
        if(franquicia.block() == false){
            return Mono.error(new Exception("La franquicia no existe"));
        }
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nuevaSucursal.getNombre());
        sucursal.setFranquiciaId(nuevaSucursal.getIdFranquicia());
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Mono<Sucursal> actualizar(Long id, ActualizarDTO nuevaSucursal) {
        return sucursalRepository.findById(id)
                .flatMap(existingSucursal -> {
                    existingSucursal.setNombre(nuevaSucursal.getNuevoNombre());
                    return sucursalRepository.save(existingSucursal);
                });
    }
    /*

    @Override
    public Sucursal save(Sucursal sucursal){

        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal findById(Long id){
        return sucursalRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return sucursalRepository.existsById(id);
    }
    @Override
    public void deleteById(Long id) {
        sucursalRepository.deleteById(id);
    }

    @Override
    public Sucursal agregarSucursal(AgregarSucursalDTO nuevaSucursal){
        Franquicia franquicia = franquiciaRepository.findById(nuevaSucursal.getIdFranquicia())
                .orElseThrow(() -> new RuntimeException("Franquicia no existe."));
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nuevaSucursal.getNombre());
        sucursal.setFranquicia(franquicia);
        return sucursalRepository.save(sucursal);
    }

    @Override
    @Transactional
    public Sucursal actualizarNombre(Long id, ActualizarDTO nuevaSucursal) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada."));
        if(nuevaSucursal.getNuevoNombre() != null){
            sucursal.setNombre(nuevaSucursal.getNuevoNombre());
        }
        return sucursalRepository.save(sucursal);
    }
     */
}
