package model;

import java.util.Date;

public class BookingConfirmationModel {
    private int confirmationId;
    private int bookingId;
    private Date date;
    private String status;
    
    
    public BookingConfirmationModel() {}
    
    
    public BookingConfirmationModel(int confirmationId, int bookingId, 
                                   Date date, String status) {
        this.confirmationId = confirmationId;
        this.bookingId = bookingId;
        this.date = date;
        this.status = status;
    }
    
    
    public BookingConfirmationModel(int bookingId, String status) {
        this.bookingId = bookingId;
        this.status = status;
        this.date = new Date();
    }
    
    public void setConfirmationId(int confirmationId) { 
        this.confirmationId = confirmationId; 
    }  
    public int getConfirmationId() {
        return confirmationId;
    }
    
    public void setBookingId(int bookingId) { 
        this.bookingId = bookingId; 
    }
    public int getBookingId() { 
        return bookingId;
    }
    
    public void setDate(Date date) { 
        this.date = date; 
    }
    public Date getDate() { 
        return date;
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    public String getStatus() { 
        return status; 
    }
   
    public String getFormattedDate() {
        if (date != null) {
            return new java.text.SimpleDateFormat("dd-MMM-yyyy HH:mm").format(date);
        }
        return "N/A";
    }
    
    @Override
    public String toString() {
        return "BookingConfirmationModel{" +
               "confirmationId=" + confirmationId +
               ", bookingId=" + bookingId +
               ", date=" + getFormattedDate() +
               ", status='" + status + '\'' +
               '}';
    }
}