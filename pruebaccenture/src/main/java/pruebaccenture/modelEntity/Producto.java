package pruebaccenture.modelEntity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="Producto")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String nombre;

    @Column()
    @NotNull
    private int cantidadStock;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    @JsonBackReference
    private Sucursal sucursal;

}
