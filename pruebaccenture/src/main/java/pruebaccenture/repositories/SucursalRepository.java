package pruebaccenture.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pruebaccenture.model.Sucursal;

public interface SucursalRepository extends R2dbcRepository<Sucursal, Long> {
}
