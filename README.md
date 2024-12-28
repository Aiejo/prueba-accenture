# Readme file

El despliegue de la aplicación se hará con docker.

Moverse a carpeta del proyecto: 
    cd pruebaaccenture

Construir imagen del DOckerfile: 
    docker build -t prueba-accenture-image .

Corre contenedor en el puerto 8080: 
    docker run --name contenedor-franquicia -p 8080:8080 prueba-accenture-image

Podemos reemplazar prueba-accenture-image y contenedor-franquicia por los nombres que queramos.
