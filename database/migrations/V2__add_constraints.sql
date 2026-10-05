/* =========================================================
   TABLE: AWARD
   ========================================================= */

ALTER TABLE award
    ADD CONSTRAINT fk_award_organization
        FOREIGN KEY (organization_id) REFERENCES award_organization(id),

    ADD CONSTRAINT uq_award_organization_id_name
        UNIQUE (organization_id, name);


/* =========================================================
   TABLE: IMAGE
   ========================================================= */

ALTER TABLE image
    ADD CONSTRAINT fk_image_movie
        FOREIGN KEY (movie_id) REFERENCES movie(id),

    ADD CONSTRAINT fk_image_person
        FOREIGN KEY (person_id) REFERENCES person(id),

    ADD CONSTRAINT ck_image_owner
        CHECK ((movie_id IS NOT NULL AND person_id IS NULL) OR (movie_id IS NULL AND person_id IS NOT NULL));


/* =========================================================
   TABLE: MOVIE
   ========================================================= */

ALTER TABLE movie
    ADD CONSTRAINT uq_movie_external_id_external_source
        UNIQUE (external_id, external_source);


/* =========================================================
   TABLE: MOVIE_AWARD
   ========================================================= */

ALTER TABLE movie_award
    ADD CONSTRAINT fk_movie_award_movie
        FOREIGN KEY (movie_id) REFERENCES movie(id),

    ADD CONSTRAINT fk_movie_award_person
        FOREIGN KEY (person_id) REFERENCES person(id),

    ADD CONSTRAINT fk_movie_award_award
        FOREIGN KEY (award_id) REFERENCES award(id),

    ADD CONSTRAINT ck_movie_award_result
        CHECK (result IN ('WINNER', 'NOMINATED'));


/* =========================================================
   TABLE: MOVIE_COUNTRY
   ========================================================= */

ALTER TABLE movie_country
    ADD CONSTRAINT fk_movie_country_movie
        FOREIGN KEY (movie_id) REFERENCES movie(id),

    ADD CONSTRAINT fk_movie_country_country
        FOREIGN KEY (country_id) REFERENCES country(id);


/* =========================================================
   TABLE: MOVIE_CREDIT
   ========================================================= */

ALTER TABLE movie_credit
    ADD CONSTRAINT fk_movie_credit_movie
        FOREIGN KEY (movie_id) REFERENCES movie(id),

    ADD CONSTRAINT fk_movie_credit_person
        FOREIGN KEY (person_id) REFERENCES person(id),

    ADD CONSTRAINT fk_movie_credit_role
        FOREIGN KEY (role_id) REFERENCES credit_role(id);


/* =========================================================
   TABLE: MOVIE_GENRE
   ========================================================= */

ALTER TABLE movie_genre
    ADD CONSTRAINT fk_movie_genre_movie
        FOREIGN KEY (movie_id) REFERENCES movie(id),

    ADD CONSTRAINT fk_movie_genre_genre
        FOREIGN KEY (genre_id) REFERENCES genre(id);


/* =========================================================
   TABLE: MOVIE_RATING
   ========================================================= */

ALTER TABLE movie_rating
    ADD CONSTRAINT fk_movie_rating_movie
        FOREIGN KEY (movie_id) REFERENCES movie(id),

    ADD CONSTRAINT fk_movie_rating_source
        FOREIGN KEY (rating_source_id) REFERENCES rating_source(id),

    ADD CONSTRAINT uq_movie_rating_movie_id_rating_source_id
        UNIQUE (movie_id, rating_source_id);


/* =========================================================
   TABLE: PERSON
   ========================================================= */

ALTER TABLE person
    ADD CONSTRAINT uq_person_external_id_external_source
        UNIQUE (external_id, external_source);


/* =========================================================
   TABLE: PERSON_NATIONALITY
   ========================================================= */

ALTER TABLE person_nationality
    ADD CONSTRAINT fk_person_nationality_person
        FOREIGN KEY (person_id) REFERENCES person(id),

    ADD CONSTRAINT fk_person_nationality_country
        FOREIGN KEY (country_id) REFERENCES country(id);


/* =========================================================
   TABLE: USER_MOVIE
   ========================================================= */

ALTER TABLE user_movie
    ADD CONSTRAINT fk_user_movie_user
        FOREIGN KEY (user_id) REFERENCES app_user(id),

    ADD CONSTRAINT fk_user_movie_movie
        FOREIGN KEY (movie_id) REFERENCES movie(id),

    ADD CONSTRAINT uq_user_movie_user_id_movie_id
        UNIQUE (user_id, movie_id),

    ADD CONSTRAINT ck_user_movie_priority
        CHECK (priority BETWEEN 1 AND 3),

    ADD CONSTRAINT ck_user_movie_status
        CHECK (status IN ('WANT_TO_WATCH', 'WATCHED'));