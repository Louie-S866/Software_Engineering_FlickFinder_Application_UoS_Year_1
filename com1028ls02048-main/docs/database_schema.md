## Database Schema

The database, provided as part of your starting project (see `src/main/resources/movies.db`), is provided by IMDB. The database has 407,431 movies and 1,252,264 people; people can be stars and directors. Movies can have ratings, of which there are 225,090. **For this assessment, you will read data from the database, not modify, update or delete.** 


```java

CREATE TABLE movies (
                    id INTEGER,
                    title TEXT NOT NULL,
                    year NUMERIC,
                    PRIMARY KEY(id)
                );
CREATE TABLE stars (
                movie_id INTEGER NOT NULL,
                person_id INTEGER NOT NULL,
                FOREIGN KEY(movie_id) REFERENCES movies(id),
                FOREIGN KEY(person_id) REFERENCES people(id)
            );
CREATE TABLE directors (
                movie_id INTEGER NOT NULL,
                person_id INTEGER NOT NULL,
                FOREIGN KEY(movie_id) REFERENCES movies(id),
                FOREIGN KEY(person_id) REFERENCES people(id)
            );
CREATE TABLE ratings (
                movie_id INTEGER NOT NULL,
                rating REAL NOT NULL,
                votes INTEGER NOT NULL,
                FOREIGN KEY(movie_id) REFERENCES movies(id)
            );
CREATE TABLE people (
                id INTEGER,
                name TEXT NOT NULL,
                birth NUMERIC,
                PRIMARY KEY(id)
            );
```
