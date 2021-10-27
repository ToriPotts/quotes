DROP TABLE IF EXISTS quotes;

CREATE TABLE quotes (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              author VARCHAR(250) NOT NULL,
                              text VARCHAR(250) NOT NULL
);

INSERT INTO quotes (author, text) VALUES
('John', 'Hello, world'),
('Tori', 'Clean up your room'),
('JFK', 'Ask not what your country can do for you – ask what you can do for your country'),
('Walt Disney', 'The way to get started is to quit talking and begin doing'),
('Eleanor Roosevelt', 'If life were predictable it would cease to be life, and be without flavor'),
('John Lennon', 'Life is what happens when you''re busy making other plans');


DROP TABLE IF EXISTS books;

CREATE TABLE books (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              author VARCHAR(250) NOT NULL,
                              title VARCHAR(250) NOT NULL,
                              pages INT DEFAULT NULL
);

INSERT INTO books (author, title, pages) VALUES
('foo', 'bar', 27);