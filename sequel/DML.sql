
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

-- Popolare la tabella genre
INSERT INTO genre (name) VALUES
                             ('Romanzo'),
                             ('Fantasy'),
                             ('Filosofico'),
                             ('Umoristico'),
                             ('Fantascienza');

-- Popolare la tabella author
INSERT INTO author (name) VALUES
                              ('F. Scott Fitzgerald'),
                              ('J.R.R. Tolkien'),
                              ('Hanya Yanagihara'),
                              ('Hermann Hesse'),
                              ('Francesco Totti'),
                              ('Ernest Hemingway'),
                              ('Charles Bukowski'),
                              ('Jack Kerouac'),
                              ('Suzanne Collins'),
                              ('Fyodor Dostoevsky'),
                              ('Richard Matheson'),
                              ('Italo Calvino'),
                              ('Gabriele D\'Annunzio');

-- Popolare la tabella product
INSERT INTO product (name, price, description, image, quantity, language, author_id, genre_id) VALUES
                                                                                                   ('Il grande Gatsby', 10.99, 'Un classico della letteratura americana', NULL, 100, 'Italiano', (SELECT id FROM author WHERE name = 'F. Scott Fitzgerald'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Il Silmarillion', 12.99, 'La storia della Terra di Mezzo', NULL, 50, 'Italiano', (SELECT id FROM author WHERE name = 'J.R.R. Tolkien'), (SELECT id FROM genre WHERE name = 'Fantasy')),
                                                                                                   ('Una vita come tante', 14.99, 'Un romanzo contemporaneo', NULL, 30, 'Italiano', (SELECT id FROM author WHERE name = 'Hanya Yanagihara'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Siddharta', 9.99, 'Un viaggio spirituale', NULL, 70, 'Italiano', (SELECT id FROM author WHERE name = 'Hermann Hesse'), (SELECT id FROM genre WHERE name = 'Filosofico')),
                                                                                                   ('Le barzellette di Totti', 8.99, 'Una raccolta di barzellette divertenti', NULL, 200, 'Italiano', (SELECT id FROM author WHERE name = 'Francesco Totti'), (SELECT id FROM genre WHERE name = 'Umoristico')),
                                                                                                   ('Beren e Lúthien', 11.99, 'Una storia d\'amore nella Terra di Mezzo', NULL, 60, 'Italiano', (SELECT id FROM author WHERE name = 'J.R.R. Tolkien'), (SELECT id FROM genre WHERE name = 'Fantasy')),
                                                                                                   ('Per chi suona la campana', 10.99, 'Un romanzo sulla guerra civile spagnola', NULL, 80, 'Italiano', (SELECT id FROM author WHERE name = 'Ernest Hemingway'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Factotum', 9.99, 'Un romanzo autobiografico', NULL, 40, 'Italiano', (SELECT id FROM author WHERE name = 'Charles Bukowski'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('On the Road', 10.99, 'Un viaggio attraverso l\'America', NULL, 90, 'Italiano', (SELECT id FROM author WHERE name = 'Jack Kerouac'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Hunger Games', 9.99, 'Il primo libro della trilogia di Hunger Games', NULL, 30, 'Italiano', (SELECT id FROM author WHERE name = 'Suzanne Collins'), (SELECT id FROM genre WHERE name = 'Fantasy')),
                                                                                                   ('La ragazza di fuoco', 9.99, 'Il secondo libro della trilogia di Hunger Games', NULL, 30, 'Italiano', (SELECT id FROM author WHERE name = 'Suzanne Collins'), (SELECT id FROM genre WHERE name = 'Fantasy')),
                                                                                                   ('Il canto della rivolta', 9.99, 'Il terzo libro della trilogia di Hunger Games', NULL, 30, 'Italiano', (SELECT id FROM author WHERE name = 'Suzanne Collins'), (SELECT id FROM genre WHERE name = 'Fantasy')),
                                                                                                   ('La compagnia dell\'anello', 12.99, 'Il primo libro della trilogia del Signore degli Anelli', NULL, 20, 'Italiano', (SELECT id FROM author WHERE name = 'J.R.R. Tolkien'), (SELECT id FROM genre WHERE name = 'Fantasy')),
                                                                                                   ('Le due torri', 12.99, 'Il secondo libro della trilogia del Signore degli Anelli', NULL, 20, 'Italiano', (SELECT id FROM author WHERE name = 'J.R.R. Tolkien'), (SELECT id FROM genre WHERE name = 'Fantasy')),
                                                                                                   ('Il ritorno del re', 12.99, 'Il terzo libro della trilogia del Signore degli Anelli', NULL, 20, 'Italiano', (SELECT id FROM author WHERE name = 'J.R.R. Tolkien'), (SELECT id FROM genre WHERE name = 'Fantasy')),
                                                                                                   ('Le notti bianche', 7.99, 'Un breve romanzo di Dostoevsky', NULL, 100, 'Italiano', (SELECT id FROM author WHERE name = 'Fyodor Dostoevsky'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Il giocatore', 9.99, 'Un romanzo sulla dipendenza dal gioco', NULL, 100, 'Italiano', (SELECT id FROM author WHERE name = 'Fyodor Dostoevsky'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Io sono leggenda', 11.99, 'Un romanzo post-apocalittico', NULL, 60, 'Italiano', (SELECT id FROM author WHERE name = 'Richard Matheson'), (SELECT id FROM genre WHERE name = 'Fantascienza')),
                                                                                                   ('Il visconte dimezzato', 9.99, 'Il primo libro della trilogia di Italo Calvino', NULL, 50, 'Italiano', (SELECT id FROM author WHERE name = 'Italo Calvino'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Il barone rampante', 9.99, 'Il secondo libro della trilogia di Italo Calvino', NULL, 50, 'Italiano', (SELECT id FROM author WHERE name = 'Italo Calvino'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Il cavaliere inesistente', 9.99, 'Il terzo libro della trilogia di Italo Calvino', NULL, 50, 'Italiano', (SELECT id FROM author WHERE name = 'Italo Calvino'), (SELECT id FROM genre WHERE name = 'Romanzo')),
                                                                                                   ('Il piacere', 9.99, 'Un romanzo decadente', NULL, 70, 'Italiano', (SELECT id FROM author WHERE name = 'Gabriele D\'Annunzio'), (SELECT id FROM genre WHERE name = 'Romanzo'));

-- Popolare la tabella genre
INSERT INTO genre (name) VALUES
                             ('Dystopian'),
                             ('Tragedy'),
                             ('Historical'),
                             ('Epic');

-- Popolare la tabella author
INSERT INTO author (name) VALUES
                              ('George Orwell'),
                              ('Harper Lee'),
                              ('Jane Austen'),
                              ('Herman Melville'),
                              ('Aldous Huxley'),
                              ('J.D. Salinger'),
                              ('Leo Tolstoy'),
                              ('Johann Wolfgang von Goethe'),
                              ('Franz Kafka'),
                              ('Julius Caesar'),
                              ('Virgil');

-- Popolare la tabella product
INSERT INTO product (name, price, description, image, quantity, language, author_id, genre_id) VALUES
                                                                                                   ('1984', 15.99, 'A dystopian novel', NULL, 100, 'English', (SELECT id FROM author WHERE name = 'George Orwell'), (SELECT id FROM genre WHERE name = 'Dystopian')),
                                                                                                   ('To Kill a Mockingbird', 10.99, 'A novel about racial injustice', NULL, 100, 'English', (SELECT id FROM author WHERE name = 'Harper Lee'), (SELECT id FROM genre WHERE name = 'Novel')),
                                                                                                   ('Pride and Prejudice', 8.99, 'A romantic novel', NULL, 100, 'English', (SELECT id FROM author WHERE name = 'Jane Austen'), (SELECT id FROM genre WHERE name = 'Novel')),
                                                                                                   ('Moby Dick', 12.99, 'A novel about a whale', NULL, 100, 'English', (SELECT id FROM author WHERE name = 'Herman Melville'), (SELECT id FROM genre WHERE name = 'Novel')),
                                                                                                   ('Brave New World', 14.99, 'A dystopian novel', NULL, 100, 'English', (SELECT id FROM author WHERE name = 'Aldous Huxley'), (SELECT id FROM genre WHERE name = 'Dystopian')),
                                                                                                   ('The Catcher in the Rye', 11.99, 'A novel about teenage rebellion', NULL, 100, 'English', (SELECT id FROM author WHERE name = 'J.D. Salinger'), (SELECT id FROM genre WHERE name = 'Novel')),
                                                                                                   ('The Great Gatsby', 10.99, 'A classic American novel', NULL, 100, 'English', (SELECT id FROM author WHERE name = 'F. Scott Fitzgerald'), (SELECT id FROM genre WHERE name = 'Novel')),

                                                                                                   ('Преступление и наказание', 13.99, 'A novel about crime and punishment', NULL, 50, 'Russian', (SELECT id FROM author WHERE name = 'Fyodor Dostoevsky'), (SELECT id FROM genre WHERE name = 'Novel')),
                                                                                                   ('Война и мир', 18.99, 'A novel about war and peace', NULL, 50, 'Russian', (SELECT id FROM author WHERE name = 'Leo Tolstoy'), (SELECT id FROM genre WHERE name = 'Novel')),
                                                                                                   ('Анна Каренина', 16.99, 'A novel about a tragic love story', NULL, 50, 'Russian', (SELECT id FROM author WHERE name = 'Leo Tolstoy'), (SELECT id FROM genre WHERE name = 'Novel')),

                                                                                                   ('Faust', 9.99, 'A tragic play', NULL, 50, 'German', (SELECT id FROM author WHERE name = 'Johann Wolfgang von Goethe'), (SELECT id FROM genre WHERE name = 'Tragedy')),
                                                                                                   ('Die Verwandlung', 7.99, 'A novel about a man transformed into an insect', NULL, 50, 'German', (SELECT id FROM author WHERE name = 'Franz Kafka'), (SELECT id FROM genre WHERE name = 'Novel')),

                                                                                                   ('De Bello Gallico', 19.99, 'A historical account of the Gallic Wars', NULL, 30, 'Latin', (SELECT id FROM author WHERE name = 'Julius Caesar'), (SELECT id FROM genre WHERE name = 'Historical')),
                                                                                                   ('Aeneis', 17.99, 'An epic poem', NULL, 30, 'Latin', (SELECT id FROM author WHERE name = 'Virgil'), (SELECT id FROM genre WHERE name = 'Epic'));
