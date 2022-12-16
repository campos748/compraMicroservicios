# Indicamos la imagen base a partir de la cual queremos construir nuestra imagen
FROM gradle:7-jdk17-alpine

# Creamos el directorio que queremos compartir y copiamos los archivos que queremos incluir en él
RUN mkdir /listacd
COPY ./listacds /listacd

# Establecemos el directorio compartido como directorio de trabajo de Apache
WORKDIR /listacd

# Exponemos el puerto 80 para que podamos acceder al contenido del directorio compartido desde el host
EXPOSE 8082

# Arrancamos Apache para servir el contenido del directorio compartido
CMD ["java", "-jar", "lista-cds-0.0.1-SNAPSHOT.jar"]
