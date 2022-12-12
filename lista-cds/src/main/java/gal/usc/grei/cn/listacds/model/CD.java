package gal.usc.grei.cn.listacds.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/*
  disc_id SERIAL PRIMARY KEY,
  disc_name VARCHAR(50) NOT NULL,
  disc_price FLOAT NOT NULL,
  disc_author VARCHAR(50) NOT NULL,
*/

@Document(collection = "lista_cds")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CD {

    @Id
    private String id;
    @NotEmpty(message = "El símbolo no puede ser vacío")
    private String name;
    @NotEmpty(message = "El símbolo no puede ser vacío")
    private String author;
    @NotNull(message = "El símbolo no puede ser vacío")
    private Double price;

    // ******************* Setters y Getters ******************* \\

    public String getId() {
        return id;
    }

    public CD setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CD setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CD setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public CD setPrice(Double price) {
        this.price = price;
        return this;
    }

    // ******************* Equals y HashCode ******************* \\

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CD CD = (CD) o;
        return id.equals(CD.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // ******************* toString ******************* \\

    @Override
    public String toString() {
        return "CDs{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
