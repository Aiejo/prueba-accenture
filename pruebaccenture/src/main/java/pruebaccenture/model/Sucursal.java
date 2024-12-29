package pruebaccenture.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;

@Table("Sucursal")
@NoArgsConstructor
@Data
public class Sucursal {
    @Id
    @NotNull
    private Long id;
    private String nombre;
    private Long franquiciaId;

    public Sucursal(Long id, String nombre, Long franquiciaId) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (franquiciaId == null ) {
            throw new IllegalArgumentException("FranquiciaId requerido.");
        }
        this.id = id;
        this.nombre = nombre;
        this.franquiciaId = franquiciaId;
    }
}

