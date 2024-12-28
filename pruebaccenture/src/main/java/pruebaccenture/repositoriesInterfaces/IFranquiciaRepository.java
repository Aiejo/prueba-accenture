package pruebaccenture.repositoriesInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebaccenture.modelEntity.Franquicia;

@Repository
public interface IFranquiciaRepository extends JpaRepository<Franquicia, Long> {
}
