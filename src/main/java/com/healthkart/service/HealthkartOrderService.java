/**
 *
 */
package com.healthkart.service;

import com.google.gson.Gson;
import com.healthkart.Medicines;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.healthkart.vo.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;

/**
 * @author Rajan
 *
 */
@Path("/orders")
public class HealthkartOrderService
{  
    @Path("/createOrder")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(InputStream is)
    {
        //return null;
        try
        {
            Gson gson = new Gson();
            return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(Helper.getOrder())).build();
        } catch (Exception ex)
        {
            System.out.println("exception in createorder :" + ex.toString());
        }
        return null;
    }

    @Path("/searchMedicine")
    @GET
    public Response searchMedicine(@HeaderParam("searchQuery") String searchQuery)
    {
        try
        {
            Gson gson = new Gson();           
            return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(Helper.searchMedicine(searchQuery))).build();
        } catch (Exception ex)
        {
            System.out.println("exception in searchMedicine :" + ex.toString());
        }
        return null;
    }
    @Path("/getMedicineDetails")
    @GET
    public Response getMedicineDetails(@HeaderParam("medicineId") String medicineId)
    {
        try
        {
            Gson gson = new Gson();
            Medicines med1=Helper.getMedicineDetail(medicineId);
            ArrayList<Medicines> list=new ArrayList<Medicines>();
            list.add(med1);
            
            return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(Helper.getSubstitute(medicineId,list))).build();
        } catch (Exception ex)
        {
            System.out.println("exception in getMedicineDetails :" + ex.toString());
        }
        return null;
    }
    @Path("/getDataFromCart")
    @GET
    public Response getDataFromCart(@HeaderParam("userId") String userId,@HeaderParam("orderId") String orderId)
    {
        try
        {
            Gson gson = new Gson();
            return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(Helper.getItemsFromCart(userId, orderId))).build();
        } 
        catch (Exception ex)
        {
            System.out.println("exception in getMedicinesForCart :" + ex.toString());
        }
        return null;
    }

    @Path("/addInCart")
    @GET
    public Response addInCart(@HeaderParam("medicineId") String medId,@HeaderParam("selectedQty") int selectedQty,@HeaderParam("orderId") String orderId,@HeaderParam("userId") String userId)
    {
        try
        {
            Gson gson = new Gson();
            return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(Helper.addToCart(medId, selectedQty, orderId, userId))).build();
        } 
        catch (Exception ex)
        {
            System.out.println("exception in getMedicinesForCart :" + ex.toString());
        }
        return null;
    }

    @Path("/checkLocation")
    @GET
    public Response checkLocation(@HeaderParam("pincode") int pincode)
    {
        //return null;
        try
        {
            if (Helper.isValidPincode(pincode))
            {
                return Response.status(HttpServletResponse.SC_OK).build();
            } 
            else
            {
                return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
            }
        } 
        catch (Exception ex)
        {
            System.out.println("exception in createorder :" + ex.toString());
        }
        return null;
    }

}
class Demo
{

    String value;

    public Demo(String val)
    {
        value = val;
    }
}
