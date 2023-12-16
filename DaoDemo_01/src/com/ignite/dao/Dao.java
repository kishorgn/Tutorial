package com.ignite.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.ignite.helper.ConnectionManager;

public class Dao {

	Connection con ;

	public Dao() throws ClassNotFoundException, SQLException {
		con = ConnectionManager.getConnection(); 
	}
	public void commit() throws SQLException {
		if(con != null) {
			con.commit();
			con.close();
		}
	}
	public void rollback() throws SQLException {
		if(con != null) {
			con.rollback();
			con.close();
		}
	}

}
