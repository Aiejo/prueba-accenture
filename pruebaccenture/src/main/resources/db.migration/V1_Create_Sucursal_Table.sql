CREATE TABLE Sucursal (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    franquicia_id INT NOT NULL,
    CONSTRAINT fk_franquicia FOREIGN KEY (franquicia_id) REFERENCES Franquicia (id) ON DELETE CASCADE
);