
drop database if exists lms;
create database lms;
use lms;

CREATE TABLE users (
    id INT AUTO_INCREMENT,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64),
    address VARCHAR(512),
    email VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE readers (
    id INT AUTO_INCREMENT,
    user_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);

create table branches(
	branch_id int auto_increment,
    name varchar(256),
    address varchar(256),
    primary key(branch_id)
);

CREATE TABLE librarians (
    id INT AUTO_INCREMENT,
    user_id int,
    branch_id int,
    PRIMARY KEY (id),
    foreign key(user_id) references users(id),
    foreign key(branch_id) references branches(branch_id)
);
CREATE TABLE authors (
    author_id INT AUTO_INCREMENT,
    full_name VARCHAR(128) NOT NULL,
    birthdate DATE,
    information VARCHAR(2048),
    PRIMARY KEY (author_id)
);
CREATE TABLE books (
	book_id int auto_increment,
    ISBN varchar(32) NOT NULL unique,
    information VARCHAR(2000) NOT NULL,
    title VARCHAR(150) NOT NULL,
    number_of_pages INT NOT NULL,
    PRIMARY KEY (book_id)
);
CREATE TABLE book_author (
    author_id INT,
    book_id int,
    FOREIGN KEY (author_id)
        REFERENCES authors (author_id),
    FOREIGN KEY (book_id)
        REFERENCES books (book_id),
    PRIMARY KEY (author_id , book_id)
);

create table categories(
	category_id int auto_increment,
    name varchar(128),
    primary key(category_id)
);

CREATE TABLE book_category (
    category_id int,
    book_id int,
    PRIMARY KEY (book_id , category_id),
    FOREIGN KEY (book_id)
        REFERENCES books (book_id)
);
create table physical_conditions(
	physical_condition_id tinyint auto_increment,
    physical_condition varchar(128),
    primary key(physical_condition_id)
);
CREATE TABLE book_copies (
    book_copy_id INT AUTO_INCREMENT,
    cost numeric(4,2) NOT NULL,
    physical_condition_id tinyint,
    branch_id int,
    book_id int,
    PRIMARY KEY (book_copy_id),
    foreign key(physical_condition_id) references physical_conditions(physical_condition_id),
    foreign key(branch_id) references branches(branch_id),
    foreign key(book_id) references books(book_id)
);

CREATE TABLE borrows (
    book_copy_id INT NOT NULL,
    reader_id INT NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (book_copy_id)
        REFERENCES book_copies (book_copy_id),
    FOREIGN KEY (reader_id)
        REFERENCES readers(id),
    PRIMARY KEY (book_copy_id , reader_id)
);








