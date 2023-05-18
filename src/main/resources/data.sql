-- This file contains all the data that can be inserted into the database when the application starts.

insert into city values (1,'London');
insert into city values (2, 'Glasgow');
insert into city values (3, 'Birmingham');
insert into city values (4, 'Liverpool');
insert into city values (5, 'Manchester');

insert into city_code values (1, '40000', 1);
insert into city_code values (2, '25000', 2);
insert into city_code values (3,'41000', 3);
insert into city_code values (4,'43000', 4);
insert into city_code values (5,'00000', 5);

insert into category values (1, 'Romance');
insert into category values (2, 'Fantasy');
insert into category values (3, 'Sci-fi');
insert into category values (4, 'Young Adult');
insert into category values (5, 'Horror');

insert into library_user values(1, 'John', 'Doe');
insert into library_user values(2, 'Suzan', 'Lee');
insert into library_user values(3, 'Patrick', 'Steward');
insert into library_user values(4, 'Mary', 'Black');
insert into library_user values(5, 'Cathleen', 'White');

insert into account values(1, 1);
insert into account values(2, 2);
insert into account values(3, 3);
insert into account values(4, 4);
insert into account values(5, 5);

insert into address (street, library_user_library_user_id, city_id) values ('Washington Street 5', 1, 1);
insert into address (street, library_user_library_user_id, city_id) values ('Jefferson Street 8', 2, 2);
insert into address (street, library_user_library_user_id, city_id) values ('Manhattan Street 11', 3, 3);
insert into address (street, library_user_library_user_id, city_id) values ('Chadwick Street 15', 4, 4);
insert into address (street, library_user_library_user_id, city_id) values ('Clover Street 1', 5, 5);

insert into library values (1, 'London Library');
insert into library values (2, 'Glasgow Library');
insert into library values (3, 'Birmingham Library');
insert into library values (4, 'Liverpool Library');
insert into library values (5, 'Manchester Library');

insert into inventory values (1, 1);
insert into inventory values (2, 2);
insert into inventory values (3, 3);
insert into inventory values (4, 4);
insert into inventory values (5, 5);

insert into author values(1, 'Jane', 'Austen');
insert into author values(2, 'William', 'Shakespeare');
insert into author values(3, 'Oscar', 'Wilde');
insert into author values(4, 'Tom', 'Robbins');
insert into author values(5, 'Catherine', 'Fisher');

insert into book values (1, 'Romeo and Juliet', 1, 1);
insert into book values (2, 'Romeo and Juliet', 1, 1);
insert into book values (3, 'Name of the Wind', 1, 2);
insert into book values (4, 'Witcher: Last Wish', 2, 3);
insert into book values (5, 'The Book of Twelve Seasons', 2, 4);
insert into book values (6, 'Secret Doors and Passages', 2, 5);
insert into book values (7, 'Green Hell', 2, 5);
insert into book values (8, 'The Journey', 2, 4);
insert into book values (9, 'The Catcher in the Rye', 2, 5);
insert into book values (10, 'The Hobbit', 2, 4);
insert into book values (11, 'A Tale of Two Cities', 2, 1);
insert into book values (12, 'Maze Runner', 3, 1);
insert into book values (13, 'She: A History of Adventure', 4, 2);
insert into book values (14, 'Harry Potter and the Chamber of Secrets', 1, 3);
insert into book values (15, 'The Name of the Rose ', 2, 4);

insert into isbn (book_book_id, isbn_number) values (1, 986532935611);
insert into isbn (book_book_id, isbn_number) values (2, 986532935611);
insert into isbn (book_book_id, isbn_number) values (3, 986532935613);
insert into isbn (book_book_id, isbn_number) values (4, 986532935614);
insert into isbn (book_book_id, isbn_number) values (5, 986532935615);
insert into isbn (book_book_id, isbn_number) values (6, 986532935616);
insert into isbn (book_book_id, isbn_number) values (7, 986532935617);
insert into isbn (book_book_id, isbn_number) values (8, 986532935618);
insert into isbn (book_book_id, isbn_number) values (9, 986532935619);
insert into isbn (book_book_id, isbn_number) values (10, 986532935620);
insert into isbn (book_book_id, isbn_number) values (11, 986532935621);
insert into isbn (book_book_id, isbn_number) values (12, 986532935622);
insert into isbn (book_book_id, isbn_number) values (13, 986532935123);
insert into isbn (book_book_id, isbn_number) values (14, 986532935624);
insert into isbn (book_book_id, isbn_number) values (15, 986532935625);

insert into credit (credit_id, book_book_id, author_author_id) values(1, 1, 2);
insert into credit (credit_id, book_book_id, author_author_id) values(2, 2, 2);
insert into credit (credit_id, book_book_id, author_author_id) values(3, 3, 1);
insert into credit (credit_id, book_book_id, author_author_id) values(4, 4, 5);
insert into credit (credit_id, book_book_id, author_author_id) values(5, 5, 5);
insert into credit (credit_id, book_book_id, author_author_id) values(6, 6, 4);
insert into credit (credit_id, book_book_id, author_author_id) values(7, 6, 5);
insert into credit (credit_id, book_book_id, author_author_id) values(8, 7, 3);
insert into credit (credit_id, book_book_id, author_author_id) values(9, 8, 5);
insert into credit (credit_id, book_book_id, author_author_id) values(10, 9, 4);
insert into credit (credit_id, book_book_id, author_author_id) values(11, 10, 5);
insert into credit (credit_id, book_book_id, author_author_id) values(12, 10, 4);
insert into credit (credit_id, book_book_id, author_author_id) values(13, 11, 2);
insert into credit (credit_id, book_book_id, author_author_id) values(15, 12, 3);
insert into credit (credit_id, book_book_id, author_author_id) values(16, 13, 1);
insert into credit (credit_id, book_book_id, author_author_id) values(17, 13, 2);
insert into credit (credit_id, book_book_id, author_author_id) values(18, 14, 3);
insert into credit (credit_id, book_book_id, author_author_id) values(19, 15, 5);
insert into credit (credit_id, book_book_id, author_author_id) values(20, 15, 4);

insert into loan values('1', 1, 1);
insert into loan values('2', 2, 2);
insert into loan values('3', 3, 3);
insert into loan values('4', 4, 4);
insert into loan values('5', 5, 5);
