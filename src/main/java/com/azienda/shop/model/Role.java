package com.azienda.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity  // Indica che questa classe è una entità JPA e verrà mappata a una tabella nel database
public class Role {
    @Id  // Specifica che questo campo è la chiave primaria della tabella
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica che il valore dell'ID è generato automaticamente dal database usando una strategia di incremento
    private Integer id;

    private String nome;  // Campo per memorizzare il nome del ruolo

    @OneToMany(mappedBy = "role")  // Definisce una relazione uno-a-molti con la classe `User`. Il campo `role` nella classe `User` è il proprietario della relazione
    private List<User> users;  // Lista di utenti associati al ruolo

    // Costruttore vuoto richiesto da Hibernate
    public Role() {
    }

    public Role(String nome) {
        this.nome = nome;
    }

    // Costruttore che accetta una lista di utenti e un nome
    public Role(List<User> users, String nome) {
        this.users = users;
        this.nome = nome;
    }

    // Getter e setter per accedere e modificare i campi privati

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", users=" + users +
                '}';
    }
}
