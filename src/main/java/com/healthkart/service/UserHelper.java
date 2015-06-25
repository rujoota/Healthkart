/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthkart.service;

import static com.healthkart.service.OrderHelper.forname;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author RQV035
 */
public class UserHelper
{
    static String dbUrl = "jdbc:mysql://localhost/inviks";
    static String userName = "root", password = "password";
    static String forname = "com.mysql.jdbc.Driver";
    static Connection con;
    public static boolean checkUserNamePwd(String userid,String pwd)
    {
        String query = "Select * FROM users where userid='" + userid+"' and password='"+pwd+"'";
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
            } 
            else
            {
                return true;
            }

        } 
        catch (Exception e)
        {
            System.out.println("exception in checkUserNamePwd :" + e.toString());
        }
        return false;
    }
}
