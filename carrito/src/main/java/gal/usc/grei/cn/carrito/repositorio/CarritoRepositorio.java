package gal.usc.grei.cn.carrito.repositorio;

import gal.usc.grei.cn.carrito.modelo.CD;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface CarritoRepositorio extends MongoRepository<CD, String> {

    // Funcion para obtener disco dado su nombre
    @Query("{ 'name' : ?0 }")
    Optional<CD> findByName(String name);

    // Funcion para obtener todos los discos del carrito
    //ArrayList<CD> findAll();

    // Funcion para borrar un disco del carrito
    @DeleteQuery("{ 'name' : ?0 }")
    void deleteByName(String name);



}
