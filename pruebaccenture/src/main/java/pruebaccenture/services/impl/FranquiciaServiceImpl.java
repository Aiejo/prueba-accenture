package pruebaccenture.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pruebaccenture.model.Franquicia;
import pruebaccenture.model.Producto;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.ProductoConSucursalDTO;
import pruebaccenture.repositories.FranquiciaRepository;
import pruebaccenture.repositories.ProductoRepository;
import pruebaccenture.repositories.SucursalRepository;
import pruebaccenture.services.FranquiciaService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@RequiredArgsConstructor
@Service
public class FranquiciaServiceImpl implements FranquiciaService {

    private final FranquiciaRepository franquiciaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    @Override
    public Flux<Franquicia> findAll(){
        return franquiciaRepository.findAll();
    }

    @Override
    public Mono<Franquicia> findById(Long id){
        return franquiciaRepository.findById(id);
    }

    @Override
    public Mono<Boolean> existsById(Long id) {
        return franquiciaRepository.existsById(id);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
       return franquiciaRepository.deleteById(id);
    }

    @Override
    public Mono<Franquicia> save(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    @Override
    public Mono<Franquicia> actualizar(Long id, ActualizarDTO nuevaFranquicia) {
        return franquiciaRepository.findById(id)
                .flatMap(existingFranquicia -> {
                    existingFranquicia.setNombre(nuevaFranquicia.getNuevoNombre());
                    return franquiciaRepository.save(existingFranquicia);
                });
    }

    @Override
    public Flux<ProductoConSucursalDTO> obtenerProductosConMasStock(Long idFranquicia) {
        return sucursalRepository.findByFranquiciaId(idFranquicia) // Sucursales por franquicia
                .flatMap(sucursal -> productoRepository.findBySucursalId(sucursal.getId()) // Productos por sucursal
                        .collectList() // Productos de sucursal en lista
                        .flatMapMany(productos -> {
                            return Flux.fromIterable(
                                    productos.stream()
                                            .max(Comparator.comparingInt(Producto::getCantidadStock))
                                            .map(mayorStock -> {
                                                ProductoConSucursalDTO.SucursalDTO sucursalDTO = new ProductoConSucursalDTO.SucursalDTO(
                                                        sucursal.getId(),
                                                        sucursal.getNombre()
                                                );
                                                return new ProductoConSucursalDTO(
                                                        mayorStock.getId(),
                                                        mayorStock.getNombre(),
                                                        mayorStock.getCantidadStock(),
                                                        sucursalDTO
                                                );
                                            })
                                            .stream().toList()
                            );
                        })
                );
    }
}