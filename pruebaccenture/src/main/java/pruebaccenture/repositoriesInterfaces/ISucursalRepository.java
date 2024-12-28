package pruebaccenture.repositoriesInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebaccenture.modelEntity.Sucursal;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Long> {
}
