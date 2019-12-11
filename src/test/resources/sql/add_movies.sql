INSERT INTO actors(id, name) VALUES (1, 'Leonardo Dicaprio');
INSERT INTO actors(id, name) VALUES (2, 'Brad Pitt');

INSERT INTO genres(id, name) VALUES (1, 'Thriller');

INSERT INTO movies(id, title, genre_id, year) VALUES (1, 'Once upon a time in Hollywood', 1, 2019);
INSERT INTO movies(id, title, genre_id, year) VALUES (2, 'Matrix', 1, 1999);

INSERT INTO performances(movie_id, actor_id) VALUES (1,1);
INSERT INTO performances(movie_id, actor_id) VALUES (1,2);
INSERT INTO performances(movie_id, actor_id) VALUES (2,2);