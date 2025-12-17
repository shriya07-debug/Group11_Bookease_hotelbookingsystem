
package dao;

import database.DBConnection;
import java.sql.*;

public class UserDAO {

    // Method to check if email exists
    public boolean isEmailExist(String email) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM users WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        }
        return false;
    }

    // Method to save OTP to database
    public void saveOtp(String email, String otp) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO password_resets (email, otp) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, otp);
                stmt.executeUpdate();
            }
        }
    }

    // Method to verify OTP
    public boolean verifyOtp(String email, String otp) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM password_resets WHERE email = ? AND otp = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                stmt.setString(2, otp);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            }
        }
        return false;
    }

    // Method to update the user's password
    public void updatePassword(String email, String newPassword) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE users SET password = ? WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newPassword);
                stmt.setString(2, email);
                stmt.executeUpdate();
            }
        }
    }

    public boolean checkEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void saveOTP(String email, String otp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean verifyOTP(String email, String otp) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean emailExists(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}