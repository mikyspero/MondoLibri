package com.azienda.shop.model;

import com.azienda.shop.utils.PasswordHasher;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
public class User
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique=true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String address;

    public User() {
    }

    public User(String address, String email, String password, String username) {
        this.address = address;
        this.email = email;
        this.password = password;
        this.username = username;
    }
    
    
    
    //method needs a little dixing to better work with hibernate
    public static User createInstance(String address, String email, String password, String username) throws NoSuchAlgorithmException {
            return new User(address, email, PasswordHasher.hashPassword(password), username);
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
