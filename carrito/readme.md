De primeras solo nos interesa conseguir el .jar se entra en el proyecto del servicio en concreto y se ejecuta

$>gradle tasks
$>gradle assemble
$>gradle build

El .jar estará en /build/libs

Ahora debemos crear una imagen con este .jar dentro:

Para eso utilizamos el siguiente Dockerfile:
************************************

# Indicamos la imagen base a partir de la cual queremos construir nuestra imagen
FROM gradle:7-jdk17-alpine

# Creamos el directorio que queremos compartir y copiamos los archivos que queremos incluir en él
RUN mkdir /carrito
COPY ./ficheros_carrito /carrito

# Establecemos el directorio compartido como directorio de trabajo
WORKDIR /carrito

# Exponemos el puerto 8080 para que podamos acceder al contenido del directorio compartido desde el host
EXPOSE 8080

# Arrancamos la app para servir el contenido del directorio compartido
CMD ["java", "-jar", "carrito-0.0.1-SNAPSHOT.jar"]

************************************
// Construimos la imagen usando el dockerfile
docker build -t image_carrito .

// Cambiamos etiqueta y hacemos el push para subir al docker hub
$>docker tag image_carrito albertovillasante/image_carrito
$>docker push albertovillasante/image_carrito

// Ahora ya tenemos la imagen y podemos utilizarla en AWS, un ejemplo de docker-stack.yml sería:

version: "3.7"

services:
listacds:
image: campos748/image_listacd:latest
networks:
- net_lista_cds
ports:
- "8082:8082"
deploy:
replicas: 1
db_lista:
image: mongo
networks:
- net_lista_cds
ports:
- "27019:27017"
deploy:
replicas: 1
networks:
net_lista_cds: {}