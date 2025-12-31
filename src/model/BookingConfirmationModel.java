package model;

import java.util.Date;

public class BookingConfirmationModel {
    private int confirmationId;
    private int bookingId;
    private Date date;
    private String status;
    
    // Empty constructor
    public BookingConfirmationModel() {
    }
    
    // Constructor from database
    public BookingConfirmationModel(int confirmationId, int bookingId, 
                                   Date date, String status) {
        this.confirmationId = confirmationId;
        this.bookingId = bookingId;
        this.date = date;
        this.status = status;
    }
    
    // Constructor for new confirmation
    public BookingConfirmationModel(int bookingId, String status) {
        this.bookingId = bookingId;
        this.status = status;
        this.date = new Date();
    }
    
    // Getters
    public int getConfirmationId() { return confirmationId; }
    public int getBookingId() { return bookingId; }
    public Date getDate() { return date; }
    public String getStatus() { return status; }
    
    // Setters
    public void setConfirmationId(int confirmationId) { 
        this.confirmationId = confirmationId; 
    }
    
    public void setBookingId(int bookingId) { 
        this.bookingId = bookingId; 
    }
    
    public void setDate(Date date) { 
        this.date = date; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    // Format date for display
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