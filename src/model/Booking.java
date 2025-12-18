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
    private int booking_ID;
    private String hotelName;
    private Date Check_in_date;
    private Date Check_out_date;
    private String Status;
    private double Price;
    
    // Constructor with hotelName
    public Booking(int booking_ID, String hotelName, Date Check_in_date, 
                   Date Check_out_date, String Status, double Price) {
        this.booking_ID = booking_ID;
        this.hotelName = hotelName;  
        this.Check_in_date = Check_in_date;
        this.Check_out_date = Check_out_date;
        this.Status = Status;
        this.Price = Price;
    }
    
    // Default constructor
    public Booking() {}
    
    // Getters and Setters
    public void setBooking_ID(int booking_ID) { 
        this.booking_ID = booking_ID; 
    }
    public int getBooking_ID() { 
        return booking_ID;
    }
    
    public void setHotelName(String hotelName) { 
        this.hotelName = hotelName;
    } 
    
    public String getHotelName() { 
        return hotelName; 
    }       
  
    public void setCheck_in_date(Date Check_in_date) {
        this.Check_in_date = Check_in_date; 
    }
    public Date getCheck_in_date() { 
        return Check_in_date;
    }
   
     public void setCheck_out_date(Date Check_out_date) { 
        this.Check_out_date = Check_out_date; 
    }
    
    public Date getCheck_out_date() { 
        return Check_out_date; 
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