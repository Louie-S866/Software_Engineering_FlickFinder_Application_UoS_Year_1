package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.MovieRating;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * The Data Access Object for the Movie table.
 *
 * This class is responsible for getting data from the Movies table in the
 * database.
 *
 */
public class MovieDAO {

	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLiteMovieDAO object and gets the database connection.
	 *
	 */
	public MovieDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all movies in the database.
	 *
	 * @return a list of all movies in the database
	 * 
	 * @throws SQLException if a database error occurs
	 */

	public List<Movie> getAllMovies(int limit) throws SQLException {
		List<Movie> movies = new ArrayList<>();

		if (limit <= 0) {
			limit = 50;
		}

		String statement = "select * from movies LIMIT ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, limit);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
		}

		return movies;
	}

	/**
	 * Returns the movie with the specified id.
	 *
	 * @param id the id of the movie
	 * 
	 * @return the movie with the specified id
	 * 
	 * @throws SQLException if a database error occurs
	 */
	public Movie getMovieById(int id) throws SQLException {
		String statement = "select * from movies where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year"));
		}

		// return null if the id does not return a movie.
		// return null;

		// throw an SQLException if the id does not return a movie
		throw new SQLException("Invalid id");
	}

	/**
	 * Returns the stars of a specific movie using its unique id
	 *
	 * @param id the id of the movie
	 * 
	 * @return a list of people who star is a specific movie
	 * 
	 * @throws SQLException if a database error occurs
	 */
	public List<Person> getPeopleByMovieId(int movieId) throws SQLException {
		List<Person> people = new ArrayList<>();

		String statement = "SELECT p.id, p.name, p.birth FROM people p "
				+ "INNER JOIN stars s on p.id = s.person_id "
				+ "INNER JOIN movies m on s.movie_id = m.id "
				+ "WHERE m.id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, movieId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}

		return people;
	}

	/**
	 * Returns a list of movies ordered by rating in descending order for a given
	 * year The results are also limited to the user input or if there is no user
	 * input the results are limited to 50 (base case) This is the same wit the
	 * votes except the base case for votes is 1000
	 *
	 * @param limit the value at which the results should be limited to
	 * 
	 * @param votes the value at which the movie's votes should be greater than
	 * 
	 * @param year  the year of the movie
	 * 
	 * @return a list of movies from a particular year where the votes are greater
	 *         than 'votes' param or 1000
	 * 
	 * @throws SQLException if a database error occurs
	 */
	public List<Movie> getRatingsByYear(int limit, int votes, int year) throws SQLException {
		List<Movie> movies = new ArrayList<>();

		if (limit <= 0) {
			limit = 50;
		}

		if (votes <= 0) {
			votes = 1000;
		}

		String statement = "SELECT m.id, m.title, r.rating, r.votes, m.year FROM movies m "
				+ "INNER JOIN ratings r ON m.id = r.movie_id "
				+ "WHERE r.votes >= ? AND m.year = ? "
				+ "ORDER BY r.rating DESC "
				+ "LIMIT ? ";

		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, votes);
		ps.setInt(2, year);
		ps.setInt(3, limit);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			movies.add(new MovieRating(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getDouble("rating"), rs.getInt("votes")));
		}

		return movies;
	}
}