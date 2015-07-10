create table author (
    type varchar2(6 char) not null,
    id number(19,0) not null,
    birth_date DATE not null,
    first_name varchar2(255 char) not null,
    last_name varchar2(255 char) not null,
    nick_name varchar2(255 char),
    literary_genre varchar2(255 char),
    primary key (id)
);

create table book (
    id number(19,0) not null,
    title varchar2(50 char) not null,
    primary key (id)
);

create table book_author (
    book_id number(19,0) not null,
    author_id number(19,0) not null,
    primary key (book_id, author_id)
);

create table book_exemplar (
    id number(19,0) not null,
    serial_number varchar2(15 char) not null,
    book_fk number(19,0) not null,
    loan_fk number(19,0),
    primary key (id)
);

create table customer (
    id number(19,0) not null,
    email varchar2(35 char) not null,
    birth_date DATE not null,
    first_name varchar2(255 char) not null,
    last_name varchar2(255 char) not null,
    phone_number varchar2(15 char) not null,
    primary key (id)
);

create table loan (
    id number(19,0) not null,
    loan_date timestamp not null,
    customer_fk number(19,0),
    primary key (id)
);

create table paper_book (
    book_cover varchar2(255 char) not null,
    pages_count number(10,0) not null,
    paper_size varchar2(255 char) not null,
    book_ex_id number(19,0) not null,
    primary key (book_ex_id)
);

alter table book_exemplar
    add constraint UK_q1vdbj5iwa03t5gql2e9f4fln  unique (serial_number);

alter table book_author
    add constraint FK_6cmg2roopa9a4c97uxetgf2e9
    foreign key (author_id)
    references author;

alter table book_author
    add constraint FK_q37qkj7serxg0bh56m450uigs
    foreign key (book_id)
    references book;

alter table book_exemplar
    add constraint FK_6xjdyconq4lm332wdhhln9e80
    foreign key (book_fk)
    references book;

alter table book_exemplar
    add constraint FK_hp46sikojivt1quj4ybeu3sau
    foreign key (loan_fk)
    references loan;

alter table loan
    add constraint FK_1r3cl8gspam5pvkukjhqma556
    foreign key (customer_fk)
    references customer;

alter table paper_book
    add constraint FK_g118bplwt41nseg340hy1g5vf
    foreign key (book_ex_id)
    references book_exemplar;

create sequence hibernate_sequence;