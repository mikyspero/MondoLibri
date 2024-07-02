package com.azienda.shop.model;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

/**
 * Entity class representing a product.
 * Each Product belongs to a specific Genre and Author,
 * can be associated with multiple Carts and Purchases.
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;  // Name of the product
    private Double price;  // Price of the product
    private int quantity;  // Quantity of the product
    private String description;  // Description of the product
    private String language;  // Language of the product
    private Blob image;  // Image of the product

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;  // Author of the product

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;  // Genre of the product

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;  // Carts containing this product

    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;  // Purchases of this product

    /**
     * Default constructor required by Hibernate.
     * Initializes an empty instance of Product.
     */
    public Product() {
    }

    /**
     * Constructor that accepts all fields except image.
     * @param purchases List of purchases associated with the product.
     * @param carts List of carts containing the product.
     * @param genre Genre of the product.
     * @param author Author of the product.
     * @param language Language of the product.
     * @param description Description of the product.
     * @param quantity Quantity of the product.
     * @param price Price of the product.
     * @param name Name of the product.
     */
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

    /**
     * Constructor that accepts all fields including image.
     * @param purchases List of purchases associated with the product.
     * @param carts List of carts containing the product.
     * @param genre Genre of the product.
     * @param author Author of the product.
     * @param language Language of the product.
     * @param image Image of the product.
     * @param description Description of the product.
     * @param quantity Quantity of the product.
     * @param price Price of the product.
     * @param name Name of the product.
     */
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

    /**
     * Constructor that accepts only the main fields of the product.
     * @param name Name of the product.
     * @param price Price of the product.
     * @param quantity Quantity of the product.
     * @param description Description of the product.
     * @param language Language of the product.
     */
    public Product(String name, Double price, int quantity, String description, String language) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.language = language;
    }

    public Product(String name, Author author, Genre genere, Double price, int quantity, String description, String language) {
        this.name = name;
        this.author = author;
        this.genre = genere;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.language = language;
    }

    /**
     * Retrieves the ID of the product.
     * @return ID of the product.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Retrieves the list of purchases associated with the product.
     * @return List of purchases associated with the product.
     */
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * Sets the list of purchases associated with the product.
     * @param purchases List of purchases to set for the product.
     */
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    /**
     * Retrieves the list of carts containing the product.
     * @return List of carts containing the product.
     */
    public List<Cart> getCarts() {
        return carts;
    }

    /**
     * Sets the list of carts containing the product.
     * @param carts List of carts to set for the product.
     */
    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    /**
     * Retrieves the genre of the product.
     * @return Genre of the product.
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Sets the genre of the product.
     * @param genre Genre to set for the product.
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Retrieves the author of the product.
     * @return Author of the product.
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets the author of the product.
     * @param author Author to set for the product.
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Retrieves the language of the product.
     * @return Language of the product.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language of the product.
     * @param language Language to set for the product.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Retrieves the image of the product.
     * @return Image of the product.
     */
    public Blob getImage() {
        return image;
    }

    /**
     * Sets the image of the product.
     * @param image Image to set for the product.
     */
    public void setImage(Blob image) {
        this.image = image;
    }

    /**
     * Retrieves the description of the product.
     * @return Description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     * @param description Description to set for the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the quantity of the product.
     * @return Quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     * @param quantity Quantity to set for the product.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the price of the product.
     * @return Price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * @param price Price to set for the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the name of the product.
     * @return Name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * @param name Name to set for the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", price=" + price +
                '}';
    }
}

