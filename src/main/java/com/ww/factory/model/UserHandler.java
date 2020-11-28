package com.ww.factory.model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

import static com.ww.factory.DBConfig.*;

public class UserHandler {
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = URL;
    static final String DB_USERNAME = USERNAME;
    static final String DB_PASSWORD = PASSWORD;
    Connection conn;

    public UserHandler() {
        try {
            Class.forName(DB_DRIVER);
            this.conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean Register(String username, String email, String unhashed_password) {
        String hashed_password = BCrypt.hashpw(unhashed_password, BCrypt.gensalt(12));
        PreparedStatement stmt = null;
        boolean registered = false;
        String query = "INSERT INTO users VALUES (?, ?, ?)";

        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, hashed_password);

            int registeredInt = stmt.executeUpdate();

            if (registeredInt == 1) {
                registered = true;
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

            return registered;
        }

    }

    public boolean Login(String username, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT password FROM users WHERE username = ?";
        boolean match = false;

        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {
                if (BCrypt.checkpw(password, rs.getString("password"))) {
                    match = true;
                }
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
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return match;
        }
    }
}
