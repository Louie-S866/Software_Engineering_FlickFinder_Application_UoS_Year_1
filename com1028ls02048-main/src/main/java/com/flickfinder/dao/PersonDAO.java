package com.flickfinder.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flickfinder.model.Movie;
import com.flickfinder.model.Person;
import com.flickfinder.util.Database;

/**
 * TODO: Implement this class
 * 
 */
public class PersonDAO {
	
	/**
	 * The connection to the database.
	 */
	private final Connection connection;

	/**
	 * Constructs a SQLiteMovieDAO object and gets the database connection.
	 * 
	 */
	public PersonDAO() {
		Database database = Database.getInstance();
		connection = database.getConnection();
	}

	/**
	 * Returns a list of all the people in the database.
	 * 
	 * @return a list of all the people in the database
	 * @throws SQLException if a database error occurs
	 */
	public List<Person> getAllPeople(Integer limit) throws SQLException {
		List<Person> people = new ArrayList<>();
		
		if (limit <= 0) {
			limit = 50;
		}
		
		String statement = "select * from people LIMIT ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, limit);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			people.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth")));
		}
		
		return people;
	}

	/**
	 * Returns the person with the specified id.
	 * 
	 * @param id the id of the person
	 * @return the person with the specified id
	 * @throws SQLException if a database error occurs
	 */
	public Person getPersonById(int id) throws SQLException {
		String statement = "select * from people where id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return new Person(rs.getInt("id"), rs.getString("name"), rs.getInt("birth"));
		}
		
		// return null if the id does not return a person.
		// return null;
		
		// Throws a SQLException if the id does not return a person
		throw new SQLException("Invalid id");
	}
	
	/**
	 * Returns the movies the person starred in
	 * The person is found / specified with its unique id
	 * 
	 * @param id the id of the person
	 * @return a list of movies which the person starred in
	 * @throws SQLException if a database error occurs
	 */
	public List<Movie> getMoviesStarringPerson(int movieId) throws SQLException {
		List<Movie> movies = new ArrayList<>();
		
		String statement = "SELECT m.id, m.title , m.year FROM movies m "
				+ "INNER JOIN stars s on m.id = s.movie_id "
				+ "INNER JOIN people p on s.person_id = p.id "
				+ "WHERE p.id = ?";
		PreparedStatement ps = connection.prepareStatement(statement);
		ps.setInt(1, movieId);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			movies.add(new Movie(rs.getInt("id"), rs.getString("title"), rs.getInt("year")));
		}
		
		return movies;
	}
}