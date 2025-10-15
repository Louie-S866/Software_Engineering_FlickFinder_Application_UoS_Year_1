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
 * Test for the Person Data Access Object. This uses an in-memory database for
 * testing purposes.
 */

class PersonDAOTest {

	/**
	 * The person data access object.
	 */

	private PersonDAO personDAO;

	/**
	 * Seeder
	 */

	Seeder seeder;

	/**
	 * Sets up the database connection and creates the tables.
	 * We are using an in-memory database for testing purposes.
	 * This gets passed to the Database class to get a connection to the database.
	 * As it's a singleton class, the entire application will use the same
	 * connection.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		personDAO = new PersonDAO();

	}

	/**
	 * Tests the getAllPeople method.
	 * We expect to get a list of all people in the database.
	 * We have seeded the database with 5 people, so we expect to get 5 persons back.
	 * At this point, we avoid checking the actual content of the list.
	 */
	@Test
	void testGetAllPeople() {
		try {
			List<Person> people = personDAO.getAllPeople(50);
			assertEquals(5, people.size());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getPersonById method.
	 * We expect to get the person with the specified id.
	 */
	@Test
	void testGetPersonById() {
		Person people;
		try {
			people = personDAO.getPersonById(3);
			assertEquals("Christopher Nolan", people.getName());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}

	/**
	 * Tests the getPersonById method with an invalid id. Null should be returned.
	 */
	@Test
	void testGetPersonByIdInvalidId() {
		// write an assertThrows for a SQLException
		SQLException thrown = assertThrows(SQLException.class, () -> {
			personDAO.getPersonById('a');
		});
		assertEquals("Invalid id", thrown.getMessage());

//		try {
//			Person people = personDAO.getPersonById(1000);
//			assertEquals(null, people);
//		} catch (SQLException e) {
//			fail("SQLException thrown");
//			e.printStackTrace();
//		}

	}
	
	/**
	 * Tests the getMoviesStarringPerson method with multiple IDs
	 */
	@Test
	void testGetMoviesStarringPerson() {
		try {
			List<Movie> movies = personDAO.getMoviesStarringPerson(1);
			assertEquals("The Shawshank Redemption", movies.get(0).getTitle());
			assertEquals(1, movies.get(0).getId());
			assertEquals(1994, movies.get(0).getYear());
			
			movies = personDAO.getMoviesStarringPerson(4);
			assertEquals("The Godfather", movies.get(0).getTitle());
			assertEquals(2, movies.get(0).getId());
			assertEquals(1972, movies.get(0).getYear());
		} catch (SQLException e) {
			fail("SQLException thrown");
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests if the limit works on the getAllPeople() function
	 */
	@Test
	void testGetPeopleLimit() {
		try {
			List<Person> people = personDAO.getAllPeople(10);
			assertEquals(5, people.size());
			
			people = personDAO.getAllPeople(1);
			assertEquals(1, people.size());
			
			people = personDAO.getAllPeople(3);
			assertEquals(3, people.size());
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
			List<Person> people = personDAO.getAllPeople(-10);
			assertEquals(5, people.size());
			
			people = personDAO.getAllPeople(0);
			assertEquals(5, people.size());
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