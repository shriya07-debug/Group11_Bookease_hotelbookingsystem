package model;

public class AdminPerformanceModel {
    private int hotelId;
    private String username;
    private int totalBookings;
    private double totalRevenue;
    
  
    public AdminPerformanceModel() {}
    
    public AdminPerformanceModel(int hotelId, String username, int totalBookings, double totalRevenue) {
        this.hotelId = hotelId;
        this.username = username;
        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
    }
    
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId; 
    }
    public int getHotelId() {
        return hotelId; 
    }

    public void setUsername(String username) {
        this.username = username;
    }    
    public String getUsername() {
        return username;
    }

    public void setTotalBookings(int totalBookings) { 
        this.totalBookings = totalBookings; 
    }    
    public int getTotalBookings() { 
        return totalBookings; 
    }
    
    public void setTotalRevenue(double totalRevenue) { 
        this.totalRevenue = totalRevenue; 
    }    
    public double getTotalRevenue() {
        return totalRevenue;
    }
}