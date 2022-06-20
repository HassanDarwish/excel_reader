package com.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import jdbc.ASingleton;

public class company_info {

	String name_title;
	String tel1;
	String email;
	String website;
	String mobil1;
	String mobil2;
	String mobil3;
	String mobil4;
	String tel2;
	String mobil5;
	/****************************added at day20-6-2022*/
	String location;
	String categury;
	
	public void save() {
		try {
		ASingleton jdbc=ASingleton.getInstance();
		Connection con=jdbc.poll();
		Statement stmt=con.createStatement();  
	 
		String query="select * from company_info where name_title='"+getName_title()+"'";
		ResultSet  result=stmt.executeQuery(query);
		
		if (result.next() == false) {
		String sql = "INSERT INTO  company_info(name_title,tel1,tel2,email,website,mobil1,mobil2,mobil3,mobil4,mobil5,categury,location)"
				+ " VALUES ( '"+getName_title()+"','"+getTel1()+"','"+getTel2()+"','"+getEmail()+"','"+getWebsite()+"'"
						+ ",'"+getMobil1()+"','"+getMobil2()+"','"+getMobil3()+"','"+getMobil4()+"','"+getMobil5()+"'"
								+ ",'"+getCategury()+"','"+getLocation()+"')";
	 
	 
		   stmt.executeUpdate(sql);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			   JOptionPane.showMessageDialog(null, "saved Faild", "Information ",JOptionPane.INFORMATION_MESSAGE);
			 
		}
 
	}
	
	
	
	
	
	public String getLocation() {
		return location;
	}





	public void setLocation(String location) {
		this.location = location;
	}





	public String getCategury() {
		return categury;
	}





	public void setCategury(String categury) {
		this.categury = categury;
	}





	public String getName_title() {
		return name_title;
	}

	public void setName_title(String name_title) {
		this.name_title = name_title;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getMobil1() {
		return mobil1;
	}

	public void setMobil1(String mobil1) {
		this.mobil1 = mobil1;
	}

	public String getMobil2() {
		return mobil2;
	}

	public void setMobil2(String mobil2) {
		this.mobil2 = mobil2;
	}

	public String getMobil3() {
		return mobil3;
	}

	public void setMobil3(String mobil3) {
		this.mobil3 = mobil3;
	}

	public String getMobil4() {
		return mobil4;
	}

	public void setMobil4(String mobil4) {
		this.mobil4 = mobil4;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getMobil5() {
		return mobil5;
	}

	public void setMobil5(String mobil5) {
		this.mobil5 = mobil5;
	}

	private company_info() {
	 
	}
	
public static class Builder{
		
	
	String name_title;
	String tel1;
	String email;
	String website;
	String mobil1;
	String mobil2;
	String mobil3;
	String mobil4;
	String tel2;
	String mobil5;
	String location;
	String categury;
	
	 

 



	public Builder setLocation(String location) {
		this.location = location;return this;
	}
 


	public Builder setCategury(String categury) {
		this.categury = categury;return this;
	}



	public Builder setName_title(String name_title) {
		this.name_title = name_title;return this;
	}

 

	public Builder setTel1(String tel1) {
		this.tel1 = tel1;return this;
	}


 

	public Builder setEmail(String email) {
		this.email = email;return this;
	}

 

	public Builder setWebsite(String website) {
		this.website = website;return this;
	}

 


	public Builder setMobil1(String mobil1) {
		this.mobil1 = mobil1;return this;
	}

 
	public Builder setMobil2(String mobil2) {
		this.mobil2 = mobil2;return this;
	}

 

	public Builder setMobil3(String mobil3) {
		this.mobil3 = mobil3;return this;
	}


 
	public Builder setMobil4(String mobil4) {
		this.mobil4 = mobil4;return this;
	}

 

	public Builder setTel2(String tel2) {
		this.tel2 = tel2;return this;
	}
 


	public Builder setMobil5(String mobil5) {
		this.mobil5 = mobil5;return this;
	}


		public company_info build() {
			company_info company_info=new company_info();
			company_info.setEmail(email);
			company_info.setMobil1(mobil1);
			company_info.setMobil2(mobil2);
			company_info.setMobil3(mobil3);
			company_info.setMobil4(mobil4);
			company_info.setMobil5(mobil5);
			company_info.setName_title(name_title);
			company_info.setTel1(tel1);
			company_info.setTel2(tel2);
			company_info.setWebsite(website);
			company_info.setLocation(location);
			company_info.setCategury(categury);
			
			
			return company_info;
		}
	



}

}
