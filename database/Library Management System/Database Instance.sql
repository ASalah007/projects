-- insert authors

insert into authors values(default, "Aly Ibrahim", "1968-9-27", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, ");
insert into authors values(default, "Ahmed Mohamed", "1993-6-21", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, ");
insert into authors values(default, "Sayed Mahmoud", "1979-5-25", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, ");
insert into authors values(default, "Salma AbdelKhalek", "1988-1-3", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, ");
insert into authors values(default, "Somayia Ahmed", "1998-10-1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, ");

-- insert books

insert into books values(default, "0-3344-5099-3", "information about random book", "Book1", 123);
insert into books values(default, "0-2628-1422-6", "information about random book", "Book2", 537);
insert into books values(default, "0-5219-5553-X", "information about random book", "Book3", 137);
insert into books values(default, "0-4179-6655-5", "information about random book", "Book4", 852);
insert into books values(default, "0-1318-1542-3", "information about random book", "Book5", 334);

-- relate books with autors
insert into book_author values(1,1);
insert into book_author values(2,2);
insert into book_author values(3,1);
insert into book_author values(2,4);
insert into book_author values(2,5);
insert into book_author values(1,3);

-- insert physical conditions 

insert into physical_conditions values(default, "Excellent");
insert into physical_conditions values(default, "Very-Good");
insert into physical_conditions values(default, "Good");
insert into physical_conditions values(default, "Usable");
insert into physical_conditions values(default, "Not-Usable");

-- insert branches 

insert into branches values(default, "Elmaady branch", "90 st, Elmaady, Cairo");
insert into branches values(default, "Alex branch", "36 st, Alex, Cairo");
insert into branches values(default, "Nasr City branch", "15 st, Nasr City, Cairo");

-- insert book copies
insert into book_copies values(default, 25.39, 1, 1, 1);
insert into book_copies values(default, 50.25, 3, 1, 1); 
insert into book_copies values(default, 75.00, 2, 1, 3); 
insert into book_copies values(default, 15.99, 5, 2, 2);
insert into book_copies values(default, 15.99, 4, 2, 4);
insert into book_copies values(default, 15.99, 4, 3, 1);
insert into book_copies values(default, 15.99, 2, 3, 2);

-- insert some categories 

insert into categories values(default, "Action and Adventure");
insert into categories values(default, "Classics");
insert into categories values(default, "Comic Book");
insert into categories values(default, "Fantasy");
insert into categories values(default, "Historical Fiction");
insert into categories values(default, "Science");

-- relate books with categories 

insert into book_category values(1,1);
insert into book_category values(5,1);
insert into book_category values(2,2);
insert into book_category values(1,2);
insert into book_category values(6,4);
insert into book_category values(1,5);
insert into book_category values(1,3);
insert into book_category values(3,2);
insert into book_category values(5,4);
insert into book_category values(4,1);
insert into book_category values(4,4);


-- insert some users 
insert into users values(default, "Ahmed", "Salah", "1563 fancy st, Assgard, Multiverse", "email1@gmail.com", "password1");
insert into users values(default, "Aly", "Mahmoud", "1563 fancy st, Assgard, Multiverse", "email2@gmail.com", "password2");
insert into users values(default, "Saleh", "Ibrahim", "1563 fancy st, Assgard, Multiverse", "email3@gmail.com", "password3");
insert into users values(default, "Gamel", "moahmed", "1563 fancy st, Assgard, Multiverse", "email4@gmail.com", "password4");
insert into users values(default, "Hossam", "Ismail", "1563 fancy st, Assgard, Multiverse", "email5@gmail.com", "password5");
insert into users values(default, "Salem", "John", "1563 fancy st, Assgard, Multiverse", "email6@gmail.com", "password6");
insert into users values(default, "Salma", "Antony", "1563 fancy st, Assgard, Multiverse", "email7@gmail.com", "password7");

-- insert some readers 
insert into readers values(default, 1);
insert into readers values(default, 2);
insert into readers values(default, 3);
insert into readers values(default, 4);

insert into borrows values (1, 1, '2022-4-25', null);
insert into borrows values (2, 1, '2022-4-1', null);
insert into borrows values (3, 2, '2022-4-10', '2022-4-9');
insert into borrows values (4, 3, '2022-4-1', '2022-4-10');
insert into borrows values (5, 4, '2022-4-19', '2022-4-10');

-- insert some librarians

insert into librarians values (default, 5, 1);
insert into librarians values (default, 6, 2);
insert into librarians values (default, 7, 3);



 