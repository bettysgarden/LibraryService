create table author
(
    id          bigint         default nextval('author_idauthor_seq'::regclass) not null
        primary key,
    name        varchar(255)                                                    not null,
    website     varchar(255)   default NULL::character varying,
    description varchar(10000) default NULL::character varying
);
create table author_has_book
(
    author_idauthor bigint not null
        constraint fk_author_has_book_author1
        references author (id),
    book_idbook     bigint not null
        constraint fk_author_has_book_book1
        references book (id),
    primary key (author_idauthor, book_idbook)
);
create table book
(
    id                    bigint  default nextval('book_idbook_seq'::regclass) not null
        primary key,
    title                 varchar(255)                                         not null,
    rating                integer default 0,
    description           varchar(10000),
    cover_url             varchar(255),
    publisher_idpublisher bigint
        references publisher
            on delete cascade,
    year_published        integer
        constraint book_date_published_check
        check ((year_published)::numeric <= EXTRACT(year FROM now())),
    cover_url_m           varchar(255),
    cover_url_l           varchar(255),
    isbn                  text
);
create table book_has_genre
(
    book_idbook   bigint not null
        references book (id),
    genre_idgenre bigint not null
        references genre (id),
    primary key (book_idbook, genre_idgenre)
);
create table comment
(
    id              bigint       default nextval('comment_idcomment_seq'::regclass) not null
        primary key,
    content         varchar(255)                                                    not null,
    timeadded       timestamp(6) default CURRENT_TIMESTAMP,
    review_idreview bigint
        references review
            on delete cascade,
    user_iduser     bigint
        references "user"
            on update cascade on delete cascade
);
create table genre
(
    id    bigint default nextval('genre_idgenre_seq'::regclass) not null
        primary key,
    title varchar(255)                                          not null
);
create table publisher
(
    id    bigint default nextval('publisher_idpublisher_seq'::regclass) not null
        primary key,
    title varchar(255)                                                  not null
        unique
);
create table review
(
    id          bigint       default nextval('review_idreview_seq'::regclass) not null
        primary key,
    rating      integer                                                       not null,
    content     varchar(10000),
    timeadded   timestamp(6) default CURRENT_TIMESTAMP,
    book_idbook bigint                                                        not null
        constraint fk_review_book1
        references book,
    user_iduser bigint
        references "user"
);
create table "user"
(
    id       integer default nextval('userid_seq'::regclass) not null
        primary key,
    age      integer
        constraint real_age
        check (age >= 0),
    location text,
    password text                                            not null,
    username varchar(255)                                    not null
        unique
);