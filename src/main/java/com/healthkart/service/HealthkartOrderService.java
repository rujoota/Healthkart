/**
 * 
 */
package com.healthkart.service;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.healthkart.vo.Order;

/**
 * @author Rajan
 *
 */
@Path("/orders")
public class HealthkartOrderService {

	@Path("/createOrder")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Order createOrder( InputStream is )
	{
		return null;
	}
	
	
}
