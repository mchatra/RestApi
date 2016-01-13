package io.egen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import io.egen.exception.AppException;
import io.egen.model.Reservation;
import io.egen.util.util;

public class customerDAO {
public void delete(int id) throws AppException {
	
	Connection con = util.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
		
		String query = "delete from reservation where customerid = ?";
	      ps = con.prepareStatement(query);
	      ps.setInt(1, id);
	 
	      // execute the preparedstatement
	      ps.execute();
	       
	   
	}
	catch (SQLException e) {
		e.printStackTrace();
		throw new AppException(e.getMessage());
	}
	finally {
		
		try {
			if (ps != null) {
				ps.close();
			}

			if (rs != null) {
				rs.close();
			}

			if (con != null) {
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	}

public Reservation create (Reservation emp) throws AppException {
	System.out.println("Inside the create function");
	Connection con = util.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
//		insert into reservation (Date,Timestampto,size,contactinfo) 
//		values ('2015-01-12','2015-02-08 05:15:23',5,'4079232038');
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = emp.getDate();

		

			java.util.Date date = formatter.parse(dateInString);
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			System.out.println(date);
			System.out.println(formatter.format(date));
			

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			java.util.Date date1 = sdf.parse(emp.getTimeTo());
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date1.getTime());
		
		ps = con.prepareStatement("INSERT INTO reservation (Date,Timestampto,size,contactinfo) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setDate(1, sqlStartDate);
		ps.setTimestamp(2, timestamp);
		ps.setInt(3, emp.getSize());
		ps.setString(4, emp.getContactInfo());
		
		System.out.println("Before execution of insert statement");
		ps.executeUpdate();
		System.out.println("After execution of insert statement");
		rs = ps.getGeneratedKeys();
		
		
		
	} catch (SQLException | ParseException e) {
		e.printStackTrace();
		throw new AppException(e.getMessage());
	}
	finally {
		
		try {
			if (ps != null) {
				ps.close();
			}

			if (rs != null) {
				rs.close();
			}

			if (con != null) {
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return emp;
}

public Reservation update(int id, Reservation emp) throws AppException {
	Connection con = util.connect();
	PreparedStatement ps = null;
	ResultSet rs = null;
	try{
		
		String query = "UPDATE reservation SET  size= ?, contactinfo= ? WHERE customerid = ?";
	      ps = con.prepareStatement(query);
	      ps.setInt(1, emp.getSize());
	      ps.setString(2, emp.getContactInfo());
	      ps.setInt(3, id);
	 
	      // execute the preparedstatement
	      ps.execute();
	       
	   
	}
	catch (SQLException e) {
		e.printStackTrace();
		throw new AppException(e.getMessage());
	}
	finally {
		
		try {
			if (ps != null) {
				ps.close();
			}

			if (rs != null) {
				rs.close();
			}

			if (con != null) {
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return emp;
}



}
