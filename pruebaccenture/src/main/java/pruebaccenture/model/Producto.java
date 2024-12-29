package pruebaccenture.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("Producto")
@NoArgsConstructor
@Data
public class Producto {
    @Id
    private Long id;
    private String nombre;
    @Min(0)
    private Integer cantidadStock;
    private Long sucursalId;

    public Producto(Long id, String nombre, Integer cantidadStock, Long sucursalId) {
        if (cantidadStock < 0) {
            throw new IllegalArgumentException("La cantidad de stock no puede ser negativa");
        }
        this.id = id;
        this.nombre = nombre;
        this.cantidadStock = cantidadStock;
        this.sucursalId = sucursalId;
    }
}
