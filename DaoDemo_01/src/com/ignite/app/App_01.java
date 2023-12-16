package com.ignite.app;

import java.sql.SQLException;

import com.ignite.beans.Student;
import com.ignite.dao.StudentDao;
import com.ignite.exception.DatabaseInternalException;
import com.ignite.exception.InvalidStudentIdException;

public class App_01 {

	public static void main(String[] args) {
		
		
		// Below code is to retrieve all records 
		/*
		try {
			StudentDao studentDao = new StudentDao();
			List<Student> students = studentDao.findAll();
			for(Student student : students) {
				System.out.println(student);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (DatabaseInternalException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		*/
		
		
		// Below code is to retrieve a single record by ID
		try {
			StudentDao studentDao = new StudentDao();
			Student student = studentDao.findById(101);
			System.out.println(student);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseInternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvalidStudentIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
