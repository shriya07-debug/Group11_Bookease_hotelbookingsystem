package dao;

import java.sql.*;
import model.Admin;
import database.MySqlConnection;

public class AdminDAO {
    private final MySqlConnection dbConnection = new MySqlConnection();

    public Admin getAdminById(int adminId) {
        String sql = "SELECT * FROM admin WHERE hotel_id = ?";  
        Connection con = null;
        try {
            con = dbConnection.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getInt("hotel_id"),  
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                dbConnection.closeConnection(con);
            }
        }
        return null;
    }

    public boolean updateAdmin(Admin admin) {
        String sql = "UPDATE admin SET fullname = ?, email = ?, phone = ? WHERE hotel_id = ?";  
        Connection con = null;
        try {
            con = dbConnection.openConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, admin.getFullName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPhone());
            ps.setInt(4, admin.getId());  

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                dbConnection.closeConnection(con);
            }
        }
    }
}