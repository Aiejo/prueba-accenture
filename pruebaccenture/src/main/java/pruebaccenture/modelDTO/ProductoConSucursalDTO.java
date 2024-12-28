package pruebaccenture.modelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductoConSucursalDTO {
    private Long id;
    private String nombre;
    private int cantidadStock;
    private SucursalDTO sucursal;

    @Data
    @AllArgsConstructor
    public static class SucursalDTO {
        private Long id;
        private String nombre;

    }
}



