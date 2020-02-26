package com.imcs.fileAndJDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This Class provides methods to establish an connection to Orcale database, 
 * retrieve the list of employees from table employees from database, retrieve
 * employees based on their IDs, and adds an employee details or record into 
 * table employees_test in the database
 * @author Hem BK
 *
 */
public class FileJDBC {
	// employeeList stores a list of employee objects
	private ArrayList<Employee> employeeList = new ArrayList<Employee>(); 

	/**
	 * Establish a connection to Oracle db using jdbc
	 * @return an Connection Object that starts an connection to oracle db
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		Connection con = null;

		try {
			// load the driver for Oracle
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		} catch (ClassNotFoundException classNotFoundException) {
			classNotFoundException.printStackTrace();
			throw classNotFoundException;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw sqlException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return con;
	}

	/**
	 * 	retrieve the list of employees from employees table
	 * @param con a Connection Object
	 * @return an ArrayList of employees from db
	 */
	public ArrayList<Employee> getEmployeeInfo(Connection con) {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEES");
			System.out.println("Employee Data");

			while (rs.next()) {
				int employeeID = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				Employee emp = new Employee(employeeID, firstName, lastName, email);
				this.employeeList.add(emp);				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return this.employeeList;
	}

	/**
	 * Retrieve an employee record or details based on employee ID
	 * @param con an Connection Object to db
	 * @param empID an ID of an employee
	 * @return an employee object
	 */
	public Employee getEmployeeInfo(Connection con, int empID) {
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		Employee employee = null;
		try {
			pStmt = con.prepareStatement("SELECT * FROM EMPLOYEES_TEST WHERE EMPLOYEE_ID=?");
			pStmt.setInt(1, empID);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				int employeeID = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");

				employee = new Employee(employeeID, firstName, lastName, email);

				System.out
						.println("The employee ID is " + employee.getId() + ", full name is " + employee.getFirstName()
								+ " " + employee.getLastName() + ", and email is " + employee.getEmail());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pStmt.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		return employee;
	}

	/**
	 * add a employee record into employees_test table
	 * @param con an Connection Object to db
	 * @param empID the ID of an employee
	 * @param empFirstName the first name of an employee
	 * @param empLastName the last name of an employee
	 * @param empEmail the email of an employee
	 * @throws Exception
	 */
	public void addEmployeeInfo(Connection con, int empID, String empFirstName, String empLastName, String empEmail)
			throws Exception {
		PreparedStatement pStmt;

		String insertQuery = "insert into employees_test(employee_id, first_name, last_name, email)"
				+ "values(?,?,?,?)";
		try {
			Employee employee = getEmployeeInfo(getConnection(), empID);
			/**
			 * checks if an employee to be added already exists and
			 * adds an employee only if it doesn't exist
			 */
			if (employee == null) {
				pStmt = con.prepareStatement(insertQuery);
				pStmt.setInt(1, empID);
				pStmt.setString(2, empFirstName);
				pStmt.setString(3, empLastName);
				pStmt.setString(4, empEmail);

				int insertCheck = pStmt.executeUpdate();
				if (insertCheck == 1) {
					System.out.println("Employee with ID " + empID + " is added!");
					pStmt.close();
				} else if (insertCheck == 0) {
					System.out.println("Insertion unsuccessful!");
					pStmt.close();
				}
			} else {
				System.out.println("Employee with ID " + empID + " already exists!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
