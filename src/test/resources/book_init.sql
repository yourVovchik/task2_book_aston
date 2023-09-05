CREATE TABLE publisher
(
    id SERIAL NOT NULL ,
    name character varying(50)
);

CREATE TABLE author
(
    id SERIAL NOT NULL ,
    name character varying(50),
    surname character varying(50),
    publisher_id bigint,
    CONSTRAINT author_pkey PRIMARY KEY (id),
    CONSTRAINT author_publisher_id_fkey FOREIGN KEY (publisher_id)
        REFERENCES public.publisher (id)

);

CREATE TABLE book
(
    id SERIAL NOT NULL ,
    name character varying(50) ,
    publication_date date NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (id)
);

CREATE TABLE author_book
(
    id SERIAL NOT NULL,
    author_id bigint NOT NULL,
    book_id bigint NOT NULL,
    CONSTRAINT author_book_pkey PRIMARY KEY (id),
    CONSTRAINT author_book_author_id_book_id_key UNIQUE (author_id, book_id),
    CONSTRAINT author_book_author_id_fkey FOREIGN KEY (author_id)
        REFERENCES public.author (id) ,
    CONSTRAINT author_book_book_id_fkey FOREIGN KEY (book_id)
        REFERENCES public.book (id)
);

INSERT INTO publisher(id,name)
VALUES (DEFAULT,'THE NEW YORK TIMES');
INSERT INTO publisher(id,name)
VALUES (DEFAULT,'THE WASHINGTON POST');
INSERT INTO publisher(id,name)
VALUES (DEFAULT,'THE GUARDIAN');

INSERT INTO author (id, name, surname)
VALUES (1, 'Maksim1', 'Tankov1');
INSERT INTO author (id, name, surname)
VALUES (2, 'Maksim2', 'Tankov2');
INSERT INTO author (id, name, surname)
VALUES (3, 'Maksim3', 'Tankov3');
INSERT INTO author (id, name, surname)
VALUES (4, 'Maksim4', 'Tankov4');
INSERT INTO author (id, name, surname)
VALUES (5, 'Maksim5', 'Tankov5');
INSERT INTO author (id, name, surname)
VALUES (6, 'Maksim6', 'Tankov6');

INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_1', '2020-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_2', '2019-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_3', '2001-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_4', '1998-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_5', '2005-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_6', '2001-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_7', '2017-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_8', '2022-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_9', '1893-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_10', '1932-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_11', '1977-01-01');
INSERT INTO book (id, name, publication_date)
VALUES (DEFAULT, 'Book_12', '2015-01-01');

INSERT INTO author_book (author_id, book_id)
VALUES (1, 1);
INSERT INTO author_book (author_id, book_id)
VALUES (1, 2);
INSERT INTO author_book (author_id, book_id)
VALUES (1, 3);
INSERT INTO author_book (author_id, book_id)
VALUES (2, 4);
INSERT INTO author_book (author_id, book_id)
VALUES (3, 5);
INSERT INTO author_book (author_id, book_id)
VALUES (4, 5);
INSERT INTO author_book (author_id, book_id)
VALUES (4, 6);
INSERT INTO author_book (author_id, book_id)
VALUES (5, 7);
INSERT INTO author_book (author_id, book_id)
VALUES (6, 7);
INSERT INTO author_book (author_id, book_id)
VALUES (6, 8);
INSERT INTO author_book (author_id, book_id)
VALUES (6, 9);
INSERT INTO author_book (author_id, book_id)
VALUES (6, 10);
INSERT INTO author_book (author_id, book_id)
VALUES (6, 11);
