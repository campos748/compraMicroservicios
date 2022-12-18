package gal.usc.grei.cn.compra_cd.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Objects;

@Document(collection = "compras")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Compra {

    @Id
    private String id;
    @NotEmpty(message = "El usuario no debe estar vacio")
    private String usuario;

    private String carrito;

    private String fecha;
    private String precioTotal;




    // ******************* Setters y Getters ******************* \\

    public String getId() {
        return id;
    }

    public Compra setId(String id) {
        this.id = id;
        return this;
    }

    public String getPrecioTotal() {
        return precioTotal;
    }

    public Compra setPrecioTotal(String precioTotal) {
        this.precioTotal = precioTotal;
        return this;
    }
    public String getUsuario() {
        return usuario;
    }

    public Compra setUsuario(String usuario) {
        this.usuario= usuario;
        return this;
    }
    public String getCarrito() {
        return carrito;
    }

    public Compra setCarrito(String carrito) {
        this.carrito = carrito;
        return this;
    }
    public String getFecha() {
        return fecha;
    }

    public Compra setFecha() {
        Date d= new Date();
        this.fecha=d.toString();
        return this;
    }



    // ******************* Equals y HashCode ******************* \\

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra Compra = (Compra) o;
        return id.equals(Compra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ******************* toString ******************* \\


    @Override
    public String toString() {
        return "Compra{" +
                "id='" + id + '\'' +
                ", usuario='" + usuario + '\'' +
                ", carrito='" + carrito + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
