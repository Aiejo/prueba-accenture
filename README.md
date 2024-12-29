# API Reactiva de Gestión de Franquicias, Sucursales y Productos

Este proyecto es una API desarrollada con Spring Boot utilizando Spring WebFlux para manejar operaciones reactivas. Está diseñada para gestionar franquicias, sucursales y productos, y está basada en una base de datos MySQL alojada en un RDS. La aplicación está dockerizada para facilitar su despliegue.

## Tecnologías Utilizadas

- **Spring Boot** con **Spring WebFlux** (para programación reactiva)
- **MySQL** (base de datos en Amazon RDS)
- **Docker** (para contenerización de la aplicación)

## Descripción

La API permite realizar operaciones CRUD sobre los siguientes recursos:
- **Franquicias**: Gestión de franquicias.
- **Sucursales**: Gestión de las sucursales de las franquicias.
- **Productos**: Gestión de productos asociados a las franquicias.

Asimismo la API cuenta con pruebas unitarias a los Controladores, servicioes y modelos realizadas con Mockito y JUnit.

## Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener lo siguiente instalado:

- **Docker**: Para crear y ejecutar contenedores.
- **JDK 17**: Para compilar y ejecutar la aplicación en caso de que necesites hacerlo fuera del contenedor.
- **MySQL RDS**: Debes tener una base de datos MySQL configurada y accesible en Amazon RDS.

## Instrucciones de Despliegue

### Despliegue Local con Docker

Para ejecutar la aplicación en tu entorno local, sigue estos pasos:

1. **Clonar repositorio**:
   
   Escoge el directorio de preferencia y ejecuta:
   ```bash
   git clone https://github.com/Aiejo/prueba-accenture.git
   ```

2. **Moverse a la carpeta del proyecto**:
   
   Abre una terminal y navega a la carpeta donde se encuentra el proyecto:
   ```bash
   cd pruebaaccenture
   ```

3. **Construir la imagen del Dockerfile:**:
   
   Asegúrate de que tu terminal está en la carpeta raíz del proyecto, donde se encuentra el Dockerfile. 
   Luego ejecuta el siguiente comando para construir la imagen de Docker:
   ```bash
   docker build -t prueba-accenture-image .
   ```

4. **Ejecutar el contenedor en el puerto 8080:**:
   
   Una vez que la imagen esté construida, ejecuta el siguiente comando para crear un contenedor basado en la imagen y exponer la aplicación en el puerto 8080:
   ```bash
   docker run --name contenedor-franquicia -p 8080:8080 prueba-accenture-image
   ```
5. **Acceder a la aplicación:**:
    La aplicación estará corriendo en http://localhost:8080. Puedes usar herramientas como Postman. 

## Documentación de API

La documentación de la API la encuentras en **Prueba Accenture.postman_collection.json**, ahi podrás probar todos los endpoint implementados.

### Nota 
Por cuestiones prácticas las variables de conexión a la base de datos están en el archivo .env. Esto se reconoce como una mala práctica.
Para que amazon no tumbe la base de datos, es necesario que tu agregues en DB_PASSWORD (sin los espacios):
**contra  base  accenture**