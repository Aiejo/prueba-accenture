package pruebaccenture.modelDTO;

import lombok.Data;

@Data
public class ProductoConSucursalDTO {
    private Long id;
    private String nombre;
    private int cantidadStock;
    private SucursalDTO sucursal;
    public ProductoConSucursalDTO(Long id, String nombre, int cantidadStock, SucursalDTO sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadStock = cantidadStock;
        this.sucursal = sucursal;
    }
    @Data
    public static class SucursalDTO {
        private Long id;
        private String nombre;
        public SucursalDTO(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }
    }
}



