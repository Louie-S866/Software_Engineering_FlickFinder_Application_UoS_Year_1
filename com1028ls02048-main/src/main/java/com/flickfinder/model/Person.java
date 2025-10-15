package com.flickfinder.model;

public class Person {

	int id;
	String name;
	int birth;

	/**
	 * Constructs a Person object with the specified id, name, and birth.
	 *
	 * @param id    the unique identifier of the person.
	 * @param name  the person's name.
	 * @param birth the birth date of the person.
	 */
	public Person(int id, String name, int birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
	}

	/**
	 * Gets the unique identifier of the person object.
	 *
	 * @return the unique identifier of the person object.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Gets the name of the person object.
	 *
	 * @return the name of the person object.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the birth of the person object.
	 *
	 * @return the birth of the person object.
	 */
	public int getBirth() {
		return this.birth;
	}
	
	/**
	 * Sets the unique identifier of the person object.
	 *
	 * @param unique identifier of the person to set.
	 */
	public void setId(int newId) {
		this.id = newId;
	}
	
	/**
	 * Sets the name of the person object.
	 *
	 * @param name of the person to set.
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	
	/**
	 * Sets the birth date of the person object.
	 * birth date is just an integer not an actual date.
	 *
	 * @param birth of the person to set.
	 */
	public void setBirth(int newBirth) {
		this.birth = newBirth;
	}

	/**
	 * Returns a string representation of the Person object.
	 *
	 * @return a string representation of the Person object
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birth=" + birth + "]";
	}
}