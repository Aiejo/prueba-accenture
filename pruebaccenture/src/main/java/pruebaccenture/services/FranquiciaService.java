package pruebaccenture.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.modelDTO.ProductoConSucursalDTO;
import pruebaccenture.modelEntity.Franquicia;
import pruebaccenture.modelEntity.Producto;
import pruebaccenture.modelEntity.Sucursal;
import pruebaccenture.repositoriesInterfaces.IFranquiciaRepository;
import pruebaccenture.repositoriesInterfaces.ISucursalRepository;
import pruebaccenture.servicesInterfaces.IFranquiciaService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FranquiciaService implements IFranquiciaService {

    @Autowired
    private IFranquiciaRepository franquiciaRepository;

    @Autowired
    private ISucursalRepository sucursalRepository;


    @Override
    public List<Franquicia> findAll(){
        return franquiciaRepository.findAll();
    }

    @Override
    public Franquicia save(Franquicia franquicia){
        return franquiciaRepository.save(franquicia);
    }

    @Override
    public Franquicia findById(Long id){
        return franquiciaRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return franquiciaRepository.existsById(id);
    }
    @Override
    public void deleteById(Long id) {
        franquiciaRepository.deleteById(id);
    }

    @Override
    public Franquicia actualizarNombre(Long id, ActualizarDTO nuevaFranquicia) {
        Franquicia franquicia = franquiciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada."));
        if(nuevaFranquicia.getNuevoNombre() != null){
            franquicia.setNombre(nuevaFranquicia.getNuevoNombre());
        }
        return franquiciaRepository.save(franquicia);
    }

    @Override
    public List<ProductoConSucursalDTO> obtenerProductosConMasStock(Long idFranquicia){
        Franquicia franquicia = franquiciaRepository.findById(idFranquicia)
                .orElseThrow(() -> new RuntimeException("Franquicia no encontrada."));

        List<ProductoConSucursalDTO> productosConMayorStock = new ArrayList<>();
        for (Sucursal sucursal : franquicia.getSucursales()) {
            // Obtener producto con mayor stock
            Producto mayorStock = sucursal.getProductos().stream()
                    .max(Comparator.comparingInt(Producto::getCantidadStock))
                    .orElse(null);
            // Agregar al resultado objeto que incluya la información de la sucursal
            if (mayorStock != null) {
                ProductoConSucursalDTO.SucursalDTO sucursalDTO = new ProductoConSucursalDTO.SucursalDTO(
                        sucursal.getId(),
                        sucursal.getNombre()
                );
                ProductoConSucursalDTO dto = new ProductoConSucursalDTO(
                        mayorStock.getId(),
                        mayorStock.getNombre(),
                        mayorStock.getCantidadStock(),
                        sucursalDTO
                );
                productosConMayorStock.add(dto);
            }
        }
        return productosConMayorStock;
    }
}
