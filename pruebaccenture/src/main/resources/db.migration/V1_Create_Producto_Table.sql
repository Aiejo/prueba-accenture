CREATE TABLE Producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    cantidad_stock INT NOT NULL,
    sucursal_id INT NOT NULL,
    CONSTRAINT fk_sucursal FOREIGN KEY (sucursal_id) REFERENCES Sucursal (id) ON DELETE CASCADE
);