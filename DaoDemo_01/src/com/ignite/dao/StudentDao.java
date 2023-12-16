package com.ignite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ignite.beans.Student;
import com.ignite.exception.DatabaseInternalException;
import com.ignite.exception.InvalidStudentIdException;

public class StudentDao{
	
	public StudentDao() throws SQLException, ClassNotFoundException {
		
	}
	
	// find a single record by the ID
	public Student findById(int id) throws ClassNotFoundException, InvalidStudentIdException, DatabaseInternalException {
		
		Scanner sc = new Scanner(System.in);
		Student student = null;
		Connection con = null ;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/tutorial";
			String user = "root";
			String password = "root";
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			String sql = "select * from student where id = "+id;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				student = new Student(id, name, email);
			}
			else {
				throw new InvalidStudentIdException("Student not found with id : "+id);
			}
		}
		catch(SQLException e) {
			throw new DatabaseInternalException(e);
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DatabaseInternalException(e);
			}
		}
		return student;
	}
	// find All students
	public List<Student> findAll() throws ClassNotFoundException, DatabaseInternalException {
		
		ArrayList<Student> students = new ArrayList<>();
		Connection con = null ;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/tutorial";
			String user = "root";
			String password = "root";
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			Statement stmt = con.createStatement();
			String sql = "select * from student";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Student student = new Student(id, name, email);
				students.add(student);
			}
		}
		catch(SQLException e) {
			throw new DatabaseInternalException(e);
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DatabaseInternalException(e);
			}
		}
		return students;
	}
	// save
	// edit
	// delete

}
