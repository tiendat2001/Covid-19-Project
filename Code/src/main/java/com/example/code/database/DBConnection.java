package com.example.code.database;

import java.sql.*;

public class DBConnection {
    static Connection connection = null;
    public static final String DB = "anticovid19";
    public static final String URL = "jdbc:mysql://localhost:3306/"+DB;
    public static final String USER = "root";
    public static final String PASS = "";
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USER,PASS);
//            System.out.println("Connected to database successfully");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void main(String[] args) {
        getConnection();
    }

}
