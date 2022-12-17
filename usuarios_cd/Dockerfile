# Indicamos la imagen base a partir de la cual queremos construir nuestra imagen
FROM gradle:7-jdk17-alpine

# Creamos el directorio que queremos compartir y copiamos los archivos que queremos incluir en él
RUN mkdir comprascd
COPY ./comprascd comprascd

# Establecemos el directorio compartido como directorio de trabajo
WORKDIR comprascd

# Exponemos el puerto 8082 para que podamos acceder al contenido del directorio compartido desde el host
EXPOSE 8081

# Arrancamos la app para servir el contenido del directorio compartido
CMD ["java", "-jar", "compras-0.0.1-SNAPSHOT.jar"]