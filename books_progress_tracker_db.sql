drop database if exists books_progress_tracker_db;
create database books_progress_tracker_db;
use books_progress_tracker_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE all_books (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(100) NOT NULL,
  isbn        VARCHAR(20),
  authors     TEXT,
  genres      TEXT,
  page_count  INT
);

CREATE TABLE user_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    book_id INT,
    status ENUM('not started', 'in progress', 'completed') NOT NULL DEFAULT 'not started',
    UNIQUE (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES all_books(id)
);

insert into all_books (title, isbn, authors, genres, page_count)
    values ('To Kill a Mockingbird', '978-0062420701', 'Harper Lee', 'Southern Gothic; Novel; Fiction', '336');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('The Great Gatsby', '978-0743273565', 'F. Scott Fitzgerald', 'Tragedy; Fiction; Novel', '180');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('The Catcher in the Rye', '978-0316769488', 'J.D. Salinger', 'Fiction; Novel; Coming of age', '277');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('Moby-Dick', '978-1503280786', 'Herman Melville', 'Adventure; Fiction; Novel', '585');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('Crime and Punishment', '978-0486415871', 'Fyodor Dostoevsky', 'Philosophical; Fiction; Novel', '430');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('Pride and Prejudice', '978-1503290563', 'Jane Austen', 'Romance; Fiction; Novel', '279');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('War and Peace', '978-1420954302', 'Leo Tolstoy', 'Historical Fiction; Novel', '1225');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('The Lord of the Rings', '978-0544003415', 'J.R.R. Tolkien', 'Fantasy; Adventure; Fiction', '1216');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('Adventures of Huckleberry Finn', '978-1503215150', 'Mark Twain', 'Adventure; Fiction; Novel', '366');
insert into all_books (title, isbn, authors, genres, page_count) 
    values ('A Raisin in the Sun', '978-0679755333', 'Lorraine Hansberry', 'Drama; Fiction; Play', '151');

insert into users (username, password)
    values ('test', 'test');
