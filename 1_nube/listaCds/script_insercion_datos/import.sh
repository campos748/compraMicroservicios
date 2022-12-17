#!/bin/bash
mongoimport --host tienda_cds:27017 --db tienda_cds --collection lista_cds --type csv --headerline --drop /mongo-seed/lista_inicial.csv
mongoimport --host tienda_cds:27017 --db bd_carrito --collection carrito --type csv  --headerline --drop /mongo-seed/carrito_inicial.csv
mongoimport --host tienda_cds:27017 --db compra_cds --collection compras --type csv  --headerline --drop /mongo-seed/compras_inicial.csv
