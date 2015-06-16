/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthkart.service;

import com.healthkart.Medicines;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RQV035
 */
public class Helper
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
    
}
