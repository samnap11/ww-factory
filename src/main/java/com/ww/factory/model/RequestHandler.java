package com.ww.factory.model;

import java.sql.*;
import java.util.*;

public class RequestHandler {
    static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ws_factory";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "praktikum";

    Connection conn;

    public RequestHandler() {
        initialize();
    }

    public void initialize() {
        try {
            System.out.println("Connecting to database...");
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Request> getAllRequest() {
        ArrayList<Request> requests = new ArrayList<Request>();    
        
        try {
            String query = "SELECT * FROM requeststock WHERE status='pending'";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                Request request = new Request();
                request.setIdRequest(resultSet.getInt("idrequeststock"));
                request.setIdCoklat(resultSet.getInt("idcoklat")); 
                request.setJumlah(resultSet.getInt("jumlahrequest")); 
                request.setStatus(resultSet.getString("status"));
                requests.add(request);
            }
            System.out.println("Success!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching to database");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
            }
        }
        return requests;
    }

    public boolean addNewRequest(int idcoklat, int jumlah) {
        // SQL query
        boolean success = false;
        try {
            String query = String.format("INSERT INTO requeststock(idcoklat,jumlahrequest,status) VALUES (%d, %d, 'pending')", idcoklat, jumlah);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            success = true;
            System.out.println(String.format("Success requesting choco id: %d , amount: %d", idcoklat, jumlah));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error insert to database");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
            }
        }

        return success;
    }

    public String getRequestStatus(Request req) {
        
        String status = "";
        try {
            String query = String.format("SELECT status FROM requeststock WHERE idrequeststock=%d", req.getIdRequest());
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            System.out.println("Success!");
            while(resultSet.next()) {
                status = resultSet.getString("status");
            }
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching to database");
            return status;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
            }
        }
    }

    public boolean updateRequestStatus(Request req) {
        try {
            String query = String.format("UPDATE requeststock SET status='delivered' WHERE idrequeststock=%d", req.getIdRequest());            
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            
            String query2 = String.format("UPDATE coklat SET jumlah = jumlah - %d WHERE idcoklat=%d", req.getJumlah(), req.getIdCoklat());
            Statement stmt2 = conn.createStatement();
            stmt2.executeUpdate(query2);
            System.out.println("success!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error update to database");
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
            }
        }
    }

}