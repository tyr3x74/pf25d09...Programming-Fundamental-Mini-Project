// UserDAO.java
//
// !!! WARNING: THIS CODE STORES PASSWORDS IN PLAIN TEXT. !!!
// !!! This is EXTREMELY INSECURE and is NOT recommended for any real-world application. !!!
// !!! Use this ONLY for learning purposes where security is not a concern, and no sensitive data is involved. !!!
// !!! For any production or publicly accessible application, ALWAYS use strong password hashing (e.g., BCrypt). !!!

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserDAO {

    /**
     * Registers a new user by storing their username and plain-text password in the database.
     * @param username The username for the new account.
     * @param password The plain-text password for the new account.
     * @param email An optional email address for the user (can be null).
     * @return true if registration was successful, false otherwise.
     */
    public boolean registerUser(String username, String password, String email) {
        // Renamed 'password_hash' to 'password' in the SQL query
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)"; // No email column anymore

        try (Connection conn = Connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Storing password directly as plain text
            // No email column to set: pstmt.setString(3, email);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "JDBC Driver not found. Ensure MySQL Connector/J is in your classpath.", "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle specific errors like duplicate username (SQLState for integrity constraint violation)
            if (e.getSQLState().startsWith("23")) { // For MySQL, typically 23000 or 23000-23001
                JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different one.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed due to a database error: " + e.getMessage(), "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    /**
     * Attempts to log in a user by comparing their entered password with the plain-text password
     * stored in the database.
     * @param username The username entered by the user.
     * @param password The plain-text password entered by the user.
     * @return true if the username and password match a record in the database, false otherwise.
     */
    public boolean loginUser(String username, String password) {
        // Renamed 'password_hash' to 'password' in the SQL query
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = Connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Renamed 'password_hash' to 'password' when retrieving the column
                    String storedPassword = rs.getString("password"); // Retrieve plain-text password
                    // Direct string comparison of the entered password with the stored plain-text password
                    return password.equals(storedPassword);
                }
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "JDBC Driver not found. Ensure MySQL Connector/J is in your classpath.", "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Login error due to a database issue: " + e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
        }
        return false; // User not found or password incorrect
    }
}