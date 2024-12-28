package pruebaccenture.servicesInterfaces;

import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarProductoDTO;
import pruebaccenture.modelEntity.Franquicia;
import pruebaccenture.modelEntity.Producto;

import java.util.List;

public interface IProductoService {
    public List<Producto> findAll();
    public Producto save(Producto producto);
    public Producto findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Producto agregarProducto(AgregarProductoDTO nuevoProducto);
    public Producto actualizarNombre(Long id, ActualizarDTO nuevoProducto);
}
