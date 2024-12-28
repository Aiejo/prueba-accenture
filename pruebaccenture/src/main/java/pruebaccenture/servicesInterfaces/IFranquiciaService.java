package pruebaccenture.servicesInterfaces;

import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.modelDTO.ProductoConSucursalDTO;
import pruebaccenture.modelEntity.Franquicia;
import pruebaccenture.modelEntity.Sucursal;

import java.util.List;

public interface IFranquiciaService {
    public List<Franquicia> findAll();
    public Franquicia save(Franquicia franquicia);
    public Franquicia findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Franquicia actualizarNombre(Long id, ActualizarDTO nuevaFranquicia);
    public List<ProductoConSucursalDTO> obtenerProductosConMasStock(Long idFranquicia);
}
