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
    @Operation(summary = "Obtención de las compras de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compras del usuario encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) }),
            @ApiResponse(responseCode = "404", description = "No existen compras de ese usuario",
                    content = @Content) })
    @GetMapping(
            path = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE
    )
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
    @Operation(summary = "Almacenar una compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha registrado la compra correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) }),
            @ApiResponse(responseCode = "404", description = "No se han introducido bien los datos",
                    content = @Content) })
    @GetMapping(
            path = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE
    )
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
