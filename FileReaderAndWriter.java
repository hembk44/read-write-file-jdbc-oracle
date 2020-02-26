package com.imcs.fileAndJDBC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * This class reads data from a file(.txt file) and adds to
 * table employees_test in Oracle database. It also retrieves
 * a list of employee from table employees and writes employees
 * details to a file.
 * @author Hem BK
 *
 */
public class FileReaderAndWriter {

	/**
	 * @param file an File object to read data from
	 * @throws Exception
	 */
	public void readFile(File file) throws Exception {
		BufferedReader bufferedreader = null;
		String line;
		Employee employee;
		FileJDBC fileJdbc = new FileJDBC();
		Connection con = null;
		try {
			System.out.println("Reading from file...");
			FileReader fileReader = new FileReader(file);
			bufferedreader = new BufferedReader(fileReader);
			line = bufferedreader.readLine();

			while (line != null) {
				String[] strArray = line.split(":");
				employee = new Employee(Integer.parseInt(strArray[0]), strArray[1], strArray[2], strArray[3]);
				con = fileJdbc.getConnection();
				fileJdbc.addEmployeeInfo(con, employee.getId(), employee.getFirstName(), employee.getLastName(),
						employee.getEmail());
				line = bufferedreader.readLine();
			}
			System.out.println("Successfully read data from " +file.getName() +" file and added to table employees_test...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				bufferedreader.close();
				con.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}

	}

	/**
	 * @param file an File object to read data from
	 * @throws Exception
	 */
	public void writeToFile(File file) throws Exception {
		
		BufferedWriter bufferedWriter = null;
		FileJDBC fileJDBC = new FileJDBC();
		String employeeToWrite="";
		try {
			System.out.println("writing to file...");
			
			fileJDBC = new FileJDBC();
			ArrayList<Employee> employeeList = fileJDBC.getEmployeeInfo(fileJDBC.getConnection());
			for (int i = 0; i < employeeList.size(); i++) {
				Employee emp = employeeList.get(i);
				employeeToWrite += emp.getId() + ":"+emp.getFirstName()+ ":"+emp.getLastName()+":"+emp.getEmail()+"\n";
				// use commented code to append data retrieved from db to data in file
				/*
				FileWriter fileWriter = new FileWriter(file, true);
				FileWriter fileWriter = new FileWriter(file);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(employeeToWrite);
				bufferedWriter.newLine();
				bufferedWriter.flush();
				*/
			}
			FileWriter fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(employeeToWrite);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			System.out.println("Successfully retrieved data from table employees and written to " + file.getName()+" file ...");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			throw ex;
		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			bufferedWriter.close();
		}

	}
}
