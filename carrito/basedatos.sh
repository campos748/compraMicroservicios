docker container run --name=tienda_cds -p 27017:27017 -d mongo
docker cp ./carrito_inicial.csv tienda_cds:/tmp/carrito_inicial.csv
docker exec mongo_carrito mongoimport --type csv -d bd_carrito -c carrito --headerline --drop /mongo-seed/carrito_inicial.csv
