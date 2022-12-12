package gal.usc.grei.cn.listacds.controller;

import gal.usc.grei.cn.listacds.fachada.CDFachada;
import gal.usc.grei.cn.listacds.model.CD;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/listacds")
public class CDControlador {

    // Referencia a la clase CDFachada
    private final CDFachada cds;

    /**
     * Constructor de la clase
     * @param cds Referencia al CDFachada
     */
    @Autowired
    public CDControlador(CDFachada cds) {
        this.cds = cds;
    }

    /**
     * Método: GET *
     * Url para llegar: /listacds/{nombre}
     * Objetivo: recuperar el cd en base a su nombre.
     *
     * @param nombre El símbolo de la acción a recuperar
     * @return Si el simbolo es válido, los datos de la acción.
     **/
    @Operation(summary = "Obtener un CD a partir de su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CD encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) }),
            @ApiResponse(responseCode = "404", description = "CD no encontrado",
                    content = @Content) })
    @GetMapping(
            path = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<CD> get(@PathVariable("nombre") String nombre) {
        return ResponseEntity.of(cds.getByName(nombre));
    }

    /**
     * Método: GET *
     * Url para llegar: /listacds/all
     * Objetivo: recuperar todos los cds.
     *
     * @return todos los cds.
     **/
    @Operation(summary = "Obtener la lista de todos los CDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) })})
    @GetMapping(
            path = "all", produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<CD> getAll() {
        return cds.getAll();
    }

    /**
     * Método: POST
     * Url para llegar: /listacds
     * Objetivo: insertar el cd que se facilita como parámetro.
     *
     * @param cd los datos del cd a insertar
     * @return Si la inserción se ha podido hacer, el nuevo cd y la url para acceder a el.
     */
    @Operation(summary = "Añadir un CD a la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CD añadido correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) }),
            @ApiResponse(responseCode = "400", description = "No se han introducido correctamente los campos",
                    content = { @Content})
    })
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> create(@Valid @RequestBody CD cd) {
        //Tratamos de crear la compra:
        Optional<CD> inserted = cds.add_cd(cd);
        //Si se crea correctamente, devolvemos la información de la compra creada.
        return ResponseEntity.created(URI.create(
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() +
                        "/listacds/" + inserted.get().getId()))
                .body(inserted.get());
    }


    /**
     * Método: DELETE
     * Url para llegar: /listacds/del/{nombre}
     * Objetivo: eliminar el cd con el nombre dado.
     *
     * @param nombre nombre del cd a eliminar
     **/
    @Operation(summary = "Eliminar un CD de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " true:CD eliminado correctamente | false:no existe el CD ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) })})
    @DeleteMapping(
            path = "delete/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE
    )
    boolean deleteByName(@PathVariable("nombre") String nombre) {
        return cds.deleteByName(nombre);
    }



}
