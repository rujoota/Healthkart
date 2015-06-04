/**
 * 
 */
package com.healthkart.service;

import java.io.InputStream;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.healthkart.vo.User;
import com.sun.org.apache.xerces.internal.util.Status;

/**
 * @author Rajan
 *
 */
@Path("/users")
public class HealthkartUserService {

	@Path("/createUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser( User user )
	{
		System.out.println("Inside createUser");
		return null;
	}
	
	@Path("/retrieveUser")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveUser( @HeaderParam("userId") String userId )
	{
		User user = new User();
		user.setUserID("1");
		user.setUserName("rajan.jethva");
		user.setPassword("password");
		user.setRole("admin");
		user.setCreatedDate(LocalDateTime.now());
		user.setCreatedDate(LocalDateTime.now().minusHours((long) 24.00));
		Gson gson = new Gson();
		String jsonResponse = gson.toJson( user );
		return Response.status(HttpServletResponse.SC_OK).entity(jsonResponse).build();
	}
	
	@Path("/updateUser")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser( @PathParam("userId") String userId, InputStream is )
	{
		System.out.println("Inside updateUser");
		return null;
	}
	
	@Path("/deleteUser")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser( @PathParam("userId") String userId )
	{
		System.out.println("Inside deleteUser");
		return null;
	}
	
}
