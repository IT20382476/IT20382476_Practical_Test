package com;

import java.sql.*;

public class Customer {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customermanagement", "root", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertCustomer(String firstName, String lastName, String nic, int phone, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customer (`customerId`,`firstName`,`lastName`,`nic`,`phone`,`email`)"
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
			String newCustomers = readCustomers();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readCustomers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "<table border='1'><tr><th>First Name</th>" + "<th>Last Name</th>"
					+ "<th>Nic</th>" + "<th>Phone Number</th>" + "<th>Email</th>" 
					+ "<th>Update</th><th>Remove</th></tr>";
			
			
			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
//				int customerId = rs.getInt("customerId");
				String customerId= Integer.toString(rs.getInt("customerId"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String nic = rs.getString("nic");
				String phone= Integer.toString(rs.getInt("phone"));
				String email = rs.getString("email");
//				String address = rs.getString("address");
				// Add into the html table
				output += "<tr><td><input id='hidCustomerIDUpdate' name='hidCustomerIDUpdate' type='hidden' value='" + customerId
						+ "'>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
//				output += "<td>" + address+ "</td>";
				// buttons
				
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-customerid='" + customerId + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-customerid='" + customerId + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the customers.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateCustomer(String customerId, String firstName, String lastName, String nic, String phone,String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE customer SET firstName=?,lastName=?,nic=?,phone=?,email=? WHERE customerId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, firstName);
			preparedStmt.setString(2, lastName);
			preparedStmt.setString(3, nic);
			preparedStmt.setInt(4, Integer.parseInt(phone));
			preparedStmt.setString(5, email);
			preparedStmt.setInt(6, Integer.parseInt(customerId));
			
			// execute the statement
			preparedStmt.execute();
						

			con.close();
			String newCustomers = readCustomers();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteCustomer(String customerId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from customer where customerId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newCustomers = readCustomers();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
