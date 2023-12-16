package com.ignite.app;

import java.sql.SQLException;
import java.util.List;

import com.ignite.beans.Student;
import com.ignite.dao.StudentDao;
import com.ignite.exception.DatabaseInternalException;

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
		/*
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
		*/
		
		// DML Operations
		StudentDao studentDao = null;
		try {
			studentDao = new StudentDao();
			Student student = new Student(105, "Raju", "raju@msn.com");
			Student student2 = new Student(106, "Balu", "balu@gmail.com");
			if(studentDao.save(student) == true && studentDao.save(student2) && studentDao.delete(106)) {
				studentDao.commit();
				System.out.println("Saved successfully");
			}
			else {
				studentDao.rollback();
				System.out.println("Failed to save");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseInternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Student> students;
		try {
			students = studentDao.findAll();
			for(Student s : students) {
				System.out.println(s);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseInternalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
