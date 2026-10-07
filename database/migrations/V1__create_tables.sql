/* =========================================================
   TABLE: APP_USER
   ========================================================= */

CREATE TABLE app_user (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username        VARCHAR(100)    NOT NULL UNIQUE,
    email           VARCHAR(255)    NOT NULL UNIQUE,
    password        VARCHAR(255)    NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);


/* =========================================================
   TABLE: AWARD
   ========================================================= */

CREATE TABLE award (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    organization_id INTEGER         NOT NULL,
    name            VARCHAR(100)
);


/* =========================================================
   TABLE: AWARD_ORGANIZATION
   ========================================================= */

CREATE TABLE award_organization (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(100)    NOT NULL UNIQUE,
    website         VARCHAR(255)
);


/* =========================================================
   TABLE: COUNTRY
   ========================================================= */

CREATE TABLE country (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(100)    NOT NULL UNIQUE,
    iso_code        VARCHAR(2)      NOT NULL UNIQUE
);


/* =========================================================
   TABLE: CREDIT_ROLE
   ========================================================= */

CREATE TABLE credit_role (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(100)    NOT NULL UNIQUE
);


/* =========================================================
   TABLE: GENRE
   ========================================================= */

CREATE TABLE genre (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(100)    NOT NULL UNIQUE
);


/* =========================================================
   TABLE: IMAGE
   ========================================================= */

CREATE TABLE image (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    movie_id        INTEGER,
    person_id       INTEGER,
    source          VARCHAR(100),
    external_path   VARCHAR(1000),
    storage_path    VARCHAR(1000),
    width           INTEGER,
    height          INTEGER,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);


/* =========================================================
   TABLE: MOVIE
   ========================================================= */

CREATE TABLE movie (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title           VARCHAR(255)    NOT NULL,
    original_title  VARCHAR(255),
    overview        TEXT,
    release_date    DATE,
    runtime_minutes INTEGER,
    external_id     VARCHAR(100),
    external_source VARCHAR(50),
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);


/* =========================================================
   TABLE: MOVIE_AWARD
   ========================================================= */

CREATE TABLE movie_award (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    movie_id        INTEGER         NOT NULL,
    award_id        INTEGER         NOT NULL,
    person_id       INTEGER,
    award_year      INTEGER,
    result          VARCHAR(20)     NOT NULL
);

/* =========================================================
   TABLE: MOVIE_COUNTRY
   ========================================================= */

CREATE TABLE movie_country (
    movie_id        INTEGER         NOT NULL,
    country_id      INTEGER         NOT NULL,
    PRIMARY KEY (movie_id, country_id)
);

/* =========================================================
   TABLE: MOVIE_CREDIT
   ========================================================= */

CREATE TABLE movie_credit (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    movie_id        INTEGER         NOT NULL,
    person_id       INTEGER         NOT NULL,
    role_id         INTEGER         NOT NULL,
    character_name  VARCHAR(255),
    credit_order    INTEGER
);


/* =========================================================
   TABLE: MOVIE_GENRE
   ========================================================= */

CREATE TABLE movie_genre (
    movie_id        INTEGER         NOT NULL,
    genre_id        INTEGER         NOT NULL,
    PRIMARY KEY (movie_id, genre_id)
);


/* =========================================================
   TABLE: MOVIE_RATING
   ========================================================= */

CREATE TABLE movie_rating (
    id               INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    movie_id         INTEGER        NOT NULL,
    rating_source_id INTEGER        NOT NULL,
    score            NUMERIC(5,2)   NOT NULL,
    vote_count       INTEGER,
    updated_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


/* =========================================================
   TABLE: PERSON
   ========================================================= */

CREATE TABLE person (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(100)    NOT NULL,
    data_of_birth   DATE,
    place_of_birth  VARCHAR(255),
    biography       TEXT,
    external_id     VARCHAR(100),
    external_source VARCHAR(50),
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);


/* =========================================================
   TABLE: PERSON_NATIONALITY
   ========================================================= */

CREATE TABLE person_nationality (
    person_id       INTEGER         NOT NULL,
    country_id      INTEGER         NOT NULL,
    PRIMARY KEY (person_id, country_id)
);


/* =========================================================
   TABLE: RATING_SOURCE
   ========================================================= */

CREATE TABLE rating_source (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name            VARCHAR(100)    NOT NULL UNIQUE,
    website         VARCHAR(255),
    max_score       NUMERIC(5,2)  
);


/* =========================================================
   TABLE: USER_MOVIE
   ========================================================= */

CREATE TABLE user_movie (
    id              INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id         INTEGER         NOT NULL,
    movie_id        INTEGER         NOT NULL,
    status          VARCHAR(20)     NOT NULL,
    priority        INTEGER,
    personal_rating NUMERIC(5,2),
    personal_review TEXT,
    added_at        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);