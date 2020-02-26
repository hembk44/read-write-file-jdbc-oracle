package com.imcs.fileAndJDBC;

/**
 * This is an Employee Class with it's attributes
 * 
 * @author Hem BK
 *
 */
public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	
	
	/**
	 * This is an empty constructor that initializes an empty Employee Object
	 * with default values
	 */
	public Employee() {
	}

	/**
	 * @param id the id of an employee
	 * @param firstName the first name of an employee
	 * @param lastName the last name of an employee
	 * @param email the email address of an employee
	 */
	public Employee(int id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * @return the id of an employee
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id of an employee
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the first name of an employee
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the first name of an employee
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the last name of an employee
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the last name of an employee
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email address of an employee
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email address of an employee
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
