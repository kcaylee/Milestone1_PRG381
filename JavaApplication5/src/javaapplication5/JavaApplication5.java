/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JavaApplication5 {

     private static final String CON_URL = "jdbc:postgresql://localhost:5432/LoginInfo1"; // Update with your database URL
    private static final String USERNAME = "postgres"; // Update with your database username
    private static final String PASSWORD = "admin"; // Update with your database password
    private static final String DRIVER = "org.postgresql.Driver";
    public static void main(String[] args) {
         JavaApplication5 app = new JavaApplication5();
        app.testConnection();
        app.testInsertUser("testuser", "testpass", "Test", "User", "1234567890", "test@example.com");
        app.testSelectUser("testuser");
    }
    
     public void testConnection() {
        try {
            Class.forName(DRIVER);
            try (Connection conn = DriverManager.getConnection(CON_URL, USERNAME, PASSWORD)) {
                if (conn != null) {
                    System.out.println("Connected to the database successfully!");
                } else {
                    System.out.println("Failed to connect to the database.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    // Method to test inserting a new user
    public void testInsertUser(String username, String password, String name, String surname, String phone, String email) {
        String insertSql = "INSERT INTO Members (username, password, name, surname, phone, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(CON_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(insertSql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);  // In a real application, hash the password before storing
            stmt.setString(3, name);
            stmt.setString(4, surname);
            stmt.setString(5, phone);
            stmt.setString(6, email);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User inserted successfully!");
            } else {
                System.out.println("Failed to insert user.");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    // Method to test selecting a user by username
    public void testSelectUser(String username) {
        String selectSql = "SELECT * FROM Members WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(CON_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(selectSql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("User found: " + rs.getString("username"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Surname: " + rs.getString("surname"));
                    System.out.println("Phone: " + rs.getString("phone"));
                    System.out.println("Email: " + rs.getString("email"));
                } else {
                    System.out.println("User not found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error selecting user: " + e.getMessage());
        }
    }
    
}
