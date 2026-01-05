package dao;

import model.AdminDashboardModel;
import database.MySqlConnection;
import java.sql.*;

public class AdminDashboardDAO {
    
    public AdminDashboardModel getData() {
        AdminDashboardModel model = new AdminDashboardModel();
        Connection conn = null;
        
        try {
          
            
            MySqlConnection db = new MySqlConnection();
            conn = db.openConnection();
            
            String query = "SELECT image FROM admindashboard LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                model.setimage(rs.getString("image"));
                
            }
            
            
            db.closeConnection(conn);
            
        }
        
        catch (SQLException e) {
            System.out.println(e);
        }
        
        return model;
    }
}