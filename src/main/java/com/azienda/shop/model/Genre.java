package com.azienda.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity  // Indica che questa classe è una entità JPA e verrà mappata a una tabella nel database
public class Genre {

    @Id  // Specifica che questo campo è la chiave primaria della tabella
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica che il valore dell'ID è generato automaticamente dal database usando una strategia di incremento
    private Integer id;

    private String name;  // Campo per memorizzare il nome del genere

    @OneToMany(mappedBy = "genre")  // Relazione uno-a-molti con la classe Product, il campo 'genre' nella classe Product è il proprietario della relazione
    private List<Product> products;  // Lista di prodotti associati al genere

    // Costruttore vuoto richiesto da Hibernate
    public Genre() {
    }

    // Costruttore che accetta una lista di prodotti e un nome
    public Genre(List<Product> products, String name) {
        this.products = products;
        this.name = name;
    }

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
