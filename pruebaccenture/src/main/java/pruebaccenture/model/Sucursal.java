package pruebaccenture.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;


// Sucursal
@Table("sucursal")
@Data
public class Sucursal {
    @Id
    private Long id;
    private String nombre;

    @MappedCollection(idColumn = "sucursal_id")
    private List<Producto> productos;
}