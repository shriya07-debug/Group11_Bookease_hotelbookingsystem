package model;

public class UserModel {
    private int userId;
    private Integer hotelId; 
    private String username;
    private String email;
    private String password;
    private String role;
    private String status;
    private String lastLogin;
    
    
    public UserModel() {}
    
    public UserModel(String username, String email, String password, String role, String status) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
    public void setUserId(int userId) { 
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }
    
    public void setHotelId(Integer hotelId) { 
        this.hotelId = hotelId;
    }
    public Integer getHotelId() {
        return hotelId; 
    }
    
    public void setUsername(String username) { 
        this.username = username;
    }
    public String getUsername() { 
        return username;
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
   
    public void setStatus(String status) {
        this.status = status;
    } 
    public String getStatus() {
        return status;
    }
   
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin; 
    } 
    public String getLastLogin() {
        return lastLogin;
    }
   
}