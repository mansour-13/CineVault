/* =========================================================
   TABLE: CREDIT_ROLE
   ========================================================= */

INSERT INTO credit_role (name)
VALUES
    ('ACTOR'),
    ('DIRECTOR'),
    ('WRITER'),
    ('PRODUCER');

/* =========================================================
   TABLE: GENRE
   ========================================================= */

INSERT INTO genre (name)
VALUES
    ('ACTION'),
    ('ADVENTURE'),
    ('ANIMATION'),
    ('COMEDY'),
    ('CRIME'),
    ('DOCUMENTARY'),
    ('DRAMA'),
    ('FAMILY'),
    ('FANTASY'),
    ('HISTORY'),
    ('HORROR'),
    ('MUSIC'),
    ('MYSTERY'),
    ('ROMANCE'),
    ('SCIENCE FICTION'),
    ('THRILLER'),
    ('WAR'),
    ('WESTERN'); 

/* =========================================================
   TABLE: RATING_SOURCE
   ========================================================= */

INSERT INTO rating_source (name, website, max_score)
VALUES
    ('IMDb', 'https://www.imdb.com', 10.00),
    ('Rotten Tomatoes', 'https://www.rottentomatoes.com', 100.00),
    ('Metacritic', 'https://www.metacritic.com', 100.00);