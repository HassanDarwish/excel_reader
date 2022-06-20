package com.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import jdbc.ASingleton;

public class categuary {

	int serial;
	String name_title;
	
	private categuary(String name_title) {
		this.name_title=name_title;
	}
	public int getSerial() {
		return serial;
	}
	public categuary setSerial(int serial) {
		this.serial = serial; return this;
	}
	public String getName_title() {
		return name_title;
	}
	public categuary setName_title(String name_title) {
		this.name_title = name_title;
	 return this;
	}
	public void save() {
		try {
		ASingleton jdbc=ASingleton.getInstance();
		Connection con=jdbc.poll();
		Statement stmt=con.createStatement();  
	 
		String query="select * from categuary where name_title='"+getName_title()+"'";
		ResultSet  result=stmt.executeQuery(query);
		
		if (result.next() == false) {
		String sql = "INSERT INTO  categuary(name_title) VALUES ( '"+getName_title()+"' )";
	 
	 
		   stmt.executeUpdate(sql);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			   JOptionPane.showMessageDialog(null, "saved Faild", "Information ",JOptionPane.INFORMATION_MESSAGE);
			 
		}
	}
	
	public static class Builder{
		
		public categuary build(String name_title) {
			categuary new_categuary=new categuary(name_title);
			return new_categuary;
		}
	}
}
