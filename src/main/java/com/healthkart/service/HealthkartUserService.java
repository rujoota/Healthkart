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

	@Path("/userLogin")	
        @GET
	public Response userLogin( @HeaderParam("userId") String userId,@HeaderParam("pwd") String pwd )
	{
            if(UserHelper.checkUserNamePwd(userId, pwd))
            {
                return Response.status(HttpServletResponse.SC_OK).build();
            }
            else
            {
                return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
            }
	}
}
