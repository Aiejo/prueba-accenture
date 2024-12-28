package pruebaccenture.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;


@Table("franquicia")
@Data
public class Franquicia {
    @Id
    private Long id;
    private String nombre;

    @MappedCollection(idColumn = "franquicia_id")
    private List<Sucursal> sucursales;
}