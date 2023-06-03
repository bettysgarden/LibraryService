INSERT INTO book (title, date_published, description)
VALUES ('The Seven Husbands of Evelyn Hugo', 2017,
        'Aging and reclusive Hollywood movie icon Evelyn Hugo is finally ready to tell the truth about her glamorous and scandalous life. But when she chooses unknown magazine reporter Monique Grant for the job, no one is more astounded than Monique herself. Why her? Why now?');



INSERT INTO genre (title)
VALUES ('Fiction');
INSERT INTO genre (title)
VALUES ('Romance');
INSERT INTO genre (title)
VALUES ('Historical Fiction');

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'The Seven Husbands of Evelyn Hugo'),
        (SELECT id FROM genre WHERE title LIKE 'Fiction'));

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'The Seven Husbands of Evelyn Hugo'),
        (SELECT id FROM genre WHERE title LIKE 'Romance'));

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'The Seven Husbands of Evelyn Hugo'),
        (SELECT id FROM genre WHERE title LIKE 'Historical Fiction'));
INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'The Seven Husbands of Evelyn Hugo'),
        (SELECT id FROM genre WHERE title LIKE 'Contemporary'));

INSERT INTO author (name, website, description)
VALUES ('Taylor Jenkins Reid',
        'http://www.taylorjenkinsreid.com',
        'Taylor Jenkins Reid is the New York Times bestselling author of Carrie Soto Is Back, Malibu Rising, Daisy Jones & The Six, and The Seven Husbands of Evelyn Hugo, as well as four other novels. She lives in Los Angeles. You can follow her on Instagram @tjenkinsreid.');

INSERT INTO author_has_book (author_idauthor, book_idbook)
VALUES ((Select id from author where name LIKE 'Taylor Jenkins Reid'),
        (SELECT id FROM book WHERE title LIKE 'The Seven Husbands of Evelyn Hugo')
        );
---------------------------------------------

INSERT INTO book (title, date_published, description)
VALUES ('Book Lovers',
        2022,
        'Nora Stephens’ life is books—she’s read them all—and she is not that type of heroine. Not the plucky one, not the laidback dream girl, and especially not the sweetheart. In fact, the only people Nora is a heroine for are her clients, for whom she lands enormous deals as a cutthroat literary agent, and her beloved little sister Libby.

        Which is why she agrees to go to Sunshine Falls, North Carolina for the month of August when Libby begs her for a sisters’ trip away—with visions of a small-town transformation for Nora, who she’s convinced needs to become the heroine in her own story. But instead of picnics in meadows, or run-ins with a handsome country doctor or bulging-forearmed bartender, Nora keeps bumping into Charlie Lastra, a bookish brooding editor from back in the city. It would be a meet-cute if not for the fact that they’ve met many times and it’s never been cute.

        If Nora knows she’s not an ideal heroine, Charlie knows he’s nobody’s hero, but as they are thrown together again and again—in a series of coincidences no editor worth their salt would allow—what they discover might just unravel the carefully crafted stories they’ve written about themselves.');

INSERT INTO genre (title)
VALUES ('Contemporary');
INSERT INTO genre (title)
VALUES ('Contemporary Romance');
INSERT INTO genre (title)
VALUES ('Books about books');

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Book Lovers'),
        (SELECT id FROM genre WHERE title LIKE 'Fiction'));

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Book Lovers'),
        (SELECT id FROM genre WHERE title LIKE 'Romance'));

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Book Lovers'),
        (SELECT id FROM genre WHERE title LIKE 'Contemporary'));

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Book Lovers'),
        (SELECT id FROM genre WHERE title LIKE 'Contemporary Romance'));

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Book Lovers'),
        (SELECT id FROM genre WHERE title LIKE 'Books about books'));

INSERT INTO author (name, description)
VALUES ('Emily Henry',
        'Emily Henry is the #1 New York Times bestselling author of Book Lovers, People We Meet on Vacation, and Beach Read, as well as the forthcoming Happy Place. She lives and writes in Cincinnati and the part of Kentucky just beneath it.
        Find her on Instagram @EmilyHenryWrites.');

INSERT INTO author_has_book (author_idauthor, book_idbook)
VALUES (        (Select id from author where name LIKE 'Emily Henry'),
        (SELECT id FROM book WHERE title LIKE 'Book Lovers'));


-------------------------------------------------------------------
INSERT INTO book (title, date_published, description)
VALUES ('Daisy Jones & The Six',
        2019,
        'In 1977, Daisy Jones & The Six were on top of the world; the band had risen from obscurity to fame, and then, after a sold-out show at Chicago''s Soldier Field, they called it quits; now, decades later, the band members agree to reveal the truth.');


INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Daisy Jones & The Six'),
        (SELECT id FROM genre WHERE title LIKE 'Fiction'));

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Daisy Jones & The Six'),
        (SELECT id FROM genre WHERE title LIKE 'Romance'));

INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Daisy Jones & The Six'),
        (SELECT id FROM genre WHERE title LIKE 'Historical Fiction'));
INSERT INTO book_has_genre (book_idbook, genre_idgenre)
VALUES ((SELECT id FROM book WHERE title LIKE 'Daisy Jones & The Six'),
        (SELECT id FROM genre WHERE title LIKE 'Contemporary'));

INSERT INTO author_has_book (author_idauthor, book_idbook)
VALUES ((Select id from author where name LIKE 'Taylor Jenkins Reid'),
        (SELECT id FROM book WHERE title LIKE 'Daisy Jones & The Six')
        );