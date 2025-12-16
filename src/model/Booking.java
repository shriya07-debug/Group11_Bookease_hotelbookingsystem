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
    private Date Check_in_date;
    private Date Check_out_date;
    private String Status;
    private double Price;
    
    // Constructor
    public Booking() {}
    
    public Booking(int booking_ID, Date Check_in_date, Date Check_out_date, String Status, double Price) {
        this.booking_ID = booking_ID;
        this.Check_in_date = Check_in_date;
        this.Check_out_date = Check_out_date;
        this.Status = Status;
        this.Price = Price;
    }
    
    // Getters and Setters
    public int getBooking_ID() { 
        return booking_ID; 
    }
    
    public void setBooking_ID(int booking_ID) { 
        this.booking_ID = booking_ID; 
    }
    
    public Date getCheck_in_date() { 
        return Check_in_date; 
    }
    
    public void setCheck_in_date(Date Check_in_date) { 
        this.Check_in_date = Check_in_date; 
    }
    
    public Date getCheck_out_date() { 
        return Check_out_date; 
    }
    
    public void setCheck_out_date(Date Check_out_date) { 
        this.Check_out_date = Check_out_date; 
    }
    
    public String getStatus() { 
        return Status; 
    }
    
    public void setStatus(String Status) { 
        this.Status = Status; 
    }
    
    public double getPrice() { 
        return Price; 
    }
    
    public void setPrice(double Price) { 
        this.Price = Price; 
    }
}

