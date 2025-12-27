/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sailenawale
 */
public class ReviewModel {
    private final String hotelName;    
    private final String userName;     
    private final String comment;      
    
    
    public ReviewModel(String hotelName, String userName, String comment) {
        this.hotelName = hotelName;
        this.userName = userName;
        this.comment = comment;
    }
    
    
    public String getHotelName() {
        return hotelName;
    }
    public String getUserName() { 
        return userName;
    }
    public String getComment() { 
        return comment;
    }
    
    @Override
    public String toString() {
        return userName + ": " + comment;
    }
}

