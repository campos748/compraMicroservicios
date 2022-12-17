package gal.usc.grei.cn.compra_cd.fachada;

import gal.usc.grei.cn.compra_cd.modelo.Compra;
import gal.usc.grei.cn.compra_cd.repositorio.CompraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ComprasFachada {

    private final CompraRepositorio compras;

    /**
     * Constructor de la clase
     * @param compras Referencia al CompraRepositorio
     */
    @Autowired
    public ComprasFachada(CompraRepositorio compras) {
        this.compras = compras;
    }

    public Optional<List<Compra>> get(String usuario) {
        // Se recupera la compra por el usuario
        return compras.findByUsuario(usuario);
    }

    /** Método que permite insertar una nueva compra en la base de datos.
     *  @param Compra Los datos de la compra a insertar.
     *  @return Los datos de la compra una vez insertados, incluyendo el id.
     *  @throws ResponseStatusException Excepción lanzada en caso de que se facilite alguna
     *  información incorrecta.
     * */
    public Optional<Compra> create(Compra Compra) {
        //Comprobamos que la película haya llegado sin un id:
        if(Compra.getId() == null || Compra.getId().isEmpty()) {
            //Si es así, se devuelve un optional con los datos de la película insertada.
            return Optional.of(compras.insert(Compra));
        }
        return Optional.empty();
    }

    public Optional<List<Compra>> findAll() {
        return Optional.of(compras.findAll());
    }
}
