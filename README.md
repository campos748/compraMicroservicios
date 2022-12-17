# Compra Microservicios
 Tienda virtual con una arquitectura de microservicios y diferentes archivos que permitan su despliegue en la nube AWS con Docker Swarm. Existirán 3 microservicios y cada uno de ellos estará documentados vía OpenAPI.
 
 Para el desarrollo de estos microservicios se emplea el framework Spring Boot y como base de datos se emplea MongoDB. Además se utiliza gradle como gestor de dependencias.
## Los microservicios son los siguientes:
 * [lista-cds](https://github.com/campos748/compraMicroservicios/tree/main/lista-cds): Microservicio encargado de listar los CDs que existen en la tienda. También incluye métodos CRUD. 
 * [Carrito](https://github.com/campos748/compraMicroservicios/tree/main/Carrito): Microservicio que permite añadir CDs a un carrito para su posterior compra. También permite calcular el precio total del carrito y eliminar CDs del carrito.
 * [usuario_cd](https://github.com/campos748/compraMicroservicios/tree/main/usuarios_cd): Microservicio que permite identificarse como usuario y formalizar la compra.
 
## Herramientas para el despliegue en AWS
Para lograr desplegar la aplicación en la nube de AWS hemos creado las imágenes Docker que luego desplegaremos utilizando un docker-stack.yml. Todos los archivos referentes se encuentran en la carpeta [/nube](https://github.com/campos748/compraMicroservicios/tree/main/nube) del proyecto.

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
Gracias al haber generado nuestras propias imágenes y haberlas subido será suficiente con dentro de nuestro enjambre de Docker Swarm ejecutar el comando:
```
docker stack deploy -c docker-stack.yml cds
```
