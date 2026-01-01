/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class SuperAdminModel {
    private int hotelId;
    private String hotelName;
    private String email;
    private String password;
    private String role = "hotel_admin"; // Default role
    
    public SuperAdminModel() {}
    
    public SuperAdminModel(int hotelId,String hotelName, String email, String password) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.email = email;
        this.password = password;
    }
    
    // Getters and setters
    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }
    
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getHotelName(){
        return hotelName;
    }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
