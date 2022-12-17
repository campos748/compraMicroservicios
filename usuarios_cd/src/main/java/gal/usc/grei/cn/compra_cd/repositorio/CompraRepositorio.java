package gal.usc.grei.cn.compra_cd.repositorio;
import gal.usc.grei.cn.compra_cd.modelo.Compra;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompraRepositorio extends MongoRepository<Compra, String> {
    @Query(value = "{'usuario': '?0'}")
    public Optional<List<Compra>> findByUsuario(String usuario);

}
