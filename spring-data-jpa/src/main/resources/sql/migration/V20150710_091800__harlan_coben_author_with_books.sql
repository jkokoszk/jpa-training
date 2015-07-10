-- authors
insert into author (id, type, birth_date, first_name, last_name, literary_genre) values (1, 'WRITER', TO_DATE('2003/07/09', 'yyyy/mm/dd'), 'Harlan', 'Coben', 'CRIME');

-- books
insert into book (id, title) values (1, 'Nie mów nikomu');
insert into book (id, title) values (2, 'Wszyscy mamy tajemnice');
insert into book (id, title) values (3, 'Schronienie');
insert into book (id, title) values (4, 'Mistyfikacja');

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