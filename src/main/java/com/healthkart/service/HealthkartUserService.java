/**
 * 
 */
package com.healthkart.service;

import java.io.InputStream;

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

import com.healthkart.vo.User;

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
	public User retrieveUser( @HeaderParam("userId") String userId )
	{
		System.out.println("Inside retrieveUser");
		return null;
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
