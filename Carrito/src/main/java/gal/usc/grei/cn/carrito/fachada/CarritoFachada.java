package gal.usc.grei.cn.carrito.fachada;

import gal.usc.grei.cn.carrito.modelo.CD;
import gal.usc.grei.cn.carrito.repositorio.CarritoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoFachada {
    private final CarritoRepositorio cds;

    @Autowired
    public CarritoFachada(CarritoRepositorio cds) {
        this.cds = cds;
    }

    public CarritoRepositorio getCds() {
        return cds;
    }
    public Optional<CD> findByName(String name) {
        return cds.findByName(name);
    }

    public Optional<CD> findById(String id) {
        return cds.findById(id);
    }

    public Optional<List<CD>> findAll() {
        return Optional.of(cds.findAll());
    }

    public Optional<CD> add(CD cd) {

        // COMPROBAR SI EXISTE EN LA BASE DE DATOS DE MARTIN

        if(cds.findByName(cd.getName()).isPresent()) {
            // Si está presente añadir el parámetro cantidad al que ya está en la base de datos
            CD cdAux = cds.findByName(cd.getName()).get(); // Obtener el CD de la base de datos
            cdAux.setQuantity(cdAux.getQuantity() + cd.getQuantity()); // Añadir la cantidad del parámetro al que ya está en la base de datos
            cds.save(cdAux); // Guardar el CD en la base de datos
            return Optional.of(cdAux); // Devolver el CD de la base de datos
        }
        return Optional.ofNullable(cds.insert(cd)); // Si no está presente, insertar el CD en la base de datos
    }

    public boolean deleteByName(String name) {
        if(this.findByName(name).isEmpty()){
            return false;
        }
        else{
            cds.deleteByName(name);
            return true;
        }
    }

    public void deleteAll() {
        cds.deleteAll();
    }

}
