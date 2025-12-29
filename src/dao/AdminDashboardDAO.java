package dao;

import model.AdminDashboardModel;
import database.MySqlConnection;
import java.sql.*;

public class AdminDashboardDAO {
    public AdminDashboardModel getData() {
        AdminDashboardModel model = new AdminDashboardModel();
        Connection conn = null;
        
        try {
            // Use your existing MySqlConnection
            MySqlConnection db = new MySqlConnection();
            conn = db.openConnection();
            
            String query = "SELECT image FROM admindashboard LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                model.setimage(rs.getString("image"));
                
            }
            
            // Close connection using your method
            db.closeConnection(conn);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return model;
    }
}