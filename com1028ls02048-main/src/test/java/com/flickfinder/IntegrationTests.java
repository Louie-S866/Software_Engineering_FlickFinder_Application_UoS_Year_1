package com.flickfinder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flickfinder.util.Database;
import com.flickfinder.util.Seeder;

import io.javalin.Javalin;

/**
 * These are our integration tests. We are testing the application as a whole,
 * including the database.
 */
class IntegrationTests {

	/**
	 * The Javalin app.*
	 */
	Javalin app;

	/**
	 * The seeder object.
	 */
	Seeder seeder;

	/**
	 * The port number. Try and use a different port number from your main
	 * application.
	 */
	int port = 6000;

	/**
	 * The base URL for our test application.
	 */
	String baseURL = "http://localhost:" + port;

	/**
	 * Bootstraps the application before each test.
	 */
	@BeforeEach
	void setUp() {
		var url = "jdbc:sqlite::memory:";
		seeder = new Seeder(url);
		Database.getInstance(seeder.getConnection());
		app = AppConfig.startServer(port);
	}

	/**
	 * Test that the application retrieves a list of all movies. Notice how we are
	 * checking the actual content of the list. At this higher level, we are not
	 * concerned with the implementation details.
	 */

	@Test
	void retrieves_a_list_of_all_movies() {
		given().when().get(baseURL + "/movies").then().assertThat().statusCode(200). // Assuming a successful
		// response returns HTTP
		// 200
				body("id", hasItems(1, 2, 3, 4, 5))
				.body("title",
						hasItems("The Shawshank Redemption", "The Godfather", "The Godfather: Part II",
								"The Dark Knight", "12 Angry Men"))
				.body("year", hasItems(1994, 1972, 1974, 2008, 1957));
	}

	@Test
	void retrieves_a_single_movie_by_id() {

		given().when().get(baseURL + "/movies/1").then().assertThat().statusCode(200). // Assuming a successful
		// response returns HTTP
		// 200
				body("id", equalTo(1)).body("title", equalTo("The Shawshank Redemption")).body("year", equalTo(1994));
	}

	/**
	 * Test the application retrieves a list of all people The seeder has 5 people
	 * in the database so only 5 are tested for
	 */
	@Test
	void retrieves_a_list_of_all_people() {
		given().when().get(baseURL + "/people").then().assertThat().statusCode(200).body("id", hasItems(1, 2, 3, 4, 5))
				.body("name",
						hasItems("Tim Robbins", "Morgan Freeman", "Christopher Nolan", "Al Pacino", "Henry Fonda"))
				.body("birth", hasItems(1958, 1937, 1970, 1940, 1905));
	}

	/**
	 * Test the application retrieves a single person by id
	 * 
	 */
	@Test
	void retrieves_a_single_person_by_id() {
		given().when().get(baseURL + "/people/1").then().assertThat().statusCode(200).body("id", equalTo(1))
				.body("name", equalTo("Tim Robbins")).body("birth", equalTo(1958));

		given().when().get(baseURL + "/people/3").then().assertThat().statusCode(200).body("id", equalTo(3))
				.body("name", equalTo("Christopher Nolan")).body("birth", equalTo(1970));

		given().when().get(baseURL + "/people/5").then().assertThat().statusCode(200).body("id", equalTo(5))
				.body("name", equalTo("Henry Fonda")).body("birth", equalTo(1905));
	}

	/**
	 * Test the application retrieves all stars by movie (id)
	 * 
	 */
	@Test
	void retrieves_people_by_movie_id() {
		given().when().get(baseURL + "/movies/1/stars")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(1, 2))
		.body("name", hasItems("Tim Robbins", "Morgan Freeman"))
		.body("birth", hasItems(1958, 1937));
		
		given().when().get(baseURL + "/movies/2/stars")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(4))
		.body("name", hasItems("Al Pacino"))
		.body("birth", hasItems(1940));
		
		given().when().get(baseURL + "/movies/3/stars")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(4))
		.body("name", hasItems("Al Pacino"))
		.body("birth", hasItems(1940));
	}

	/**
	 * Test the application retrieves all movies starting person (id)
	 * 
	 */
	@Test
	void retrives_movies_starring_person_by_id() {
		given().when().get(baseURL + "/people/2/movies")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(1))
		.body("title", hasItems("The Shawshank Redemption"))
		.body("year", hasItems(1994));
		
		given().when().get(baseURL + "/people/1/movies")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(1))
		.body("title", hasItems("The Shawshank Redemption"))
		.body("year", hasItems(1994));
		
		given().when().get(baseURL + "/people/4/movies")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(2, 3))
		.body("title", hasItems("The Godfather", "The Godfather: Part II"))
		.body("year", hasItems(1972, 1974));
	}
	
	/**
	 * Tests the application retrieves all movies where the results are limited to a certain number
	 * The limits are 2 and 3 in the test
	 */
	@Test
	void retrives_all_movies_with_limit() {
		given().when().get(baseURL + "/movies?limit=2")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(1, 2))
		.body("title", hasItems("The Shawshank Redemption", "The Godfather"))
		.body("year", hasItems(1994, 1972));
		
		given().when().get(baseURL + "/movies?limit=3")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(1, 2, 3))
		.body("title", hasItems("The Shawshank Redemption", "The Godfather", "The Godfather: Part II"))
		.body("year", hasItems(1994, 1972, 1974));
	}
	
	/**
	 * Tests the application retrieves all people where the results are limited to a certain number
	 * The limits are 3 and 4 in the test
	 */
	@Test
	void retrives_all_people_with_limit() {
		given().when().get(baseURL + "/people?limit=3")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(1, 2))
		.body("name", hasItems("Tim Robbins", "Morgan Freeman", "Christopher Nolan"))
		.body("birth", hasItems(1958, 1937, 1970));
		
		given().when().get(baseURL + "/people?limit=4")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(1, 2, 3, 4))
		.body("name", hasItems("Tim Robbins", "Morgan Freeman", "Christopher Nolan", "Al Pacino"))
		.body("birth", hasItems(1958, 1937, 1970, 1940));
	}
	
	/**
	 * Tests the application retrieves all movies and ratings by a given year
	 * Also test the queries -> limit and votes
	 */
	@Test
	void retrives_movies_and_ratings_by_year() {
		given().when().get(baseURL + "/movies/ratings/1994")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(1))
		.body("title", hasItems("The Shawshank Redemption"))
		.body("rating", equalTo(9.3))
		.body("votes", hasItems(2200000))
		.body("year", hasItems(1994));
		
		given().when().get(baseURL + "/movies/ratings/1974?limit=1")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(3))
		.body("title", hasItems("The Godfather: Part II"))
		.body("rating", hasItems(9.3))
		.body("votes", hasItems(1000000))
		.body("year", hasItems(1974));
		
		given().when().get(baseURL + "/movies/ratings/1972?limit=1&votes=2000")
		.then().assertThat().statusCode(200)
		.body("id", hasItems(2))
		.body("title", hasItems("The Godfather"))
		.body("rating", hasItems(9.2))
		.body("votes", hasItems(1500000))
		.body("year", hasItems(1972));
	}

	/**
	 * Tears down the application after each test. We want to make sure that each
	 * test runs in isolation.
	 */
	@AfterEach
	void tearDown() {
		seeder.closeConnection();
		app.stop();
	}

}
