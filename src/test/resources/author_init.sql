CREATE TABLE author
(
    id SERIAL NOT NULL ,
    name character varying(50),
    surname character varying(50),
    CONSTRAINT author_pkey PRIMARY KEY (id)
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
    author_id integer NOT NULL,
    book_id integer NOT NULL,
    CONSTRAINT author_book_pkey PRIMARY KEY (id),
    CONSTRAINT author_book_author_id_book_id_key UNIQUE (author_id, book_id),
    CONSTRAINT author_book_author_id_fkey FOREIGN KEY (author_id)
    REFERENCES public.author (id) ,
    CONSTRAINT author_book_book_id_fkey FOREIGN KEY (book_id)
    REFERENCES public.book (id)
);

INSERT INTO author (name, surname)
VALUES ( 'Maksim1', 'Tankov1');
INSERT INTO public.author ( name, surname)
VALUES ( 'Maksim2', 'Tankov2');
INSERT INTO public.author ( name, surname)
VALUES ( 'Maksim3', 'Tankov3');
INSERT INTO public.author ( name, surname)
VALUES ( 'Maksim4', 'Tankov4');
INSERT INTO public.author ( name, surname)
VALUES ( 'Maksim5', 'Tankov5');

