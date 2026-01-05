/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class ProfileModel {
    private int userId;
    private String fullName;
    private String email;
    private String phone;
    private String photoPath;
 
    public ProfileModel() {}
    
    public ProfileModel(int userId, String fullName, String email, String phone, String photoPath) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.photoPath = photoPath;
    }
    
    public void setUserId(int userId) {
        this.userId = userId; 
    }
    public int getUserId() {
        return userId; 
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getFullName() { 
        return fullName; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    public String getEmail() {
        return email; 
    }
    
    public void setPhone(String phone) {
        this.phone = phone; 
    }
    public String getPhone() {
        return phone; 
    }
    
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    public String getPhotoPath() {
        return photoPath;
    }
}