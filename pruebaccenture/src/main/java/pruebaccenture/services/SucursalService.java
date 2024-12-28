package pruebaccenture.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.modelEntity.Franquicia;
import pruebaccenture.modelEntity.Producto;
import pruebaccenture.modelEntity.Sucursal;
import pruebaccenture.repositoriesInterfaces.IFranquiciaRepository;
import pruebaccenture.repositoriesInterfaces.ISucursalRepository;
import pruebaccenture.servicesInterfaces.ISucursalService;

import java.util.List;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private IFranquiciaRepository franquiciaRepository;

    @Autowired
    private ISucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> findAll(){
        return sucursalRepository.findAll();
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
}
