package com.flickfinder.model;

public class MovieRating extends Movie {
	
	private double rating;
	private int votes;
	
	/**
	 * Constructs a Movie object with the specified id, title, and year.
	 *
	 * @param id    the unique identifier of the movie
	 * @param title the title of the movie
	 * @param year  the release year of the movie
	 */
	public MovieRating(int id, String title, int year, double rating, int votes) {
		super(id, title, year);
		this.rating = rating;
		this.votes = votes;
	}
	
	/**
	 * Returns the rating of the movie.
	 *
	 * @return the rating of the movie
	 */
	public double getRating() {
		return this.rating;
	}

	/**
	 * Sets the rating of the movie.
	 *
	 * @param rating the rating of the movie to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	/**
	 * Returns total votes of the movie.
	 *
	 * @return the total number of votes relating to the movie
	 */
	public int getVotes() {
		return this.votes;
	}

	/**
	 * Sets the total number of votes of the movie.
	 *
	 * @param votes the number of votes to set
	 */
	public void setVotes(int votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "MovieRating [rating=" + rating + ", votes=" + votes + "]";
	}
}
