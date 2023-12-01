package com.cityapi.cityapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection {
    private static Connection con = null;

    private final static String url = "jdbc:postgresql://localhost:5432/cities_database";
    private final static String user = "postgres";
    private final static String password = "brzina1";

    public static Connection getConnection(){
        if(con != null)
            return con;

        try{
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected.");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}
