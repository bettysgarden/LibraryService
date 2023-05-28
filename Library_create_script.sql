

CREATE TABLE IF NOT EXISTS Book
(
    idBook         SERIAL8,
    title          VARCHAR(100)   NOT NULL,
    rating         INT DEFAULT 0,
    date_published timestamp,
    description    VARCHAR(10000) NOT NULL,
    PRIMARY KEY (idBook)
);

CREATE TABLE IF NOT EXISTS Genre
(
    idGenre SERIAL8,
    title   VARCHAR(45) NOT NULL,
    PRIMARY KEY (idGenre)
);

CREATE TABLE IF NOT EXISTS Book_has_Genre
(
    Book_idBook   INT NOT NULL,
    Genre_idGenre INT NOT NULL,
    PRIMARY KEY (Book_idBook, Genre_idGenre),
    FOREIGN KEY (Book_idBook) REFERENCES Book (idBook) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (Genre_idGenre) REFERENCES Genre (idGenre) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS Review
(
    idReview    SERIAL8,
    Rating      INT         NOT NULL,
    Content     VARCHAR(45) NOT NULL,
    TimeAdded   TIMESTAMP DEFAULT current_timestamp,
    Book_idBook INT         NOT NULL,
    PRIMARY KEY (idReview),
    CONSTRAINT fk_Review_Book1 FOREIGN KEY (Book_idBook) REFERENCES Book (idBook) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS Comment
(
    idComment       SERIAL8,
    content         VARCHAR(1000) NOT NULL,
    TimeAdded       TIMESTAMP DEFAULT current_timestamp,
    review_idReview INT       DEFAULT NULL,
    PRIMARY KEY (idComment),
--     PRIMARY KEY (article_idArticle),
    FOREIGN KEY (review_idReview) REFERENCES review (idReview) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS Author
(
    idAuthor    SERIAL8,
    Name        VARCHAR(45) NOT NULL,
    Surname     VARCHAR(45) NOT NULL,
    Website     VARCHAR(45) DEFAULT NULL,
    Description VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (idAuthor)
);

CREATE TABLE IF NOT EXISTS Author_has_Book
(
    Author_idAuthor INT NOT NULL,
    Book_idBook     INT NOT NULL,

    PRIMARY KEY (Author_idAuthor, Book_idBook),
    CONSTRAINT fk_Author_has_Book_Author1
        FOREIGN KEY (Author_idAuthor)
            REFERENCES Author (idAuthor)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT fk_Author_has_Book_Book1
        FOREIGN KEY (Book_idBook)
            REFERENCES Book (idBook)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
