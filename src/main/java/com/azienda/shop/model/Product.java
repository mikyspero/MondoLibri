package com.azienda.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private double price;
    private int quantity;
    private String description;
    private byte[] image;
    private String language;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;

    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;


    //COSTRUTTORE VUOTO
    public Product() {
    }

    public Product(List<Purchase> purchases, List<Cart> carts, Genre genre, Author author, String language, String description, int quantity, double price, String name) {
        this.purchases = purchases;
        this.carts = carts;
        this.genre = genre;
        this.author = author;
        this.language = language;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public Product(List<Purchase> purchases, List<Cart> carts, Genre genre, Author author, String language, byte[] image, String description, int quantity, double price, String name) {
        this.purchases = purchases;
        this.carts = carts;
        this.genre = genre;
        this.author = author;
        this.language = language;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public Product(String name, Double price, int quantity, String description, String language) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.language = language;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
