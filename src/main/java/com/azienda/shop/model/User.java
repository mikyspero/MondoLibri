package com.azienda.shop.model;

import com.azienda.shop.utils.PasswordHasher;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

// CLASSE USER
@Entity  // Indica che questa classe è una entità JPA e verrà mappata a una tabella nel database
public class User {

    @Id  // Specifica che questo campo è la chiave primaria della tabella
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica che il valore dell'ID è generato automaticamente dal database usando una strategia di incremento
    private Integer id;

    @Column(unique = true, nullable = false)  // Indica che il campo `username` è unico e non può essere nullo
    private String username;

    @Column(nullable = false)  // Indica che il campo `password` non può essere nullo
    private String password;

    @Column(unique = true, nullable = false)  // Indica che il campo `email` è unico e non può essere nullo
    private String email;

    @Column(nullable = false)  // Indica che il campo `address` non può essere nullo
    private String address;

    @OneToMany(mappedBy = "user")  // Definisce una relazione uno-a-molti con la classe `Purchase`. Il campo `user` nella classe `Purchase` è il proprietario della relazione
    private List<Purchase> purchases;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)  // Definisce una relazione uno-a-uno con la classe `Cart`. Il campo `user` nella classe `Cart` è il proprietario della relazione. La cascata ALL significa che tutte le operazioni di persistenza saranno propagate dal `User` al `Cart`
    private Cart cart;

    @ManyToOne  // Definisce una relazione molti-a-uno con la classe `Role`
    @JoinColumn(name = "id_role")  // Specifica la colonna di join per la relazione molti-a-uno
    private Role role;

    // Costruttore per il PasswordHasher DO NOT DELETE
    public User(String address, String email, String password, String username) {
        this.address = address;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Costruttore che accetta tutti i campi della classe
    public User(Role role, Cart cart, List<Purchase> purchases, String address, String email, String password, String username) {
        this.role = role;
        this.cart = cart;
        this.purchases = purchases;
        this.address = address;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    // Costruttore vuoto richiesto da Hibernate
    public User() {
    }

    // Metodo statico per creare un'istanza di User con la password hashata
    public static User createInstance(String address, String email, String password, String username) throws NoSuchAlgorithmException {
        return new User(address, email, PasswordHasher.hashPassword(password), username);
    }

    public void isAdmin(){
        if (!this.getRole().equals("Admin")){
            throw new SecurityException("You do not have permission to access this resource");
        }
    }


    // Getter e setter per accedere e modificare i campi privati


    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
