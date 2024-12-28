package pruebaccenture.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pruebaccenture.model.Producto;
import reactor.core.publisher.Flux;

public interface ProductoRepository extends R2dbcRepository<Producto, Long> {
    Flux<Producto> findBySucursalId(Long sucursalId);
}
