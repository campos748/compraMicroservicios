package gal.usc.grei.cn.carrito.modelo;




import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection = "carrito")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CD {
    @Id
    private String id;
    private String name;
    private String author;
    private double price;
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CD{" +
                "id=" + id +
                ", nombre='" + name + '\'' +
                ", autor='" + author + '\'' +
                ", precio=" + price +
                ", cantidad=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CD cd = (CD) o;
        return id.equals(cd.id) && name.equals(cd.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
