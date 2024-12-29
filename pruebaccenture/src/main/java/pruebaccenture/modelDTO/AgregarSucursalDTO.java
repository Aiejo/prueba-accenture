package pruebaccenture.modelDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgregarSucursalDTO {
    private long idFranquicia;
    private String nombre;

    public long getIdFranquicia() {
        return idFranquicia;
    }

    public String getNombre() {
        return nombre;
    }


}
