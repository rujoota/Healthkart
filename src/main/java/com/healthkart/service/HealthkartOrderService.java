/**
 *
 */
package com.healthkart.service;

import com.google.gson.Gson;
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
import javax.servlet.http.HttpServletResponse;

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
    public Response createOrder(InputStream is) {
        //return null;
        try
        {
        Gson gson = new Gson();        
        return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(getOrder())).build();
        }
        catch(Exception ex)
        {
            System.out.println("exception in createorder :"+ex.toString());
        }
        return null;
    }

    ArrayList<Demo> getOrder() {
        String dbUrl = "jdbc:mysql://localhost/inviks";
        
        String query = "Select * FROM demo";
        String userName = "root", password = "password";
        ArrayList<Demo> arr=new ArrayList<Demo>();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                arr.add(new Demo(rs.getString(1)));
            } 
            System.out.println(arr);
            con.close();
        } 
        catch (Exception e) {
            System.out.println("exception in getorder :"+e.toString());
        } 
        return arr;
    }
}
class Demo
{
    String value;
    public Demo(String val)
    {
        value=val;
    }
}