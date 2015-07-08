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
    
    @Path("/forgotPassword")	
    @GET
    public Response forgotPassword( @HeaderParam("userId") String userId,@HeaderParam("code") String code )
    {
        Gson gson = new Gson();
        return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(UserHelper.forgotPassword(userId,code))).build();
    }
    @Path("/checkForgotCode")	
    @GET
    public Response checkForgotCode( @HeaderParam("userId") String userId,@HeaderParam("code") String code )
    {
        Gson gson = new Gson();
        return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(UserHelper.retrieveForgotPassword(userId,code))).build();
    }
    @Path("/userLogin")	
    @GET
    public Response userLogin( @HeaderParam("userId") String userId,@HeaderParam("pwd") String pwd )
    {
        Gson gson = new Gson();
        return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(UserHelper.checkUserNamePwd(userId, pwd))).build();
    }
    @Path("/verifyUser")	
    @GET
    public Response verifyUser( @HeaderParam("userId") String userId,@HeaderParam("code") int code )
    {
        Gson gson = new Gson();
        return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(UserHelper.verifyUser(userId, code))).build();
    }
    @Path("/registerUser")	
    @GET
    public Response registerUser( @HeaderParam("name") String name,@HeaderParam("userId") String userId,@HeaderParam("pwd") String pwd,@HeaderParam("code") String code  )
    {
        try
        {            
            if(UserHelper.addNewUser(name,userId, pwd,code))
            {                
                return Response.status(HttpServletResponse.SC_OK).build();                
            }
            else
            {
                return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception in registerUser:"+ex.getMessage());
            return null;
        }
    }
}
