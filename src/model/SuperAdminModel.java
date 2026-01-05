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
    private String role = "hotel_admin"; 
    
    public SuperAdminModel() {}
    
    public SuperAdminModel(int hotelId,String hotelName, String email, String password) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.email = email;
        this.password = password;
    }
    
    public void setHotelId(int hotelId) { 
        this.hotelId = hotelId; 
    }
    public int getHotelId() {
        return hotelId; 
    }
     
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public String getHotelName(){
        return hotelName;
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }  
    public String getEmail() { 
        return email; 
    }
    
    public void setPassword(String password) { 
        this.password = password; 
    } 
    public String getPassword() {
        return password; 
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
    
}
