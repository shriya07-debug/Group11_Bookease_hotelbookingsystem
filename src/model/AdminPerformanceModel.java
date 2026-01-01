package model;

public class AdminPerformanceModel {
    private int hotelId;
    private String username;
    private int totalBookings;
    private double totalRevenue;
    
    // Constructors
    public AdminPerformanceModel() {
        // Default constructor
    }
    
    public AdminPerformanceModel(int hotelId, String username, int totalBookings, double totalRevenue) {
        this.hotelId = hotelId;
        this.username = username;
        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
    }
    
    // Getters and setters
    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public int getTotalBookings() { return totalBookings; }
    public void setTotalBookings(int totalBookings) { this.totalBookings = totalBookings; }
    
    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }
}