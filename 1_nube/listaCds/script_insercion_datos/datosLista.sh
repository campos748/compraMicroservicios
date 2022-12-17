#! /bin/bash
docker cp lista_inicial.csv tienda_cds:/tmp/lista_inicial.csv

docker exec tienda_cds mongoimport --type csv -d tienda_cds -c lista_cds --headerline --drop /tmp/lista_inicial.csv