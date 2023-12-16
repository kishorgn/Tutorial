package com.ignite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ignite.beans.Student;
import com.ignite.exception.DatabaseInternalException;
import com.ignite.exception.InvalidStudentIdException;
import com.ignite.helper.ConnectionManager;

public class StudentDao extends Dao {
	
	public StudentDao() throws SQLException, ClassNotFoundException {
		
	}
	
	// find a single record by the ID
	public Student findById(int id) throws ClassNotFoundException, InvalidStudentIdException, DatabaseInternalException {
		
		Scanner sc = new Scanner(System.in);
		Student student = null;
		Connection con = null ;
		try {
			con = ConnectionManager.getConnection();
			con.commit();
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
			con = ConnectionManager.getConnection();
			con.commit();
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
	public boolean save(Student student) throws DatabaseInternalException {
		boolean res = false;
		String sql = "insert into student values (?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getEmail());
			if(pstmt.executeUpdate()==1) {
				res = true;
			}
		} catch (SQLException e) {
			throw new DatabaseInternalException(e);
		}
		
		return res;
	}
	// edit
	public boolean edit(Student student) throws DatabaseInternalException {
		boolean res = false;
		String sql = "update student set name = ?, email = ? where id = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getEmail());
			pstmt.setInt(3, student.getId());
			if(pstmt.executeUpdate() == 1) {
				res = true;
			}
		} catch (SQLException e) {
			throw new DatabaseInternalException(e);
		}
		return res;
	}
	// delete
	public boolean delete(int id) {
		boolean res = false;
		String sql = "delete from student where id = "+id;
		try {
			Statement stmt = con.createStatement();
			if(stmt.executeUpdate(sql) == 1) {
				res = true;
			}
		} catch (SQLException e) {
			try {
				throw new DatabaseInternalException(e);
			} catch (DatabaseInternalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return res;
	}
}