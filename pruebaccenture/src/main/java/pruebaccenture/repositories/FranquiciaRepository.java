package pruebaccenture.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pruebaccenture.model.Franquicia;

public interface FranquiciaRepository  extends R2dbcRepository<Franquicia, Long> {
}
