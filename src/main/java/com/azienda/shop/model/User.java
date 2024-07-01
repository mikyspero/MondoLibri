package com.azienda.shop.model;

import com.azienda.shop.utils.PasswordHasher;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Entity class representing a user in the system.
 * Each User can have a role, a cart, and multiple purchases associated with them.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    /**
     * Constructor that accepts address, email, password, and username.
     * Used internally for creating a new User instance with a hashed password.
     * @param address Address of the user.
     * @param email Email of the user.
     * @param password Password of the user (hashed using PasswordHasher).
     * @param username Username of the user.
     */
    public User(String address, String email, String password, String username) {
        this.address = address;
        this.email = email;
        this.password = password;
        this.username = username;
    }


    /**
     * Constructor that accepts only username and password.
     * Used internally for authentication purposes.
     * @param username Username of the user.
     * @param password Password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor that accepts all fields of the User class.
     * @param role Role of the user.
     * @param cart Cart associated with the user.
     * @param purchases List of purchases made by the user.
     * @param address Address of the user.
     * @param email Email of the user.
     * @param password Password of the user.
     * @param username Username of the user.
     */
    public User(Role role, Cart cart, List<Purchase> purchases, String address, String email, String password, String username) {
        this.role = role;
        this.cart = cart;
        this.purchases = purchases;
        this.address = address;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    /**
     * Default constructor required by Hibernate.
     * Initializes an empty instance of User.
     */
    public User() {
    }

    public User(Role role, String address, String email, String password, String username) {
        this.role = role;
        this.address = address;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(Role role, Cart cart, String address, String email, String password, String username) {
        this.role = role;
        this.cart = cart;
        this.address = address;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    /**
     * Static method to create a new User instance with a hashed password.
     * @param address Address of the user.
     * @param email Email of the user.
     * @param password Plain password of the user.
     * @param username Username of the user.
     * @return User instance with hashed password.
     * @throws NoSuchAlgorithmException If the hashing algorithm is not available.
     */
    public static User createInstance(Role role,String username, String password, String email, String address) throws NoSuchAlgorithmException {
        return new User(role,address, email, PasswordHasher.hashPassword(password), username);
    }

    /**
     * Method to check if the user is an admin.
     * Throws SecurityException if the user does not have admin role.
     */
    public void isAdmin(){
        if (!this.getRole().getNome().equals("admin")){
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
