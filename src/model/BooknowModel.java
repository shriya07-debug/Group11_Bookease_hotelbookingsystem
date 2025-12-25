package model;

import java.sql.Date;
import java.sql.Timestamp;

public class BooknowModel {
    private int id;
    private String roomType;
    private int numberOfPeople;
    private Date checkInDate;
    private Date checkOutDate;
    private Timestamp bookingDate;
    private String status;
    
    // Constructor
    public BooknowModel() {
        this.bookingDate = new Timestamp(System.currentTimeMillis());
        this.status = "Confirmed";
    }
    
    // Getters and Setters
  public int getId() { 
        return id; 
    }
    
    public void setId(int id) { 
        this.id = id; 
    }
    
    public String getRoomType() { 
        return roomType; 
    }
    
    public void setRoomType(String roomType) { 
        this.roomType = roomType; 
    }
    
    public int getNumberOfPeople() { 
        return numberOfPeople; 
    }
    
    public void setNumberOfPeople(int numberOfPeople) { 
        this.numberOfPeople = numberOfPeople; 
    }
    
    // Keep this for backward compatibility with controller
    public int getNumPeople() { 
        return numberOfPeople; 
    }
    
    public void setNumPeople(int numPeople) { 
        this.numberOfPeople = numPeople; 
    }
    
    public Date getCheckInDate() { 
        return checkInDate; 
    }
    
    public void setCheckInDate(Date checkInDate) { 
        this.checkInDate = checkInDate; 
    }
    
    public Date getCheckOutDate() { 
        return checkOutDate; 
    }
    
    public void setCheckOutDate(Date checkOutDate) { 
        this.checkOutDate = checkOutDate; 
    }
    
    public Timestamp getBookingDate() { 
        return bookingDate; 
    }
    
    public void setBookingDate(Timestamp bookingDate) { 
        this.bookingDate = bookingDate; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
}  