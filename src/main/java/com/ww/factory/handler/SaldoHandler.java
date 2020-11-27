package com.ww.factory.handler;

import java.sql.*;
import java.util.ArrayList;

import static com.ww.factory.DBConfig.*;
import com.ww.factory.model.*;

public class SaldoHandler {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = URL;
    static final String DB_USERNAME = USERNAME;
    static final String DB_PASSWORD = PASSWORD;
    private Connection conn = null;

    public SaldoHandler() {
        try {
            Class.forName(DB_DRIVER);
            this.conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Saldo> getListOfSaldo(int n) {
        ArrayList<Saldo> saldoArr = new ArrayList<Saldo>();
        PreparedStatement stmt = null;
        String query = "SELECT * FROM saldo ORDER BY saldo_timestamp DESC LIMIT ?";
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, n);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Saldo saldo = new Saldo(rs.getInt("saldo"));
                saldo.setTimestamp(rs.getTimestamp("saldo_timestamp"));
                saldoArr.add(saldo);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return saldoArr;
        }
    }

    public int getCurrentSaldo() {
        Saldo saldo = new Saldo();
        Statement stmt = null;
        String query = "SELECT * FROM saldo ORDER BY saldo_timestamp DESC LIMIT 1";
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                saldo.setSaldo(rs.getInt("saldo"));
                saldo.setTimestamp(rs.getTimestamp("saldo_timestamp"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return saldo.getSaldo();
        }
    }

    public boolean addSaldo(int newSaldoInt) {
        Saldo newSaldo = new Saldo(newSaldoInt);
        PreparedStatement stmt = null;
        String query = "INSERT INTO saldo VALUES (?, ?, ?)";
        boolean inserted = false;

        try {
            stmt = conn.prepareStatement(query);
            stmt.setNull(1, Types.NULL);
            stmt.setInt(2, newSaldo.getSaldo());
            stmt.setTimestamp(3, newSaldo.getTimestamp());

            int insertedInt = stmt.executeUpdate();

            if (insertedInt > 0) {
                inserted = true;
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

            return inserted;
        }
    }
}
