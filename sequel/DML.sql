
USE shopdb;
-- Popola la tabella role
INSERT INTO role (nome) VALUES ('Admin');
INSERT INTO role (nome) VALUES ('Customer');

-- Popola la tabella user
INSERT INTO user (username, password, email, address, id_role)
VALUES
    ('admin', 'adminpass', 'admin@example.com', '123 Admin St', 1),
    ('john_doe', 'password123', 'john.doe@example.com', '456 Main St', 2),
    ('jane_doe', 'password456', 'jane.doe@example.com', '789 Main St', 2);

-- Popola la tabella cart
INSERT INTO cart (id_user) VALUES (2);
INSERT INTO cart (id_user) VALUES (3);

-- Popola la tabella author
INSERT INTO author (name) VALUES ('J.K. Rowling');
INSERT INTO author (name) VALUES ('George R.R. Martin');
INSERT INTO author (name) VALUES ('F. Scott Fitzgerald');

-- Popola la tabella genre
INSERT INTO genre (name) VALUES ('Fantasy');
INSERT INTO genre (name) VALUES ('Science Fiction');

-- Popola la tabella product con i collegamenti a author e genre
INSERT INTO product (name, price, description, image, quantity,language , author_id, genre_id)
VALUES
    ('Harry Potter and the Sorcerer''s Stone', 19.99, 'A young wizard''s journey begins.', NULL, 100, 'English', 1, 1),
    ('A Game of Thrones', 14.99, 'A tale of power and intrigue in the Seven Kingdoms.', NULL, 50,'English', 2, 1),
    ('Harry Potter and the Chamber of Secrets', 18.99, 'The second adventure in the Harry Potter series.', NULL, 100, 'English', 1, 1);

INSERT INTO product (name, price, description, image, quantity,language , author_id, genre_id)
VALUES
    ('The great Gatsby', 19.99, 'The Great Gabsy', NULL, 100, 'English', 1, 1);

-- Popola la tabella purchase
INSERT INTO purchase (purchase_date, id_user, id_product)
VALUES
    ('2024-06-01', 2, 1),
    ('2024-06-05', 3, 2);

-- Popola la tabella cart_product
INSERT INTO cart_product (id_cart, id_product)
VALUES
    (1, 1),
    (1, 3),
    (2, 2);

