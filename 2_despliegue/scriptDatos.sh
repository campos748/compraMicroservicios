#!/bin/bash
docker cp ./lista_inicial.csv "$1":/tmp/lista_inicial.csv
docker cp ./carrito_inicial.csv "$1":/tmp/carrito_inicial.csv
docker cp ./compras_inicial.csv "$1":/tmp/compras_inicial.csv

docker exec "$1" mongoimport  --db tienda_cds --collection lista_cds --type csv --headerline --drop /tmp/lista_inicial.csv
docker exec "$1" mongoimport  --db bd_carrito --collection carrito --ignoreBlanks --type csv  --headerline --drop /tmp/carrito_inicial.csv
docker exec "$1" mongoimport  --db compra_cds --collection compras --type csv  --headerline --drop /tmp/compras_inicial.csv
