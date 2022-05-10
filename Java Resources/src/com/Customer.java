package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Customer {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer", "root", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertCustomer(int customerId, String firstName, String lastName, String nic, int phone, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into items (`customerId`,`firstName`,`lastName`,`nic`,`phone`,`email`)"
					+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, firstName);
			preparedStmt.setString(3, lastName);
			preparedStmt.setString(4, nic);
			preparedStmt.setInt(5, phone);
			preparedStmt.setString(6, email);
			// execute the statement
			preparedStmt.execute();
			con.close();
//			String newCustomers = readItems();
//			output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	



}
