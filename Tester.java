package com.imcs.fileAndJDBC;

import java.io.File;
//import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		try {
		FileReaderAndWriter fileReaderAndWriter = new FileReaderAndWriter();
//		File readFile = new File("C:\\test\\employees.txt");
//		fileReaderAndWriter.readFile(readFile);
		File writeFile = new File("c:\\test\\writeEmployees.txt");
		fileReaderAndWriter.writeToFile(writeFile);
		}
		catch(Exception e) {
			System.out.println("Exception Details: \n"+e.getClass()+ " "+ e.getMessage()+"\n");
		}
	}

}
