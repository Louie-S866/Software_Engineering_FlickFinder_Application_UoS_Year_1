package com.flickfinder.controller;

import java.sql.SQLException;
import com.flickfinder.dao.MovieDAO;
import com.flickfinder.model.Movie;

import io.javalin.http.Context;

/**
 * The controller for the movie endpoints.
 * 
 * The controller acts as an intermediary between the HTTP routes and the DAO.
 * 
 * As you can see each method in the controller class is responsible for
 * handling a specific HTTP request.
 * 
 * Methods a Javalin Context object as a parameter and uses it to send a
 * response back to the client. We also handle business logic in the controller,
 * such as validating input and handling errors.
 *
 * Notice that the methods don't return anything. Instead, they use the Javalin
 * Context object to send a response back to the client.
 */

public class MovieController {

	/**
	 * The movie data access object.
	 */

	private final MovieDAO movieDAO;

	/**
	 * Constructs a MovieController object and initializes the movieDAO.
	 */
	public MovieController(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}

	/**
	 * Returns a list of all movies in the database.
	 * If there is a limit query then the limit is given
	 * If there is no limit then the base limit is 50
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllMovies(Context ctx) {
		try {
			String limit = ctx.queryParam("limit");
			if (limit != null) {
				ctx.json(movieDAO.getAllMovies(Integer.parseInt(limit)));
			} else {
				ctx.json(movieDAO.getAllMovies(50));
			}
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the movie with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMovieById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getMovieById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the stars of a specific movie using its unique id Checks if the movie
	 * exists before continuing
	 * 
	 * @param ctx the Javalin context
	 */
	public void getPeopleByMovieId(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Movie movie = movieDAO.getMovieById(id);
			if (movie == null) {
				ctx.status(404);
				ctx.result("Movie not found");
				return;
			}
			ctx.json(movieDAO.getPeopleByMovieId(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns a list of movies ordered by rating in descending order
	 * for a given year
	 * If there is a limit query and a votes query then the limit and votes
	 * are updated accordingly
	 * If not then the base case for limit is 50 and votes is 1000
	 * 
	 * @param ctx the Javalin context
	 */
	public void getRatingsByYear(Context ctx) {
		int limit = 50;
		int votes = 1000;
		try {
			int year = Integer.parseInt(ctx.pathParam("year"));
			String limitQuery = ctx.queryParam("limit");
			String votesQuery = ctx.queryParam("votes");
			if (limitQuery != null) {
				limit = Integer.parseInt(limitQuery);
			}
			if (votesQuery != null) {
				votes = Integer.parseInt(votesQuery);
			}
			ctx.json(movieDAO.getRatingsByYear(limit, votes, year));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
}