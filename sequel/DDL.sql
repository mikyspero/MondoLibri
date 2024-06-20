CREATE DATABASE shopdb;

USE shopdb;

CREATE TABLE role (
                      id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                      nome VARCHAR(20) NOT NULL
);

CREATE TABLE user (
                      id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(20) NOT NULL UNIQUE,
                      password VARCHAR(100) NOT NULL,
                      email VARCHAR(50) NOT NULL UNIQUE,
                      address VARCHAR(255) NOT NULL,
                      id_role INT UNSIGNED,
                      FOREIGN KEY (id_role) REFERENCES role(id)
);

CREATE TABLE cart (
                      id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                      id_user INT UNSIGNED,
                      FOREIGN KEY (id_user) REFERENCES user(id)
);

CREATE TABLE author (
                        id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL
);

CREATE TABLE genre (
                       id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL
);

CREATE TABLE product (
                         id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL UNIQUE,
                         price DECIMAL(10,2) NOT NULL,
                         description VARCHAR(255),
                         image BLOB,
                         quantity INT UNSIGNED NOT NULL,
                         language VARCHAR(20) NOT NULL,
                         author_id INT UNSIGNED,
                         genre_id INT UNSIGNED,
                         FOREIGN KEY (author_id) REFERENCES author(id),
                         FOREIGN KEY (genre_id) REFERENCES genre(id)
);

CREATE TABLE purchase (
                          id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                          purchase_date DATE NOT NULL,
                          id_user INT UNSIGNED,
                          id_product INT UNSIGNED,
                          FOREIGN KEY (id_user) REFERENCES user(id),
                          FOREIGN KEY (id_product) REFERENCES product(id)
);

CREATE TABLE cart_product (
                              id_cart INT UNSIGNED,
                              id_product INT UNSIGNED,
                              PRIMARY KEY (id_cart, id_product),
                              FOREIGN KEY (id_product) REFERENCES product(id),
                              FOREIGN KEY (id_cart) REFERENCES cart(id)
);

-- Visualizza tutte le tabelle
SHOW TABLES;

-- Visualizza i dati dalla tabella role
SELECT * FROM role;

-- Visualizza i dati dalla tabella user
SELECT * FROM user;

-- Visualizza i dati dalla tabella cart
SELECT * FROM cart;

-- Visualizza i dati dalla tabella author
SELECT * FROM author;

-- Visualizza i dati dalla tabella genre
SELECT * FROM genre;

-- Visualizza i dati dalla tabella product
SELECT * FROM product;

-- Visualizza i dati dalla tabella purchase
SELECT * FROM purchase;

-- Visualizza i dati dalla tabella cart_product
SELECT * FROM cart_product;

