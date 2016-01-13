package io.egen.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;











import io.egen.exception.AppException;
import io.egen.model.Reservation;
import io.egen.model.Table;
import io.egen.util.util;

public class ownerDAO {
	
	public List<Table> findAll() throws AppException {
		List<Table> tables = new ArrayList<Table>();
		Connection con = util.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM seating");
			rs = ps.executeQuery();
			System.out.println("After Execution of query");
			
			while(rs.next()) {
				Table t = new Table();
				t.setTableNo(rs.getInt("tableno"));
				t.setSize(rs.getInt("size"));
				t.setStatus(rs.getString("status"));
				
				
				tables.add(t);
			}
		} catch (SQLException e) {
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
		System.out.println("Before returning the table object");
		return tables;
	}
	
	public List<Reservation> findAllReserve() throws AppException {
		List<Reservation> tables = new ArrayList<Reservation>();
		Connection con = util.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM reservation");
			rs = ps.executeQuery();
			System.out.println("After Execution of query");
			
			while(rs.next()) {
				Reservation t = new Reservation();
				t.setCustomerId(rs.getInt("customerid"));
				t.setDate(rs.getDate("Date").toString());
				t.setTimeFrom(rs.getTimestamp("Timestampfrom").toString());
				t.setTimeTo(rs.getTimestamp("Timestampto").toString());
				t.setSize(rs.getInt("size"));
				t.setContactInfo(rs.getString("contactinfo"));
				t.setStatus(rs.getString("Status"));
				
				
				tables.add(t);
			}
		} catch (SQLException e) {
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
		System.out.println("Before returning the table object");
		return tables;
	}
	
	public Reservation create (Reservation emp) throws AppException {
		System.out.println("Inside the create function");
		Connection con = util.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
//			insert into reservation (Date,Timestampto,size,contactinfo) 
//			values ('2015-01-12','2015-02-08 05:15:23',5,'4079232038');
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
