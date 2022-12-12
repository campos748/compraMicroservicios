package gal.usc.grei.cn.carrito.controlador;

import gal.usc.grei.cn.carrito.modelo.CD;
import gal.usc.grei.cn.carrito.fachada.CarritoFachada;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/carrito" , method={RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST})

public class CarritoControlador {
    private final CarritoFachada cds;

    @Autowired
    public CarritoControlador(CarritoFachada cds) {
        this.cds = cds;
    }

    /**
     * Método: GET
     * Url para llegar: /precios/{name}
     * Objetivo: recuperar el cd en base a su nombre.
     *
     * @param name nombre del cd
     * @return el cd con ese nombre
     */
    @Operation(summary = "Obtener un cd a partir de su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CD encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) }),
            @ApiResponse(responseCode = "404", description = "CD no encontrado",
                    content = @Content) })
    @GetMapping(path = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CD> createByName(@PathVariable("name") String name) {
        return ResponseEntity.of(cds.findByName(name));
    }

    /**
     * Método: GET
     * Url para llegar: /carrito
     * Objetivo: recuperar el carrito entero.
     *
     * @param
     * @return todos los cds del carrito
     */
    @Operation(summary = "Obtener el carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito devuelto con éxito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) })})
    @GetMapping(
            path = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE
    )
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CD>> createCart() {
        return ResponseEntity.of(cds.findAll());
    }


    /**
     * Método: POST
     * Url para llegar: /carrito
     * Objetivo: recuperar el carrito entero.
     *
     * @param  cd disco que se quiere añadir
     * @return respuesta cd añadido
     */
    @Operation(summary = "Añadir cd al carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CD añadido al carrito correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) }),
            @ApiResponse(responseCode = "400", description = "No se introdujeron bien los datos",
                    content = @Content) })
    @GetMapping(
            path = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> create(@Valid @RequestBody CD cd) {

        //Tratamos de crear la compra:
        Optional<CD> inserted = cds.add(cd);
        //Si se crea correctamente, devolvemos la información de la compra creada.
        return ResponseEntity.created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() +
                                "/carrito" ))
                .body(inserted);

    }

    /**
     * Método: DELETE
     * Url para llegar: /carrito/delete/{nombre}
     * Objetivo: eliminar el cd del carrito con el nombre dado.
     *
     * @param nombre nombre del cd a eliminar
     **
    @Operation(summary = "Eliminar un CD del carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " true:CD eliminado correctamente | false:no existe el CD ",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) })}) */

    @Operation(summary = "Borrar un CD en función de su nombre del carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CD borrado correctamente del carrito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) })})
    @DeleteMapping(
            path = "delete/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE
    )
    boolean deleteByName(@PathVariable("nombre") String nombre) {
        return cds.deleteByName(nombre);
    }

    @Operation(summary = "Borrar todos los CDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carrito borrado correctamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CD.class)) })})
    @GetMapping(
            path = "{nombre}", produces = MediaType.APPLICATION_JSON_VALUE
    )
    @DeleteMapping(
            path = "delete", produces = MediaType.APPLICATION_JSON_VALUE
    )
    void deleteByName() {
        cds.deleteAll();
    }

}
