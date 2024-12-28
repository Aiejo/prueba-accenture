package pruebaccenture.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pruebaccenture.model.Producto;

public interface ProductoRepository extends R2dbcRepository<Producto, Long> {
}
