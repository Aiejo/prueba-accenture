package pruebaccenture.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;


@Table("Franquicia")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Franquicia {
    @Id
    private Long id;
    private String nombre;
}