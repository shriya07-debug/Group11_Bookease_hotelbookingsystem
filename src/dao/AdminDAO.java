package dao;

import java.sql.*;
import model.Admin;

public class AdminDAO {

    private final String URL = "jdbc:mysql://localhost:3306/bookease";
    private final String USER = "root";
    private final String PASS = "1234";

    public Admin getAdminById(int adminId) {
        String sql = "SELECT * FROM admin WHERE admin_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, adminId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("photo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
