package model;

public class AdminProfileModel {
    private String hotelId;
    private String fullName;
    private String email;
    private String phone;
    
    // Constructors
    public AdminProfileModel(int aInt, String string, String string1) {}
    
    public AdminProfileModel(String hotelId, String fullName, String email, String phone) {
        this.hotelId = hotelId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters and Setters
    public String getHotelId() { return hotelId; }
    public void setHotelId(String hotelId) { this.hotelId = hotelId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
}