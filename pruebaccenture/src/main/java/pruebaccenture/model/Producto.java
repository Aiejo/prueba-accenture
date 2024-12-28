package pruebaccenture.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("producto")
@Data
public class Producto {
    @Id
    private Long id;
    private String nombre;
    private Integer cantidadStock;
}
