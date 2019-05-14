/* ACTORS */
INSERT INTO actors(full_name) VALUES ('Actor Number1');
INSERT INTO actors(full_name) VALUES ('Actor Number2');
INSERT INTO actors(full_name) VALUES ('Actress Number1');
INSERT INTO actors(full_name) VALUES ('Actress Number2');

/* GENRES */
INSERT INTO genres(name) VALUES ('Horror');
INSERT INTO genres(name) VALUES ('Action');

/* MOVIES */
INSERT INTO movies(title,genre_id,year) VALUES ('Random Movie 1', 1, 2011);
INSERT INTO movies(title,genre_id,year) VALUES ('Random Movie 2', 2, 2012);

/* PERFORMANCES */
INSERT INTO performances(movie_id,actor_id) VALUES (1,1);
INSERT INTO performances(movie_id,actor_id) VALUES (1,2);
INSERT INTO performances(movie_id,actor_id) VALUES (1,3);
INSERT INTO performances(movie_id,actor_id) VALUES (2,1);
INSERT INTO performances(movie_id,actor_id) VALUES (2,3);