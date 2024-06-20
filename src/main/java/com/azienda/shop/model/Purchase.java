package com.azienda.shop.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;


    @Temporal(TemporalType.DATE)
    private Date purchaseDate;


    public Purchase() {
    }

    public Purchase(Date purchaseDate, Product product, User user) {
        this.purchaseDate = purchaseDate;
        this.product = product;
        this.user = user;
    }

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
