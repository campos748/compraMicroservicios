package gal.usc.grei.cn.compra_cd.controlador;

import gal.usc.grei.cn.compra_cd.fachada.ComprasFachada;
import gal.usc.grei.cn.compra_cd.modelo.Compra;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class ComprasControlador {

    // Referencia a la clase PrecioFachada
    private final ComprasFachada compras;

    /**
     * Constructor de la clase
     *
     * @param compras Instancia de la clase PrecioFachada
     **/
    @Autowired
    public ComprasControlador(ComprasFachada compras) {
        this.compras = compras;
    }

    /**
     * Método: GET *
     * Url para llegar: /compra
     * Objetivo: recuperar la acción en base a su símbolo.
     *
     * @param simbolo El símbolo de la acción a recuperar
     * @return Si el simbolo es válido, los datos de la acción.
     **/
    @GetMapping(
            path = "{simbolo}", produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<Compra>> get(@PathVariable("simbolo") String simbolo) {

        return ResponseEntity.of(compras.get(simbolo));
    }


    /**
     * Método: POST
     * Url para llegar: /compras
     * Objetivo: insertar la compra que se facilita como parámetro.
     *
     * @param Compra los datos de la compra a insertar
     * @return Si la inserción se ha podido hacer, la nueva compra y la url para acceder a ella.
     */
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> create(@Valid @RequestBody Compra Compra) {
        //Tratamos de crear la compra:
        Optional<Compra> inserted = compras.create(Compra);
        //Si se crea correctamente, devolvemos la información de la compra creada.
        return ResponseEntity.created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() +
                                "/compras/" + inserted.get().getId()))
                .body(inserted.get());
    }



}
