package pruebaccenture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Producto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Producto {
    @Id
    private Long id;
    private String nombre;
    private Integer cantidadStock;
    private Long sucursalId;
}
