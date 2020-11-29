package com.ww.factory.handler;

import java.sql.*;
import java.util.*;

import static com.ww.factory.DBConfig.*;
import com.ww.factory.model.Coklat;
import com.ww.factory.model.ResepBahan;
import com.ww.factory.model.Request;

public class CoklatHandler {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = URL;
    static final String DB_USERNAME = USERNAME;
    static final String DB_PASSWORD = PASSWORD;

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

    public boolean addNewCoklat(Coklat coklat, ArrayList<ResepBahan> resepBahan) {

        boolean inserted = false;
        int countCoklatAdded = 0;
        int countResepAdded = 0;

        try {
            Statement stmt1 = conn.createStatement();
            String query1 = String.format("INSERT INTO coklat (namacoklat, jumlah) VALUES ('%s', %d)", coklat.getNama(), coklat.getJumlah());
            countCoklatAdded = stmt1.executeUpdate(query1);

            //getIDCoklat for resep query
            Statement stmt2 = conn.createStatement();
            String query2 = String.format("SELECT idcoklat FROM coklat WHERE namacoklat = '%s'", coklat.getNama());
            ResultSet resultSet2 = stmt2.executeQuery(query2);
            int idCoklat = -1;
            if (resultSet2.next()) {
                idCoklat = resultSet2.getInt("idcoklat");
            }
            if (idCoklat == -1) {
                inserted = false;
            }

            try {
                for (int i = 0; i < resepBahan.size(); i++) {
                    String namaBahan = resepBahan.get(i).getNamaBahan();
                    int jumlahBahan = resepBahan.get(i).getJumlahBahan();
    
                    System.out.println("bahan: " + namaBahan);
                    System.out.println(jumlahBahan);
    
                    try {
                        Statement stmt3 = conn.createStatement();
                        String queryGetBahanID = String.format("SELECT * FROM bahan WHERE namabahan = '%s' AND tanggalkadaluarsa > NOW() ORDER BY tanggalkadaluarsa LIMIT 1", namaBahan);
                        ResultSet resultSet3 = stmt3.executeQuery(queryGetBahanID);
                        while (resultSet3.next()) {
                            int idBahan = resultSet3.getInt("idbahan");
                            String queryInsertResep = String.format("INSERT INTO resep VALUES (%d, %d, %d)", idCoklat, idBahan, jumlahBahan);
                            Statement stmt4 = conn.createStatement();
                            countResepAdded += stmt4.executeUpdate(queryInsertResep);
                            stmt4.close();
                        }
                        stmt3.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error in database!");
                        return false;
                    }
                }
    
                if (countCoklatAdded == 1 && resepBahan.size() == countResepAdded) {
                    inserted = true;
                } else {
                    inserted = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error in database!");
                inserted = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            inserted = false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                     e.printStackTrace();
                }
            }
            return inserted;
        }
    }

    public void createCoklat(int idCoklat, ArrayList<ResepBahan> resepBahan, int jumlahPenambahan) {

        try {
            for (int i = 0; i < resepBahan.size(); i++) {
                int need = resepBahan.get(i).getJumlahBahan() * jumlahPenambahan;
                while (need > 0) {
                    String query = String.format("SELECT * FROM bahan WHERE namabahan = '%s' AND jumlah > 0 AND tanggalkadaluarsa >= NOW() LIMIT 1", resepBahan.get(i).getNamaBahan());
                    Statement stmt = conn.createStatement();
                    ResultSet resultSet = stmt.executeQuery(query);
                    int idBahan = -1;
                    int banyakBahanDiPabrik = -1;
                    if (resultSet.next()) {
                        idBahan = resultSet.getInt("idbahan");
                        banyakBahanDiPabrik = resultSet.getInt("jumlah");
                    }
                    resultSet.close();
                    assert(idBahan >= 0 && banyakBahanDiPabrik >= 0);
                    int sisaBahanDiPabrik = 0;
                    if (banyakBahanDiPabrik >= need) {
                        sisaBahanDiPabrik = banyakBahanDiPabrik - need;
                        need = 0;
                    } else {
                        sisaBahanDiPabrik = 0;
                        need -= banyakBahanDiPabrik;
                    }
                    String query2 = String.format("UPDATE bahan SET jumlah = %d WHERE idbahan = %d", sisaBahanDiPabrik, idBahan);
                    Statement stmt2 = conn.createStatement();
                    int status = stmt2.executeUpdate(query2);
                }
            }
    
            String query3 = String.format("SELECT * FROM coklat WHERE idcoklat = %d", idCoklat);
            Statement stmt3 = conn.createStatement();
            ResultSet resultSet3 = stmt3.executeQuery(query3);
            int jumlahCoklat = 0;
            while (resultSet3.next()) {
                jumlahCoklat = resultSet3.getInt("jumlah");
                break;
            }
            resultSet3.close();
            int jumlahCoklatAfterPembuatan = jumlahCoklat + jumlahPenambahan;
            String query4 = String.format("UPDATE coklat SET jumlah = %d WHERE idcoklat = %d", jumlahCoklatAfterPembuatan, idCoklat);
            Statement stmt4 = conn.createStatement();
            int statusPenambahan = stmt4.executeUpdate(query4);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in database");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
            }
        }
    }

    public boolean addStockCoklat(Request req) {

        int idCoklat = req.getIdCoklat();
        ArrayList<ResepBahan> bahans = new ArrayList<ResepBahan>();
        boolean bahanCukup = true;

        try {
            String query1 = String.format("SELECT * FROM resep WHERE idcoklat = %d", idCoklat);
            Statement stmt1 = conn.createStatement();
            ResultSet resultSet1 = stmt1.executeQuery(query1);
            while (resultSet1.next()) {
                int idBahan = resultSet1.getInt("idbahan");
                int jumlahBahan = resultSet1.getInt("jumlahbahan");
                String query2 = String.format("SELECT * FROM bahan WHERE idbahan = %d", idBahan);
                Statement stmt2 = conn.createStatement();
                ResultSet resultSet2 = stmt2.executeQuery(query2);
                while (resultSet2.next()) {
                    String namaBahan = resultSet2.getString("namabahan");
                    bahans.add(new ResepBahan(namaBahan, jumlahBahan));
                    break;
                }
                resultSet2.close();
            }
            resultSet1.close();

            for (int i = 0; i < bahans.size(); i++) {
                String query3 = String.format("SELECT SUM(jumlah) FROM bahan WHERE namabahan = '%s' AND tanggalkadaluarsa >= NOW()", bahans.get(i).getNamaBahan());
                Statement stmt3 = conn.createStatement();
                ResultSet resultSet3 = stmt3.executeQuery(query3);
                while (resultSet3.next()) {
                    int jumlahBahanDiPabrik = resultSet3.getInt("SUM(jumlah)");
                    if (jumlahBahanDiPabrik < bahans.get(i).getJumlahBahan() * req.getJumlah()) {
                        bahanCukup = false;
                    }
                }
                resultSet3.close();
            }

            if (bahanCukup) {
                createCoklat(idCoklat, bahans, req.getJumlah());
            }

        } catch (Exception e) {
            bahanCukup = false;
            e.printStackTrace();
            System.out.println("Error fetching from database");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {

                }
            }
            return bahanCukup;
        }
    }
}