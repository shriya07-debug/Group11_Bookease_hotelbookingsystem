package dao;

import model.AdminPerformanceModel;
import java.sql.*;

public class AnalyticsDAO {
    private final Connection connection;
    
    public AnalyticsDAO(Connection connection) {
        this.connection = connection;
    }
    
    // In AnalyticsDAO.java
public Object[][] getHotelAdminsForTable() throws SQLException {
    String sql = "SELECT hotel_id, username, email FROM users WHERE role = 'hotel_admin'";
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    
    // Use ArrayList to collect rows
    java.util.ArrayList<Object[]> rows = new java.util.ArrayList<>();
    
    while(rs.next()) {
        Object[] row = new Object[4];
        try {
            row[0] = rs.getString("hotel_id"); // Changed from getInt
        } catch (Exception e) {
            row[0] = rs.getInt("hotel_id");
        }
        row[1] = rs.getString("username");
        row[2] = rs.getString("email");
        row[3] = "Show";
        rows.add(row);
    }
    
    // Convert to 2D array
    return rows.toArray(new Object[0][]);
}
    public AdminPerformanceModel getAdminPerformance(int hotelId) throws SQLException {
    // FIXED QUERY with GROUP BY
    String sql = "SELECT u.username, " +
                 "COUNT(b.booking_id) as bookings, " +
                 "IFNULL(SUM(b.total_price), 0) as revenue " +
                 "FROM users u " +
                 "LEFT JOIN bookings b ON u.hotel_id = b.hotel_id " +
                 "WHERE u.hotel_id = ? " +
                 "GROUP BY u.hotel_id, u.username"; // ADDED GROUP BY
    
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setInt(1, hotelId);
    ResultSet rs = stmt.executeQuery();
    
    if(rs.next()) {
        return new AdminPerformanceModel(
            hotelId,
            rs.getString("username"),
            rs.getInt("bookings"),
            rs.getDouble("revenue")
        );
    }
    
    return null;
}
}