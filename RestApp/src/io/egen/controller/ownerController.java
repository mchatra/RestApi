package io.egen.controller;

import io.egen.DAO.ownerDAO;
import io.egen.exception.AppException;
import io.egen.model.Reservation;
import io.egen.model.Table;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;



@Path("/owner")
public class ownerController {

	@GET()
	@Path("/viewseating")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Table> findAll() {
		System.out.println("inside get");
		List<Table> Tables = null;
		try {
			ownerDAO dao = new ownerDAO();
			Tables = dao.findAll();
			System.out.println("After getting table objects");
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		System.out.println("Before returning");
		return Tables;
	}
	
	@GET()
	@Path("/viewreservation")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> findAllReservation() {
		System.out.println("inside get");
		List<Reservation> Tables = null;
		try {
			ownerDAO dao = new ownerDAO();
			Tables = dao.findAllReserve();
			System.out.println("After getting table objects");
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		System.out.println("Before returning");
		return Tables;
	}
	
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation create (Reservation emp) {
		
		System.out.println("inside create");
		
		try {
			ownerDAO dao = new ownerDAO();
			emp = dao.create(emp);
			System.out.println("once the function returns back");
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		System.out.println("Before returing the object");
		return emp;
	}
	
	@PUT
	@Path("edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation update (@PathParam("id") int id, Reservation emp) {
		
		try {
			ownerDAO dao = new ownerDAO();
			emp = dao.update(id, emp);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return emp;
	}
	


	
	

}
