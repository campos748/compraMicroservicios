package gal.usc.grei.cn.listacds.fachada;

import gal.usc.grei.cn.listacds.model.CD;
import gal.usc.grei.cn.listacds.repository.CDRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CDFachada {
    private final CDRepositorio cds;

    /**
     * Constructor de la clase
     * @param cds Referencia al CDRepositorio
     */
    @Autowired
    public CDFachada(CDRepositorio cds) {
        this.cds = cds;
    }

    /**
     *  Método que permite recuperar el cd por su nombre.
     *
     *  @param name El nombre del cd a recuperar
     *  @return Los datos del cd con los parámetros facilitados (si se encuentra).
     */
    public Optional<CD> getByName(String name) {
        // Se recupera la compra por el id
        return cds.findCdByName(name);
    }

    /**
     *  Método que permite recuperar todos los cds.
     *
     *  @return todos los cds.
     */
    public List<CD> getAll() {
        return cds.findAll();
    }

    /** Método que permite insertar un nuevo cd en la base de datos.
     *  @param cd Los datos del cd a insertar.
     *  @return Los datos del cd una vez insertados, incluyendo el id.
     *  @throws ResponseStatusException Excepción lanzada en caso de que se facilite alguna
     *  información incorrecta.
     * */
    public Optional<CD> add_cd(CD cd) {
        //Comprobamos que la película haya llegado sin un id:
        if(cd.getId() == null || cd.getId().isEmpty()) {
            //Si es así, se devuelve un optional con los datos de la película insertada.
            return Optional.of(cds.insert(cd));
        }
        return Optional.empty();
    }


    /** Método que permite eliminar un cd por su nombre de la base de datos.
     *
     *  @param nombre nombre del cd a eliminar.
     *  @return true si se eliina correctamente y false si no se encuentra.
     *
     * */
    public boolean deleteByName(String nombre) {

        if(this.getByName(nombre).isEmpty()){
            return false;
        }
        else{
            cds.deleteByName(nombre);
            return true;
        }

    }
}
