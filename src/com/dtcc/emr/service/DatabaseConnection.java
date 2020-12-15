package com.dtcc.emr.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/medicalemr?useSSL=false";
    static final String USER = "root";
    //static final String PASS = "Root2020";
    static final String PASS = "LearnHiber12@!";
    static Connection connection;
    public static Connection getConnection(){
        try {
            Class.forName(JDBC_DRIVER);
            connection= DriverManager.getConnection(DB_URL,USER,PASS);
            //System.out.println("Created DB Connection....");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
}
