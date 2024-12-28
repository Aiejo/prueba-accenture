package pruebaccenture.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebaccenture.modelDTO.ActualizarDTO;
import pruebaccenture.modelDTO.AgregarProductoDTO;
import pruebaccenture.modelDTO.AgregarSucursalDTO;
import pruebaccenture.modelEntity.Franquicia;
import pruebaccenture.modelEntity.Producto;
import pruebaccenture.modelEntity.Sucursal;
import pruebaccenture.repositoriesInterfaces.IProductoRepository;
import pruebaccenture.repositoriesInterfaces.ISucursalRepository;
import pruebaccenture.servicesInterfaces.IProductoService;
import pruebaccenture.servicesInterfaces.ISucursalService;

import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ISucursalRepository sucursalRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }
    @Override
    public Producto findById(Long id){
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }
    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto agregarProducto(AgregarProductoDTO nuevoProducto){
        Sucursal sucursal = sucursalRepository.findById(nuevoProducto.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no existe."));
        Producto producto = new Producto();
        producto.setNombre(nuevoProducto.getNombre());
        producto.setCantidadStock(nuevoProducto.getCantidadStock());
        producto.setSucursal(sucursal);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarNombre(Long id, ActualizarDTO nuevoProducto){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado."));
        if(nuevoProducto.getNuevoNombre() != null){
            producto.setNombre(nuevoProducto.getNuevoNombre());
        }
        if(nuevoProducto.getNuevaCantidadStock() != null){
            producto.setCantidadStock(nuevoProducto.getNuevaCantidadStock());
        }
        return productoRepository.save(producto);
    }



}

