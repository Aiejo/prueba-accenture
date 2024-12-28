package pruebaccenture.repositoriesInterfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pruebaccenture.modelEntity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
