package com.flickfinder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for the person model
 * 
 * 
 */

class PersonTest {

	/**
	 * The person object to be tested.
	 */
	private Person person;

	/**
	 * Set up the person object before each test.
	 *
	 */
	@BeforeEach
	public void setUp() {
		person = new Person(1, "Garry", 1990);
	}

	/**
	 * Test the person object is created with the correct values.
	 */
	@Test
	public void testPersonCreated() {
		assertEquals(1, person.getId());
		assertEquals("Garry", person.getName());
		assertEquals(1990, person.getBirth());
	}

	/**
	 * Test the person object is created with the correct values.
	 */
	@Test
	public void testPersonSetters() {
		person.setId(2);
		person.setName("Joe");
		person.setBirth(1997);
		assertEquals(2, person.getId());
		assertEquals("Joe", person.getName());
		assertEquals(1997, person.getBirth());
	}
}