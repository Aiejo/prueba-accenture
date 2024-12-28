package pruebaccenture.services;

import org.springframework.stereotype.Service;
import pruebaccenture.model.Sucursal;
import pruebaccenture.repositories.SucursalRepository;
import reactor.core.publisher.Flux;

@Service
public class SucursalService  {

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public Flux<Sucursal> getAllSucursales() {
        return sucursalRepository.findAll();
    }
    /*
    public Flux<Sucursal> findAll(){
        try {
            return sucursalRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return Flux.error(new RuntimeException("Error al obtener sucursales."));
        }
    }
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
