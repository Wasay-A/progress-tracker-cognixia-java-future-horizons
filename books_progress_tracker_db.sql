drop database if exists books_progress_tracker_db;
create database books_progress_tracker_db;
use books_progress_tracker_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE all_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL
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

insert into all_books (title) 
    values ('To Kill a Mockingbird');
insert into all_books (title) 
    values ('The Great Gatsby');
insert into all_books (title) 
    values ('The Catcher in the Rye');
insert into all_books (title) 
    values ('Moby-Dick');
insert into all_books (title) 
    values ('Crime and Punishment');
insert into all_books (title) 
    values ('Pride and Prejudice');
insert into all_books (title) 
    values ('War and Peace');
insert into all_books (title) 
    values ('The Lord of the Rings');
insert into all_books (title) 
    values ('Adventures of Huckleberry Finn');
insert into all_books (title) 
    values ('A Raisin in the Sun');