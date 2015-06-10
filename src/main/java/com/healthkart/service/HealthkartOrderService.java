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

    String dbUrl = "jdbc:mysql://localhost/inviks";
    String userName = "root", password = "password";
    String forname = "com.mysql.jdbc.Driver";
    Connection con;

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
            return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(getOrder())).build();
        } catch (Exception ex)
        {
            System.out.println("exception in createorder :" + ex.toString());
        }
        return null;
    }

    @Path("/getMedicine")
    @GET
    public Response getMedicine(@HeaderParam("searchQuery") String searchQuery)
    {
        try
        {
            Gson gson = new Gson();
            return Response.status(HttpServletResponse.SC_OK).entity(gson.toJson(searchMedicine(searchQuery))).build();
        } catch (Exception ex)
        {
            System.out.println("exception in getMedicine :" + ex.toString());
        }
        return null;
    }

    ArrayList<Medicines> searchMedicine(String searchString)
    {
        //connection.prepareStatement("SELECT * FROM table1 LIMIT ?,?");
        String query = "Select * FROM medicines where medicine_name like '"+searchString+"%'";
        ArrayList<Medicines> arr = new ArrayList<Medicines>();
        try
        {
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                Medicines med=new Medicines();
                med.setMedicineId(rs.getString("medicineid"));
                med.setMedicineName(rs.getString("medicine_name"));
                med.setShortDescription(rs.getString("short_description"));
                med.setPrice(rs.getInt("price"));
                arr.add(med);
            }
            System.out.println(arr);
            con.close();
        } catch (Exception e)
        {
            System.out.println("exception in searchMedicine :" + e.toString());
        }
        return arr;
    }

    @Path("/checkLocation")
    @GET
    public Response checkLocation(@HeaderParam("pincode") int pincode)
    {
        //return null;
        try
        {
            if (isValidPincode(pincode))
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

    Boolean isValidPincode(int pincode)
    {
        String query = "Select * FROM pincode_available where pincode=" + pincode;
        try
        {
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int count = 0;
            while (rs.next())
            {
                count++;
            }
            con.close();
            if (count == 0)
            {
                return false;
            } else
            {
                return true;
            }

        } catch (Exception e)
        {
            System.out.println("exception in getorder :" + e.toString());
        }
        return false;
    }

    ArrayList<Demo> getOrder()
    {

        String query = "Select * FROM demo";

        ArrayList<Demo> arr = new ArrayList<Demo>();
        try
        {

            Class.forName(forname);
            Connection con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next())
            {
                arr.add(new Demo(rs.getString(1)));
            }
            System.out.println(arr);
            con.close();
        } catch (Exception e)
        {
            System.out.println("exception in getorder :" + e.toString());
        }
        return arr;
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
