docker container run --name=mongo_carrito -p 27017:27017 -d mongo
docker cp ./carrito_inicial.csv mongo_carrito:/tmp/carrito_inicial.csv
docker exec mongo_carrito mongoimport --type csv -d bd_carrito -c carrito --headerline --drop /tmp/carrito_inicial.csv
