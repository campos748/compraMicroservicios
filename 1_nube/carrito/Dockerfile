# Indicamos la imagen base a partir de la cual queremos construir nuestra imagen
FROM gradle:7-jdk17-alpine

# Creamos el directorio que queremos compartir y copiamos los archivos que queremos incluir en él
# En este directorio tener el .jar
RUN mkdir /carrito
COPY ./ficheros_carrito /carrito
# Establecemos el directorio compartido como directorio de trabajo
WORKDIR /carrito
# Exponemos el puerto 8088 para que podamos acceder al contenido del directorio compartido desde el host
EXPOSE 8080

# Arrancamos la app para servir el contenido del directorio compartido
CMD ["java", "-jar", "carrito-0.0.1-SNAPSHOT.jar"]


