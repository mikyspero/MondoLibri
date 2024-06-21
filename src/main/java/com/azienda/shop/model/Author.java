package com.azienda.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity  // Indica che questa classe è una entità JPA
public class Author {

    @Id  // Specifica che questo campo è la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica che l'ID è generato automaticamente dal database con la strategia di incremento
    private Integer id;

    private String name;  // Campo per memorizzare il nome dell'autore

    @OneToMany(mappedBy = "author")  // Relazione one-to-many con la classe Product, il campo 'author' nella classe Product è il proprietario della relazione
    private List<Product> products;  // Lista di prodotti associati all'autore

    // Getter e setter per accedere e modificare i campi privati

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
