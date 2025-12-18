/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Date;
/**
 *
 * @author sailenawale
 */
    public class Booking {
    private int bookingId;
    private int userId; 
    private String hotelName;
    private Date CheckInDate;
    private Date CheckOutDate;
    private String Status;
    private double Price;
    
    // Constructor with hotelName
    public Booking(int bookingId,int userId, String hotelName, Date CheckInDate, 
                   Date CheckOutDate, String Status, double Price) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.hotelName = hotelName;  
        this.CheckInDate = CheckInDate;
        this.CheckOutDate = CheckOutDate;
        this.Status = Status;
        this.Price = Price;
    }
    
    // Default constructor
    public Booking() {}
    
    // Getters and Setters
    public void setBookingId(int bookingId) { 
        this.bookingId = bookingId; 
    }
    public int getBookingId() { 
        return bookingId;
    }
    public void setUserId(int userId) { 
        this.userId = userId; 
    }
    public int getUserId() { 
        return userId;
    }
    public void setHotelName(String hotelName) { 
        this.hotelName = hotelName;
    } 
    
    public String getHotelName() { 
        return hotelName; 
    }       
  
    public void setCheckInDate(Date CheckInDate) {
        this.CheckInDate = CheckInDate; 
    }
    public Date getCheckInDate() { 
        return CheckInDate;
    }
   
     public void setCheckOutDate(Date CheckOutDate) { 
        this.CheckOutDate = CheckOutDate; 
    }
    
    public Date getCheckOutDate() { 
        return CheckOutDate; 
    }
  
    public void setStatus(String Status) {
        this.Status = Status;
    }
    public String getStatus() { 
        return Status; 
    }
    
    
    public void setPrice(double Price) { 
        this.Price = Price;
    }
    public double getPrice() {
        return Price; 
    }
   
}