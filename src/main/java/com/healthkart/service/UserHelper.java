/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.healthkart.service;

import static com.healthkart.service.OrderHelper.forname;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;
import java.util.Random;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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
    public static void updateUserCode(String userid,String code)
    {
        
    }
    public static String checkUserNamePwd(String userid, String pwd)
    {   
        String returnStatus = "";
        try
        {            
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            CallableStatement cs = null;
            cs = con.prepareCall("{call login(?,?,?)}");
            cs.setString(1, userid);
            cs.setString(2, pwd);            
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeQuery();
            returnStatus = cs.getString(3);            
            con.close();

        } 
        catch (Exception e)
        {
            System.out.println("exception in checkUserNamePwd :" + e.toString());
        }
        return returnStatus;
    }

    public static String verifyUser(String userid, int code)
    {   
        String returnStatus = null;
        try
        {            
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            CallableStatement cs = null;
            cs = con.prepareCall("{call verifyUser(?,?,?)}");
            cs.setString(1, userid);
            cs.setString(2, String.valueOf(code));            
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeQuery();
            returnStatus = cs.getString(3);            
            con.close();
        } 
        catch (Exception e)
        {
            System.out.println("exception in verifyUser :" + e.toString());            
        }
        return returnStatus;
    }
    
    public static String retrieveForgotPassword(String userid,String code)
    {   
        String returnStatus = null;
        try
        {            
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            CallableStatement cs = null;
            cs = con.prepareCall("{call retrieveForgotAccount(?,?,?)}");
            cs.setString(1, userid);
            cs.setString(2, code);            
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeQuery();
            returnStatus = cs.getString(3);            
            con.close();
        } 
        catch (Exception e)
        {
            System.out.println("exception in retrieveForgotPassword :" + e.toString());            
        }
        return returnStatus;
    }
    
    public static String forgotPassword(String userid,String code)
    {   
        String returnStatus = null;
        try
        {            
            Class.forName(forname);
            con = DriverManager.getConnection(dbUrl, userName, password);
            CallableStatement cs = null;
            cs = con.prepareCall("{call forgotPassword(?,?,?)}");
            cs.setString(1, userid);
            cs.setString(2, code);            
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeQuery();
            returnStatus = cs.getString(3);            
            con.close();
        } 
        catch (Exception e)
        {
            System.out.println("exception in forgotPassword :" + e.toString());            
        }
        return returnStatus;
    }
    static int generateRandom(int min,int max)
    {
        Random random = new Random();        
        int randomInt = random.nextInt((max - min) + 1) + min;
        if (randomInt > max)
        {
            randomInt -= min;
        }
        return randomInt;
    }
    static boolean addNewUser(String name, String userId, String pwd,String randomInt) throws Exception
    {
        Boolean returnStatus = false;
        Class.forName(forname);
        con = DriverManager.getConnection(dbUrl, userName, password);
        CallableStatement cs = null;
        cs = con.prepareCall("{call createNewUser(?,?,?,?,?)}");
        cs.setString(1, name);
        cs.setString(2, userId);
        cs.setString(3, pwd);        
        cs.setString(4, randomInt);
        cs.registerOutParameter(5, Types.BOOLEAN);
        cs.executeQuery();
        returnStatus = cs.getBoolean(5);
        con.close();
        
        //sendEmail(userId, subject, body);
        return returnStatus;
    }

    static void sendEmail(String to, String subject, String body)
    {

        String from = "mithumahek@gmail.com";
        String pass = "happynewyear2016";
        // Assuming you are sending email from localhost

        int port = 465;
        // Get system properties
        Properties properties = System.getProperties();

        String host = "smtp.gmail.com";
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        //properties.put("mail.debug", "true");
        properties.put("mail.smtp.EnableSSL.enable", "true");
        properties.put("mail.smtp.password", pass);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", port);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, pass);
            }
        });

        try
        {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            //Transport.send(message);
            Transport transport = session.getTransport("smtps");
            transport.connect(host, port, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Sent mail to " + to + " successfully....");
        } catch (Exception ex)
        {
            System.out.println("exception in sending mail:" + ex.toString());
        }

    }
}
