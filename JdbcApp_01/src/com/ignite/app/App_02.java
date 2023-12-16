package com.ignite.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App_02 {

	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/tutorial";
		String user = "root";
		String password = "root";
		Scanner sc = new Scanner(System.in);
		try (
				Connection con = DriverManager.getConnection(url, user, password);
				Statement stmt = con.createStatement();
			){
			Class.forName(driver);

			System.out.print("Enter empno to search : ");
			int empno = sc.nextInt();
			
			String sql = "SELECT * FROM emp WHERE empno = "+empno;
			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				System.out.println("Name : "+rs.getString("ename")+", Salary : "+rs.getInt("sal"));
			}
			else {
				System.out.println("Employee not found with the given empno - "+empno);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
