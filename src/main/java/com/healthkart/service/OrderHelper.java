/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthkart.service;

import com.google.gson.Gson;
import com.healthkart.Medicines;
import com.healthkart.MedicinesInCart;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RQV035
 */
public class OrderHelper
{
    static String dbUrl = "jdbc:mysql://localhost/inviks";
    static String userName = "root", password = "password";
    static String forname = "com.mysql.jdbc.Driver";
    static Connection con;
    static Boolean isValidPincode(int pincode)
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
    static String addToCart(String medicineId,int qty,String orderId,String userId)
    {
        String returnStatus;
        
        try
        {
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            CallableStatement cs = null;            
            cs = con.prepareCall("{call createCartEntry(?,?,?,?,?)}");
            cs.setString(1, medicineId);
            cs.setInt(2, qty);
            cs.setString(3, orderId);
            cs.setString(4, userId);
            cs.registerOutParameter(5, Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            returnStatus=cs.getString(5);
            con.close();
        } 
        catch (Exception e)
        {
            System.out.println("exception in addToCart :" + e.toString());
            returnStatus="exception : "+e.getMessage();
        }
        return returnStatus;
    }
    static ArrayList<Medicines> getMedicineDataForCart(String[] medicineIds)
    {
        ArrayList<Medicines> arr = new ArrayList<Medicines>();
        String query="select medicineid,price,medicine_name,quantity_available FROM medicines where medicineid in('";
        for(int i=0;i<medicineIds.length;i++)
        {
            if(i==medicineIds.length-1)
                query += medicineIds[i]+"')";
            else
                query += medicineIds[i]+"','";   
        }
        
        try
        {
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next())
            {
                Medicines temp=new Medicines();
                temp.setMedicineId(rs.getString("medicineid"));
                temp.setPrice(rs.getDouble("price"));
                temp.setMedicineName(rs.getString("medicine_name"));
                temp.setQuantity(rs.getInt("quantity_available"));
                arr.add(temp);
            }
        } 
        catch (Exception e)
        {
            System.out.println("exception in getMedicineDataForCart :" + e.toString());
        }
        finally
        {
            try
            {
                con.close();
            } 
            catch (SQLException ex)
            {
                System.out.println("exception in getMedicineDataForCart :" + ex.toString());
            }
        }
        return arr;
    }
    static ArrayList<Demo> getOrder()
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
    static ArrayList<Medicines> searchMedicine(String searchString)
    {
        //connection.prepareStatement("SELECT * FROM table1 LIMIT ?,?");
        
        String query = "Select medicineid,medicine_name,company_name,price,discount,short_description,medicine_form,composition FROM medicines where expiry_date>'"+LocalDateTime.now()+"' and (medicine_name like '"+searchString+"%'"+" or salt_name like '%"+searchString+"%' or company_name like '"+searchString+"%')";
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
                double discount=rs.getDouble("discount");
                if(discount==0)
                    med.setPrice(rs.getDouble("price"));
                else
                {
                    double price=rs.getDouble("price");
                    if((price-price*discount/100)>0)
                        med.setPrice(price-price*discount/100);
                    else
                        med.setPrice(rs.getDouble("price"));
                }
                med.setComposition(rs.getDouble("composition"));
                med.setCompanyName(rs.getString("company_name"));
                med.setForm(rs.getString("medicine_form"));
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
    static ArrayList<Medicines> getSubstitute(String medicineId, ArrayList<Medicines> list)
    {
        try
        {
            String query = "Select M2.medicineid,M2.medicine_name,M2.company_name,M2.price,M2.discount,M2.short_description,M2.medicine_form,M2.composition FROM medicines M1,medicines M2 where M1.expiry_date>'"+LocalDateTime.now()+"' and M2.expiry_date>'"+LocalDateTime.now()+"' and M1.medicineid!=M2.medicineid and M1.medicineid='"+medicineId+"' and M1.salt_name=M2.salt_name and M1.composition=M2.composition";
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
                double discount=rs.getDouble("discount");
                if(discount==0)
                    med.setPrice(rs.getDouble("price"));
                else
                {
                    double price=rs.getDouble("price");
                    if((price-price*discount/100)>0)
                        med.setPrice(price-price*discount/100);
                    else
                        med.setPrice(rs.getDouble("price"));
                }
                med.setComposition(rs.getDouble("composition"));
                med.setCompanyName(rs.getString("company_name"));
                med.setForm(rs.getString("medicine_form"));
                list.add(med);
            }
        }
        catch(Exception e)
        {
            System.out.println("exception in getSubstitute :" + e.toString());
        }
        return list;
    }
    static Medicines getMedicineDetail(String medicineId)
    {
        String query = "select * FROM medicines where medicineid='"+medicineId+"'";       
        Medicines med=new Medicines();
        try
        {
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next())
            {                
                med.setMedicineId(rs.getString("medicineid"));
                med.setMedicineName(rs.getString("medicine_name"));
                med.setShortDescription(rs.getString("short_description"));
                med.setPrice(rs.getDouble("price"));                
                med.setComposition(rs.getDouble("composition"));
                med.setCompanyName(rs.getString("company_name"));
                med.setForm(rs.getString("medicine_form"));     
                med.setAdditionalInfo(rs.getString("additional_info"));
                med.setDiscount(rs.getDouble("discount"));
                med.setLongDescription(rs.getString("long_description"));
                med.setPackSize(rs.getInt("pack_size"));
                med.setPackUnit(rs.getString("pack_unit"));
                med.setQuantity(rs.getInt("quantity_available"));
                med.setSalt(rs.getString("salt_name"));
                med.setSaltInfo(rs.getString("salt_info"));
                med.setFormInfo(rs.getString("form_display"));
            }            
            con.close();
        } catch (Exception e)
        {
            System.out.println("exception in getMedicineDetails :" + e.toString());
        }
        return med;
    }
    static ArrayList<MedicinesInCart> getItemsFromCart(String userId,String orderId)
    {
        ArrayList<MedicinesInCart> arrayList=new ArrayList<MedicinesInCart>();        
        try
        {
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            CallableStatement cs = null;            
            cs = con.prepareCall("{call getCartEntries(?,?)}");
            cs.setString(1, userId);
            cs.setString(2, orderId);
            ResultSet rs = cs.executeQuery();
            while (rs.next())
            {
                MedicinesInCart medicinesInCart=new MedicinesInCart();
                medicinesInCart.setMedicineId(rs.getString("medicineid"));
                medicinesInCart.setMedicineName(rs.getString("medicine_name"));
                medicinesInCart.setQtyAvailable(rs.getInt("qty_available"));
                medicinesInCart.setPrice(rs.getDouble("price"));
                medicinesInCart.setQtyOrdered(rs.getInt("qty_ordered"));
                medicinesInCart.setTotal(rs.getInt("total"));
                medicinesInCart.setOrderId(rs.getString("orderid"));
                arrayList.add(medicinesInCart);
            }
            con.close();
        } 
        catch (Exception e)
        {
            System.out.println("exception in addToCart :" + e.toString());            
        }
        return arrayList;
    }
    
}
