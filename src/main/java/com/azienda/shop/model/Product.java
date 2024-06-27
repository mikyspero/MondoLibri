package com.azienda.shop.model;

import javax.persistence.*;
import java.util.List;
import java.sql.Blob;

@Entity  // Indica che questa classe è una entità JPA e verrà mappata a una tabella nel database
public class Product {
    @Id  // Specifica che questo campo è la chiave primaria della tabella
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica che il valore dell'ID è generato automaticamente dal database usando una strategia di incremento
    private Integer id;

    private String name;  // Campo per memorizzare il nome del prodotto
    private double price;  // Campo per memorizzare il prezzo del prodotto
    private int quantity;  // Campo per memorizzare la quantità disponibile del prodotto
    private String description;  // Campo per memorizzare la descrizione del prodotto
    private Blob image;
    private String language;  // Campo per memorizzare la lingua del prodotto

    @ManyToOne  // Definisce una relazione molti-a-uno con la classe `Author`
    @JoinColumn(name = "id_author")  // Specifica la colonna di join per la relazione molti-a-uno
    private Author author;

    @ManyToOne  // Definisce una relazione molti-a-uno con la classe `Genre`
    @JoinColumn(name = "id_genre")  // Specifica la colonna di join per la relazione molti-a-uno
    private Genre genre;

    @ManyToMany(mappedBy = "products")  // Definisce una relazione molti-a-molti con la classe `Cart`
    private List<Cart> carts;

    @OneToMany(mappedBy = "product")  // Definisce una relazione uno-a-molti con la classe `Purchase`
    private List<Purchase> purchases;

    // Costruttore vuoto richiesto da Hibernate
    public Product() {
    }

    // Costruttore che accetta tutti i campi tranne l'immagine
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

    // Costruttore che accetta tutti i campi inclusa l'immagine
    public Product(List<Purchase> purchases, List<Cart> carts, Genre genre, Author author, String language, Blob image, String description, int quantity, double price, String name) {
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

    // Costruttore che accetta solo i campi principali del prodotto
    public Product(String name, Double price, int quantity, String description, String language) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.language = language;
    }

    // Getter e setter per accedere e modificare i campi privati
    public Integer getId() {
        return id;
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

    public Blob getImage() {
        return image;
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

    public void setImage(Blob image) {
        this.image = image;
    }
}
