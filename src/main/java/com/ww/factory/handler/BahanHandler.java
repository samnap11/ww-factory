package com.ww.factory.handler;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.ww.factory.DBConfig.*;
import com.ww.factory.model.Bahan;

public class BahanHandler {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = URL;
    static final String DB_USERNAME = USERNAME;
    static final String DB_PASSWORD = PASSWORD;

    Connection conn;

    public BahanHandler() {
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

    public ArrayList<Bahan> getAllBahanInFactory() {

        ArrayList<Bahan> bahans = new ArrayList<Bahan>();
        
        try {
            String query = "SELECT * FROM bahan";
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                Bahan bahan = new Bahan();
                bahan.setNama(resultSet.getString("namabahan"));
                bahan.setJumlah(resultSet.getInt("jumlah"));
                bahan.setTanggalKadaluarsa(resultSet.getDate("tanggalkadaluarsa"));
                bahans.add(bahan);
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

        return bahans;
    }

    public boolean addBahan(String nama, int jumlah, String tanggalKadaluarsa) {
        boolean inserted = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date kadaluarsaDate = null;
        try {
            kadaluarsaDate = format.parse(tanggalKadaluarsa);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Bahan bahan = new Bahan(nama, jumlah, kadaluarsaDate);
        PreparedStatement stmt = null;
        String query = "INSERT INTO bahan VALUES (?, ?, ?, ?)";

        try {
            stmt = conn.prepareStatement(query);
            stmt.setNull(1, Types.NULL);
            stmt.setString(2, nama);
            stmt.setInt(3, jumlah);

            String kadaluarsaDateTime = format.format(kadaluarsaDate);

            stmt.setString(4, kadaluarsaDateTime);

            int insertedInt = stmt.executeUpdate();

            if (insertedInt > 0) {
                inserted = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try{
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return inserted;
        }
    }

    public boolean addStockBahan(String nama, int jumlahTambahan) {
        boolean updated = false;
        PreparedStatement stmt = null;
        String query = "UPDATE bahan SET jumlah = jumlah + ? WHERE idbahan = (SELECT idbahan FROM (SELECT * FROM bahan) AS b WHERE b.namabahan = ?)";

        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, jumlahTambahan);
            stmt.setString(2, nama);

            int updatedInt = stmt.executeUpdate();

            if (updatedInt == 1) {
                updated = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return updated;
        }
    }
}