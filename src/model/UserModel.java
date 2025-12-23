package model;

public class UserModel {
    // Private fields
    private int userId;
    private String username;
    private String email;
    private String password;
    private String role;
    private Integer hotelId;
    private String status;
    
    // =============== CONSTRUCTORS ===============
    
    // Empty constructor
    public UserModel() {
    }
    
    // Constructor for LOGIN (minimal fields)
    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // Constructor for SIGNUP
    public UserModel(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = "pending";
    }
    
    // Full constructor (all fields)
    public UserModel(int userId, String username, String email, String password, 
                     String role, Integer hotelId, String status) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.hotelId = hotelId;
        this.status = status;
    }
    
    // =============== GETTERS ===============
    
    public int getUserId() {
        return userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
    
    public Integer getHotelId() {
        return hotelId;
    }
    
    public String getStatus() {
        return status;
    }
    
    // =============== SETTERS ===============
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    // =============== UTILITY METHODS ===============
    
    public boolean isActive() {
        return "active".equalsIgnoreCase(this.status);
    }
    
    public boolean isAdmin() {
        return "superadmin".equalsIgnoreCase(this.role) || 
               "hotel_admin".equalsIgnoreCase(this.role);
    }
    
    public boolean isValidForLogin() {
        return email != null && !email.trim().isEmpty() && 
               password != null && !password.trim().isEmpty();
    }
    
    @Override
    public String toString() {
        return "UserModel{" +
               "userId=" + userId +
               ", username='" + username + '\'' +
               ", email='" + email + '\'' +
               ", role='" + role + '\'' +
               ", status='" + status + '\'' +
               ", hotelId=" + hotelId +
               '}';
    }
}