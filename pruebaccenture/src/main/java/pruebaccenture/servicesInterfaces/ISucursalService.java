package pruebaccenture.servicesInterfaces;

import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.modelEntity.Franquicia;
import pruebaccenture.modelEntity.Sucursal;

import java.util.List;

public interface ISucursalService {
    public List<Sucursal> findAll();
    public Sucursal save(Sucursal sucursal);
    public Sucursal findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Sucursal agregarSucursal(AgregarSucursalDTO nuevaSucursal);
    public Sucursal actualizarNombre(Long id, ActualizarDTO nuevaSucursal);
}
