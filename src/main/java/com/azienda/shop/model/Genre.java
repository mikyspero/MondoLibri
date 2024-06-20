package com.azienda.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Product> products;

    //COSTRUTTORE VUOTO PER HIBERNATE
    public Genre() {
    }

    public Genre(List<Product> products, String name) {
        this.products = products;
        this.name = name;
    }

    // Getters and Setters

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
