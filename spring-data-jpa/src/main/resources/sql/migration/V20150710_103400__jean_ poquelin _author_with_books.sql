-- authors
insert into author (id, type, birth_date, first_name, last_name, nick_name, literary_genre) values (2, 'WRITER', TO_DATE('1622/01/15', 'yyyy/mm/dd'), 'Jean', 'Poquelin', 'Molier', 'COMEDY');

-- books
insert into book (id, title) values (5, 'Rogacz z urojenia');
insert into book (id, title) values (6, 'Szkoła mężów');
insert into book (id, title) values (7, 'Szkoła żon');
insert into book (id, title) values (8, 'Świętoszek');

-- book_exemplar
insert into book_exemplar (id, serial_number, book_fk) values (14, 'ISBN-0014', 5);
insert into book_exemplar (id, serial_number, book_fk) values (15, 'ISBN-0015', 5);
insert into book_exemplar (id, serial_number, book_fk) values (16, 'ISBN-0016', 5);
insert into book_exemplar (id, serial_number, book_fk) values (17, 'ISBN-0017', 5);

insert into book_exemplar (id, serial_number, book_fk) values (18, 'ISBN-0018', 6);
insert into book_exemplar (id, serial_number, book_fk) values (19, 'ISBN-0019', 6);
insert into book_exemplar (id, serial_number, book_fk) values (20, 'ISBN-0020', 6);

insert into book_exemplar (id, serial_number, book_fk) values (21, 'ISBN-0021', 7);
insert into book_exemplar (id, serial_number, book_fk) values (22, 'ISBN-0022', 7);
insert into book_exemplar (id, serial_number, book_fk) values (23, 'ISBN-0023', 7);
insert into book_exemplar (id, serial_number, book_fk) values (24, 'ISBN-0024', 7);
insert into book_exemplar (id, serial_number, book_fk) values (25, 'ISBN-0025', 7);

insert into book_exemplar (id, serial_number, book_fk) values (26, 'ISBN-0026', 8);

-- paper_book
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (14, 'HARD', 322, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (15, 'HARD', 254, 'B_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (16, 'SOFT', 443, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (17, 'HARD', 342, 'B_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (18, 'SOFT', 132, 'B_4');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (19, 'SOFT', 245, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (20, 'HARD', 523, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (21, 'SOFT', 234, 'B_4');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (22, 'HARD', 411, 'B_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (23, 'SOFT', 312, 'B_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (24, 'SOFT', 98, 'A_5');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (25, 'SOFT', 56, 'B_4');
insert into paper_book (book_ex_id, book_cover, pages_count, paper_size) values (26, 'HARD', 333, 'A_4');