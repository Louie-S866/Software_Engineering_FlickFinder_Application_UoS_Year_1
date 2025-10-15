package com.flickfinder.controller;

import java.sql.SQLException;

import com.flickfinder.dao.PersonDAO;
import com.flickfinder.model.Person;

import io.javalin.http.Context;

public class PersonController {

	/**
	 * The person data access object.
	 */
	private final PersonDAO personDAO;

	/**
	 * Constructs a PersonController object and initialises the personDAO.
	 */
	public PersonController(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	/**
	 * Returns a list of all the people in the database.
	 * If there is a limit query then the limit is given
	 * If there is no limit then the base limit is 50
	 * 
	 * @param ctx the Javalin context
	 */
	public void getAllPeople(Context ctx) {
		try {
			String limit = ctx.queryParam("limit");
			if (limit != null) {
				ctx.json(personDAO.getAllPeople(Integer.parseInt(limit)));
			} else {
				ctx.json(personDAO.getAllPeople(50));
			}
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Returns the person with the specified id.
	 * 
	 * @param ctx the Javalin context
	 */
	public void getPersonById(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("Person not found");
				return;
			}
			ctx.json(personDAO.getPersonById(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the person exists
	 * Returns the movies that the person starred in
	 * 
	 * @param ctx the Javalin context
	 */
	public void getMoviesStarringPerson(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Person person = personDAO.getPersonById(id);
			if (person == null) {
				ctx.status(404);
				ctx.result("Person not found");
				return;
			}
			ctx.json(personDAO.getMoviesStarringPerson(id));
		} catch (SQLException e) {
			ctx.status(500);
			ctx.result("Database error");
			e.printStackTrace();
		}
	}
}