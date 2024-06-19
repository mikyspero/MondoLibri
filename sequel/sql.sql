create database shopdb;

use shopdb;

CREATE TABLE role(
                     id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                     nome VARCHAR(20) NOT NULL
);

CREATE TABLE user (
                      id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                      username VARCHAR(20) NOT NULL UNIQUE,
                      password VARCHAR(20) NOT NULL,
                      email VARCHAR(30) NOT NULL UNIQUE,
                      address VARCHAR(30) NOT NULL,
                      id_role INT UNSIGNED,
                      FOREIGN KEY (id_role) REFERENCES role(id)
);

CREATE TABLE cart (
                      id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                      id_user INT UNSIGNED,
                      FOREIGN KEY (id_user) REFERENCES user(id)
);

CREATE TABLE product (
                         id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL UNIQUE,
                         price DECIMAL(10,2) NOT NULL,
                         description VARCHAR(255),
                         image BLOB,
                         quantity INT UNSIGNED NOT NULL
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



-- Populate the role table
INSERT INTO role (nome) VALUES ('Admin');
INSERT INTO role (nome) VALUES ('Customer');

-- Populate the user table
INSERT INTO user (username, password, email, address, id_role) VALUES ('admin', 'adminpass', 'admin@example.com', '123 Admin St', 1);
INSERT INTO user (username, password, email, address, id_role) VALUES ('john_doe', 'password123', 'john.doe@example.com', '456 Main St', 2);
INSERT INTO user (username, password, email, address, id_role) VALUES ('jane_doe', 'password456', 'jane.doe@example.com', '789 Main St', 2);

-- Populate the cart table
INSERT INTO cart (id_user) VALUES (2);
INSERT INTO cart (id_user) VALUES (3);

-- Populate the product table
INSERT INTO product (name, price, description, image, quantity) VALUES ('Laptop', 999.99, 'A high-performance laptop', NULL, 10);
INSERT INTO product (name, price, description, image, quantity) VALUES ('Smartphone', 499.99, 'A latest model smartphone', NULL, 20);
INSERT INTO product (name, price, description, image, quantity) VALUES ('Headphones', 199.99, 'Noise-cancelling headphones', NULL, 15);

-- Populate the purchase table
INSERT INTO purchase (purchase_date, id_user, id_product) VALUES ('2024-06-01', 2, 1);
INSERT INTO purchase (purchase_date, id_user, id_product) VALUES ('2024-06-05', 3, 2);

-- Populate the cart_product table
INSERT INTO cart_product (id_cart, id_product) VALUES (1, 1);
INSERT INTO cart_product (id_cart, id_product) VALUES (1, 3);
INSERT INTO cart_product (id_cart, id_product) VALUES (2, 2);

-- Visualizza il contenuto della tabella role
SELECT * FROM role;

-- Visualizza il contenuto della tabella user
SELECT * FROM user;

-- Visualizza il contenuto della tabella cart
SELECT * FROM cart;

-- Visualizza il contenuto della tabella product
SELECT * FROM product;

-- Visualizza il contenuto della tabella purchase
SELECT * FROM purchase;

-- Visualizza il contenuto della tabella cart_product
SELECT * FROM cart_product;





