package gal.usc.grei.cn.compra_cd.controlador;

import gal.usc.grei.cn.compra_cd.fachada.ComprasFachada;
import gal.usc.grei.cn.compra_cd.modelo.Compra;

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

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
                            schema = @Schema(implementation = Compra.class)) }),
            @ApiResponse(responseCode = "404", description = "No existen compras de ese usuario",
                    content = @Content) })

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
                            schema = @Schema(implementation = Compra.class)) }),
            @ApiResponse(responseCode = "404", description = "No se han introducido bien los datos",
                    content = @Content) })

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Object> create(@Valid @RequestBody Compra Compra) {
        //Tratamos de crear la compra:
        Compra.setPrecioTotal(carritoPrecio()).setCarrito(carrito()).setFecha();
        Optional<Compra> inserted = compras.create(Compra);
        //Si se crea correctamente, devolvemos la información de la compra creada.
        return ResponseEntity.created(URI.create(
                        ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() +
                                "/compras/" + inserted.get().getId()))
                .body(inserted.get());
    }

    /**
     * Método: GET
     * Url para llegar: /compras
     * Objetivo: recuperar todas las compras.
     *
     * @param
     * @return todas las compras
     */
    @Operation(summary = "Obtener todas las compras de la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compras devueltas con éxito",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Compra.class)) })})

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Compra>> getAll() {
        return ResponseEntity.of(compras.findAll());
    }



    private String carrito(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://carrito:8080/carrito",
                        String.class);
        String carrito = response.getBody();

        restTemplate.delete("http://carrito:8080/carrito/delete");
        return  carrito;
    }
    private String carritoPrecio(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://carrito:8080/carrito/precio",
                        String.class);
        return response.getBody();
    }


}
