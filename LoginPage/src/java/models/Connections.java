
package models;

import java.sql.*;

public class Connections {

    private static final String CON_URL = "jdbc:postgresql://localhost:5432/LoginInfo1";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";
    private static final String DRIVER = "org.postgresql.Driver";
    private Connection con;


    public Connections() {}


    public Connection getCon() throws ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(CON_URL, USERNAME, PASSWORD);
            if (this.con != null) {
                System.out.println("Connected to db");
            }
        } catch (SQLException ex) {
            System.out.println("Could not connect: " + ex.getMessage());
        }
        return con;
    }


    public boolean addNewUser(String user, String pass, String name, String surname, String phone, String email) {
        try (Connection conn = getCon()) {

            String checkSql = "SELECT * FROM Members WHERE username = ? OR email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, user);
                checkStmt.setString(2, email);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Username or Email already taken!");
                        return false;
                    } else {
                        String insertSql = "INSERT INTO Members (username, password, name, surname, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                            stmt.setString(1, user);
                            stmt.setString(2, pass);  
                            stmt.setString(3, name);
                            stmt.setString(4, surname);
                            stmt.setString(5, phone);
                            stmt.setString(6, email);

                            int rowsAffected = stmt.executeUpdate();
                            return rowsAffected > 0;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding new user: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyUser(String user, String pass) {
        try (Connection conn = getCon()) {
            String sql = "SELECT * FROM Members WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user);
                stmt.setString(2, pass);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next(); 
                }
            }
        } catch (Exception e) {
            System.out.println("Error verifying user: " + e.getMessage());
            return false;
        }
    }


    public boolean deleteUser(String user) {
        try (Connection conn = getCon()) {
            String sql = "DELETE FROM Members WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }
}