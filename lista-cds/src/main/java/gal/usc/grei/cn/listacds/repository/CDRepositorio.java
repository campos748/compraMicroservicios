package gal.usc.grei.cn.listacds.repository;

import gal.usc.grei.cn.listacds.model.CD;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CDRepositorio extends MongoRepository<CD, String> {

    @Query(value = "{'name': '?0'}")
    public Optional<CD> findCdByName(String name);

    @DeleteQuery(value = "{'name': '?0'}")
    void deleteByName(String nombre);
}
