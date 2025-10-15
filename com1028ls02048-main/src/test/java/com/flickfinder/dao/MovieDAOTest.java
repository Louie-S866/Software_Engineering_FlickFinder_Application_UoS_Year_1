package com.flickfinder.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

/**
 * Test for the Movie Data Access Object. This uses an in-memory database for
 * testing purposes.
 */

class MovieDAOTest {

	/**
	 * The movie data access object.
	 */

	private MovieDAO movieDAO;

	/**
	 * Seeder
	 */

	Seeder seeder;

	/**
	 * Sets up the database connection and creates the tables. We are using an
	 * in-memory database for testing purposes. This gets passed to the Database
	 * class to get a connection to the database. As it's a singleton class, the
	 * entire application will use the same connection.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		movieDAO = new MovieDAO();

	}

	/**
	 * Tests the getAllMovies method. We expect to get a list of all movies in the
	 * database. We have seeded the database with 5 movies, so we expect to get 5
	 * movies back. At this point, we avoid checking the actual content of the list.
	 */
	@Test
	void testGetAllMovies() {
		try {
			List<Movie> movies = movieDAO.getAllMovies(50);
			assertEquals(5, movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method. We expect to get the movie with the specified
	 * id.
	 */
	@Test
	void testGetMovieById() {
		Movie movie;
		try {
			movie = movieDAO.getMovieById(1);
			assertEquals("The Shawshank Redemption", movie.getTitle());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getMovieById method with an invalid id. Null should be returned.
	 */
	@Test
	void testGetMovieByIdInvalidId() {

		// write an assertThrows for a SQLException
		SQLException thrown = assertThrows(SQLException.class, () -> {
			movieDAO.getMovieById('a');
		});
		assertEquals("Invalid id", thrown.getMessage());

//		try {
//			Movie movie = movieDAO.getMovieById(1000);
//			assertEquals(null, movie);
//		} catch (SQLException e) {
//
//			fail("SQLException thrown");
//			e.printStackTrace();
//		}

	}

	/**
	 * Tests the getPeopleByMovieId method with several IDs
	 */
	@Test
	void testGetPeopleByMovieId() {
		try {
			List<Person> people = movieDAO.getPeopleByMovieId(1);
			assertEquals("Tim Robbins", people.get(0).getName());
			assertEquals("Morgan Freeman", people.get(1).getName());
			assertEquals(1937, people.get(1).getBirth());
			assertEquals(1, people.get(0).getId());

			people = movieDAO.getPeopleByMovieId(2);
			assertEquals("Al Pacino", people.get(0).getName());
			assertEquals(1940, people.get(0).getBirth());
			assertEquals(4, people.get(0).getId());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the limit parameter for getAllMovies()
	 */
	@Test
	void testGetMovieLimit() {
		try {
			List<Movie> movies = movieDAO.getAllMovies(2);
			assertEquals(2, movies.size());

			movies = movieDAO.getAllMovies(4);
			assertEquals(4, movies.size());

			movies = movieDAO.getAllMovies(5);
			assertEquals(5, movies.size());

			movies = movieDAO.getAllMovies(50);
			assertEquals(5, movies.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getRatingsByYear() function with different parameters
	 */
	@Test
	void testGetRatingsByYear() {
		try {
			List<Movie> movies = movieDAO.getRatingsByYear(2, 200, 1972);
			assertEquals("The Godfather", movies.get(0).getTitle());

			movies = movieDAO.getRatingsByYear(1, 200, 2008);
			assertEquals("The Dark Knight", movies.get(0).getTitle());

		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests invalid limits (negative limits including 0)
	 */
	@Test
	void testInvalidLimit() {
		try {
			List<Movie> movies = movieDAO.getRatingsByYear(-2, 200, 1972);
			assertEquals(1, movies.size());

			movies = movieDAO.getAllMovies(-10);
			assertEquals(5, movies.size());

			movies = movieDAO.getAllMovies(0);
			assertEquals(5, movies.size());

		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() {
		seeder.closeConnection();
	}

}