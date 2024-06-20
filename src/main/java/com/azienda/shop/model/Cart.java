package com.azienda.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "id_cart"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    private List<Product> products;

    //COSTRUTTORE VUOTO PER HIBERNATE
    public Cart() {
    }

    public Cart(List<Product> products, User user) {
        this.products = products;
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
