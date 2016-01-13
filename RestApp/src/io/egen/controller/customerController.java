package io.egen.controller;

import io.egen.DAO.customerDAO;
import io.egen.DAO.ownerDAO;
import io.egen.exception.AppException;
import io.egen.model.Reservation;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/customer")

public class customerController {
	
		@DELETE
		@Path("/{id}")
		public Response delete (@PathParam("id") int id) {

			try {
				customerDAO  dao = new customerDAO ();
				dao.delete(id);
			} catch (AppException e) {
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			
			return Response.ok().build();
		}

		@POST
		@Path("/create")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Reservation create (Reservation emp) {
			
			System.out.println("inside create");
			
			try {
				customerDAO dao = new customerDAO();
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
				customerDAO dao = new customerDAO();
				emp = dao.update(id, emp);
			} catch (AppException e) {
				e.printStackTrace();
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			return emp;
		}
		


}
