package pruebaccenture.modelDTO;

import lombok.AllArgsConstructor;

import javax.swing.text.html.Option;
import java.util.Optional;

@AllArgsConstructor
public class ActualizarDTO {
    private String nuevoNombre;

    private Integer nuevaCantidadStock;

    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public Integer getNuevaCantidadStock() {
        return nuevaCantidadStock ;
    }

    public ActualizarDTO(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

}
