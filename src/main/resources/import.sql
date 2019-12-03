/* actors */
INSERT INTO actors(id, name) VALUES (1, 'Leonardo Dicaprio');
INSERT INTO actors(id, name) VALUES (2, 'Brad Pitt');
INSERT INTO actors(id, name) VALUES (3, 'Richard Gere');
INSERT INTO actors(id, name) VALUES (4, 'Keanu Reeves');
INSERT INTO actors(id, name) VALUES (5, 'Laurence Fishburne');
INSERT INTO actors(id, name) VALUES (6, 'Julia Roberts');
INSERT INTO actors(id, name) VALUES (7, 'Jack Nicholson');

/* genres */
INSERT INTO genres(id, name) VALUES (1, 'Thriller');
INSERT INTO genres(id, name) VALUES (2, 'Sci-fy');
INSERT INTO genres(id, name) VALUES (3, 'Horror');
INSERT INTO genres(id, name) VALUES (4, 'Action');
INSERT INTO genres(id, name) VALUES (5, 'Romantic');

/* movies */
INSERT INTO movies(id, title, genre_id, year) VALUES (1, 'Once upon a time in Hollywood', 1, 2019);
INSERT INTO movies(id, title, genre_id, year) VALUES (2, 'Matrix', 2, 1999);
INSERT INTO movies(id, title, genre_id, year) VALUES (3, 'John Wick', 4, 2014);
INSERT INTO movies(id, title, genre_id, year) VALUES (4, 'Pretty woman', 5, 1990);
INSERT INTO movies(id, title, genre_id, year) VALUES (5, 'The shining', 3, 1980);

/* performances */
INSERT INTO performances(movie_id, actor_id) VALUES (1,1);
INSERT INTO performances(movie_id, actor_id) VALUES (1,2);
INSERT INTO performances(movie_id, actor_id) VALUES (2,4);
INSERT INTO performances(movie_id, actor_id) VALUES (2,5);
INSERT INTO performances(movie_id, actor_id) VALUES (3,4);
INSERT INTO performances(movie_id, actor_id) VALUES (4,3);
INSERT INTO performances(movie_id, actor_id) VALUES (4,6);
INSERT INTO performances(movie_id, actor_id) VALUES (5,7);