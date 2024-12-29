package pruebaccenture.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;


@Table("Franquicia")
@NoArgsConstructor
@Data
public class Franquicia {
    @Id
    private Long id;
    private String nombre;

    public Franquicia(Long id, String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        this.id = id;
        this.nombre = nombre;
    }
}