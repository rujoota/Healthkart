/**
 * 
 */
package com.healthkart.service;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Rajan
 *
 */
@Path("/healthkart/users")
public class HealthkartUserService {

	@Path("/createUser")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser( InputStream is )
	{
		return null;
	}
	
	@Path("/retrieveUser")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String retrieveUser( @HeaderParam("userId") String userId )
	{
		
		return "user";
	}
	
	@Path("/updateUser")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUser( @PathParam("userId") String userId, InputStream is )
	{
		return "user";
	}
	
	@Path("/deleteUser")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUser( @PathParam("userId") String userId )
	{
		return "user";
	}
	
}
