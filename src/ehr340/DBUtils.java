package ehr340;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public static Connection MakeConnection()
   {   
       Connection con;
       String dbURL = "jdbc:mysql://localhost:3306/ehr340?autoReconnect=true&useSSL=false";
       try{Class.forName("com.mysql.cj.jdbc.Driver");
       con = DriverManager.getConnection(dbURL, "root", "MySQL2023!");
       }
       catch(Exception ex){con = null;}
       return con;
   } 
}


