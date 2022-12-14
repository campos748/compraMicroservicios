# Compra Microservicios
 Tienda virtual con una arquitectura de microservicios y diferentes archivos que permitan su despliegue en la nube AWS con Docker Swarm. Existirán 3 microservicios y cada uno de ellos estará documentado vía OpenAPI.
 
 Para el desarrollo de estos microservicios se emplea el framework Spring Boot y como base de datos se emplea MongoDB. Además se utiliza gradle como gestor de dependencias.
## Los microservicios son los siguientes:
 * [lista-cds](https://github.com/campos748/compraMicroservicios/tree/main/lista-cds): Microservicio encargado de listar los CDs que existen en la tienda. También incluye métodos CRUD. 
 * [Carrito](https://github.com/campos748/compraMicroservicios/tree/main/Carrito): Microservicio encargado de añadir CDs a un carrito para su posterior compra. También permite calcular el precio total del carrito y eliminar CDs del carrito.
 * [usuario_cd](https://github.com/campos748/compraMicroservicios/tree/main/usuarios_cd): Microservicio que permite identificarse como usuario y formalizar la compra.
 
## Herramientas para el despliegue en AWS
Para lograr desplegar la aplicación en la nube de AWS hemos creado las imágenes Docker que luego usaremos en un docker-stack.yml. Los Dockerfile se encuentran en la carpeta [/1_nube](https://github.com/campos748/compraMicroservicios/tree/main/1_nube) del proyecto.

## Arquitectura de la aplicación
![Arquitectura de la aplicación](/0_imagenes/Arquitectura.jpg)

## Patrones de diseño empleados

1. ### Patrón de comunicación
Estilo → **Invocación Remota de Procedimientos**

2. ### Patrón de despliegue
Servicio único por host → **Servicio por máquina**

3. ### Patrón de arquitectura de base de datos
**Base de datos por servicio**

4. ### Patrón de consistencia de datos
**Saga-Coreografía**

* Confirmar compra → Sacar los CDs del Carrito y Borrar el Carrito
* Añadir CD al carrito → Comprar CD existente

## Despliegue de la aplicación
Gracias a haber generado nuestras propias imágenes y haberlas subido al Docker Hub será suficiente con acceder a la carpeta [/2_despliegue](https://github.com/campos748/compraMicroservicios/tree/main/2_despliegue) y dentro de nuestro enjambre de Docker Swarm ejecutar el comando:
```
docker stack deploy -c docker-stack.yml cds
```
Debido a ciertos problemas con la inicialización de la base de datos mongodb de manera automática, insertaremos los datos utilizando un script de inserción que está en la misma carpeta [/2_despliegue](https://github.com/campos748/compraMicroservicios/tree/main/2_despliegue). Para ello accedemos al contenedor con la base de datos mongo y, despues de haber asignado permisos de ejecución, ejecutamos el script:
```
chmod +777 scriptDatos.sh
./scriptDatos.sh <nombre contenedor>
```
Una vez hecho realizado este proceso, podemos conectarnos a cada una de las APIs de los servicios a través de las siguientes direcciones:
* lista_cds:  <IP_Pública>:8082/api
* carrito:    <IP_Pública>:8080/api
* usuario_cd: <IP_Pública>:8081/api

Un ejemplo de ejecución sería el que se muestra en el siguiente vídeo:
[vídeo ejemplo](/0_imagenes/ejemplo.mp4)
