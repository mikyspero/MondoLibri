package com.azienda.shop.model;

import javax.persistence.*;
import java.util.Date;

@Entity  // Indica che questa classe è una entità JPA e verrà mappata a una tabella nel database
public class Purchase {
    @Id  // Specifica che questo campo è la chiave primaria della tabella
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica che il valore dell'ID è generato automaticamente dal database usando una strategia di incremento
    private Integer id;

    @ManyToOne  // Definisce una relazione molti-a-uno con la classe `User`
    @JoinColumn(name = "id_user")  // Specifica la colonna di join per la relazione molti-a-uno
    private User user;

    @ManyToOne  // Definisce una relazione molti-a-uno con la classe `Product`
    @JoinColumn(name = "id_product")  // Specifica la colonna di join per la relazione molti-a-uno
    private Product product;

    @Temporal(TemporalType.DATE)  // Specifica che il campo `purchaseDate` deve essere trattato come una data
    private Date purchaseDate;

    // Costruttore vuoto richiesto da Hibernate
    public Purchase() {
    }

    // Costruttore che accetta tutti i campi della classe
    public Purchase(Date purchaseDate, Product product, User user) {
        this.purchaseDate = purchaseDate;
        this.product = product;
        this.user = user;
    }

    // Getter e setter per accedere e modificare i campi privati

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

