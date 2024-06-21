package com.azienda.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity  // Indica che questa classe è una entità JPA e verrà mappata a una tabella nel database
public class Cart {

    @Id  // Specifica che questo campo è la chiave primaria della tabella
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica che il valore dell'ID è generato automaticamente dal database usando una strategia di incremento
    private Integer id;

    @OneToOne  // Definisce una relazione uno-a-uno con la classe User
    @JoinColumn(name = "id_user")  // Specifica la colonna di join per la relazione uno-a-uno
    private User user;

    @ManyToMany  // Definisce una relazione molti-a-molti con la classe Product
    @JoinTable(  // Definisce la tabella di join per la relazione molti-a-molti
            name = "cart_product",  // Nome della tabella di join
            joinColumns = @JoinColumn(name = "id_cart"),  // Colonna di join che fa riferimento alla classe Cart
            inverseJoinColumns = @JoinColumn(name = "id_product"))  // Colonna di join che fa riferimento alla classe Product
    private List<Product> products;  // Lista di prodotti associati al carrello

    // Costruttore vuoto richiesto da Hibernate
    public Cart() {
    }

    // Costruttore che accetta una lista di prodotti e un utente
    public Cart(List<Product> products, User user) {
        this.products = products;
        this.user = user;
    }

    // Getter e setter per accedere e modificare i campi privati

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
