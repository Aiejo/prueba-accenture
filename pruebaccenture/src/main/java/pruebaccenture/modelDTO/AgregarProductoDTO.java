package pruebaccenture.modelDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgregarProductoDTO {
    private long idSucursal;
    private String nombre;
    private int cantidadStock;

    public long getIdSucursal() {
        return idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }
}
