package com.ww.factory.model;

import java.sql.*;
import java.util.*;

public class CoklatHandler {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/ws_factory?serverTimezone=Asia/Jakarta";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "fullbuster11";

    Connection conn;

    public CoklatHandler() {
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

    public ArrayList<Coklat> getAllCoklatInFactory() {

        ArrayList<Coklat> coklats = new ArrayList<Coklat>();
        
        try {
            String query = "SELECT * FROM coklat";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                Coklat coklat = new Coklat();
                coklat.setID(resultSet.getInt("idcoklat"));
                coklat.setNama(resultSet.getString("namacoklat"));
                coklat.setJumlah(resultSet.getInt("jumlah"));
                coklats.add(coklat);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fetching from database");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
            }
        }

        return coklats;
    } 

    public void debug(Coklat coklat) {
        System.out.println(coklat.getNama());
        System.out.println(coklat.getJumlah());
        System.out.println(coklat.getID());
    }

    public void debug(ArrayList<ResepBahan> resepBahan) {
        for (int i = 0; i < resepBahan.size(); i++) {
            System.out.println(i);
            System.out.println(resepBahan.get(i).getNama());
            System.out.println(resepBahan.get(i).getJumlah());
        }
    }

    public boolean addNewCoklat(Coklat coklat, ArrayList<ResepBahan> resepBahan) {

        int countCoklatAdded = 0;
        int countResepAdded = 0;

        try {
            Statement stmt1 = conn.createStatement();
            String query1 = String.format("INSERT INTO coklat (namacoklat, jumlah) VALUES ('%s', %d)", coklat.getNama(), coklat.getJumlah());
            countCoklatAdded = stmt1.executeUpdate(query1);

            //getIDCoklat for resep query
            Statement stmt4 = conn.createStatement();
            String query4 = String.format("SELECT idcoklat FROM coklat WHERE namacoklat = '%s'", coklat.getNama());
            ResultSet resultSet1 = stmt4.executeQuery(query4);
            int idCoklat = -1;
            if (resultSet1.next()) {
                idCoklat = resultSet1.getInt("idcoklat");
            }
            if (idCoklat == -1) {
                return false;
            }

            try {
                for (int i = 0; i < resepBahan.size(); i++) {
                    String namaBahan = resepBahan.get(i).getNama();
                    int jumlahBahan = resepBahan.get(i).getJumlah();
    
                    System.out.println("bahan: " + namaBahan);
                    System.out.println(jumlahBahan);
    
                    try {
                        Statement stmt2 = conn.createStatement();
                        String queryGetBahanID = String.format("SELECT * FROM bahan WHERE namabahan = '%s' AND tanggalkadaluarsa > NOW() ORDER BY tanggalkadaluarsa LIMIT 1", namaBahan);
                        ResultSet resultSet = stmt2.executeQuery(queryGetBahanID);
                        while (resultSet.next()) {
                            int idBahan = resultSet.getInt("idbahan");
                            String queryInsertResep = String.format("INSERT INTO resep VALUES (%d, %d, %d)", idCoklat, idBahan, jumlahBahan);
                            Statement stmt3 = conn.createStatement();
                            countResepAdded += stmt3.executeUpdate(queryInsertResep);
                            stmt3.close();
                        }
                        stmt2.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error in database!");
                        return false;
                    }
                }
    
                if (countCoklatAdded == 1 && resepBahan.size() == countResepAdded) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error in database!");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in database!");
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