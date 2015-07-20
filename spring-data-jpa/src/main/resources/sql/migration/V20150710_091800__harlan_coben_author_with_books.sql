-- authors
insert into author (id, type, birth_date, first_name, last_name, literary_genre, version) values (1, 'WRITER', DATE('1962-01-04'), 'Harlan', 'Coben', 'CRIME', 0);

-- books
insert into book (id, title, version) values (1, 'Nie mów nikomu', 0);
insert into book (id, title, version) values (2, 'Wszyscy mamy tajemnice', 0);
insert into book (id, title, version) values (3, 'Schronienie', 0);
insert into book (id, title, version) values (4, 'Mistyfikacja', 0);

-- book_exemplar
insert into book_exemplar (id, serial_number, book_fk) values (1, 'ISBN-0001', 1);
insert into book_exemplar (id, serial_number, book_fk) values (2, 'ISBN-0002', 1);
insert into book_exemplar (id, serial_number, book_fk) values (3, 'ISBN-0003', 1);
insert into book_exemplar (id, serial_number, book_fk) values (4, 'ISBN-0004', 1);

insert into book_exemplar (id, serial_number, book_fk) values (5, 'ISBN-0005', 2);
insert into book_exemplar (id, serial_number, book_fk) values (6, 'ISBN-0006', 2);
insert into book_exemplar (id, serial_number, book_fk) values (7, 'ISBN-0007', 2);

insert into book_exemplar (id, serial_number, book_fk) values (8, 'ISBN-0008', 3);
insert into book_exemplar (id, serial_number, book_fk) values (9, 'ISBN-0009', 3);
insert into book_exemplar (id, serial_number, book_fk) values (10, 'ISBN-0010', 3);
insert into book_exemplar (id, serial_number, book_fk) values (11, 'ISBN-0011', 3);
insert into book_exemplar (id, serial_number, book_fk) values (12, 'ISBN-0012', 3);

insert into book_exemplar (id, serial_number, book_fk) values (13, 'ISBN-0013', 4);

-- paper_book
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (1, 'HARD', 322, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (2, 'HARD', 254, 'B_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (3, 'SOFT', 443, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (4, 'HARD', 342, 'B_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (5, 'SOFT', 132, 'B_4');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (6, 'SOFT', 245, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (7, 'HARD', 523, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (8, 'SOFT', 234, 'B_4');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (9, 'HARD', 411, 'B_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (10, 'SOFT', 312, 'B_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (11, 'SOFT', 98, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (12, 'SOFT', 56, 'B_4');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (13, 'HARD', 333, 'A_4');

-- book_author
insert into book_author (author_id, book_id) values (1, 1);
insert into book_author (author_id, book_id) values (1, 2);
insert into book_author (author_id, book_id) values (1, 3);
insert into book_author (author_id, book_id) values (1, 4);

-- spoilers
insert into book_spoiler(id, book_fk, content) values (1, 1, 'Od śmierci Elizabeth z rąk seryjnego zabójcy minęło osiem lat, ale młody lekarz David Beck nie potrafi o niej zapomnieć. Niespodziewanie pocztą elektroniczną otrzymuje niezbity dowód, że jego żona nadal żyje. Jak to możliwe, skoro jej ciało zostało zidentyfikowane ponad wszelką wątpliwość? Beck ignoruje ostrzeżenie ''nie mów nikomu'' i próbuje dotrzeć do sedna mrocznej tajemnicy, której ślady...');