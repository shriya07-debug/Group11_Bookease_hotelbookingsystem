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
    

    public BooknowModel() {}
    
   
    public BooknowModel(int id, String roomType, int numberOfPeople, 
                       Date checkInDate, Date checkOutDate) {
        this.id = id; 
        this.roomType = roomType;
        this.numberOfPeople = numberOfPeople;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingDate = new Timestamp(System.currentTimeMillis());
        this.status = "Confirmed";
    }
    
    
    public BooknowModel(int id, String roomType, int numberOfPeople, 
                       Date checkInDate, Date checkOutDate, 
                       Timestamp bookingDate, String status) {
        this.id = id; 
        this.roomType = roomType;
        this.numberOfPeople = numberOfPeople;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingDate = bookingDate;
        this.status = status;
    }
    

    public void setId(int id) { 
        this.id = id; 
    }
    public int getId() { 
        return id; 
    }
    
    public void setRoomType(String roomType) { 
        this.roomType = roomType; 
    }
    public String getRoomType() { 
        return roomType; 
    }
    
    public void setNumberOfPeople(int numberOfPeople) { 
        this.numberOfPeople = numberOfPeople; 
    }
    public int getNumberOfPeople() { 
        return numberOfPeople; 
    }
   
    
    public void setCheckInDate(Date checkInDate) { 
        this.checkInDate = checkInDate; 
    }
    public Date getCheckInDate() { 
        return checkInDate; 
    }
   
    public void setCheckOutDate(Date checkOutDate) { 
        this.checkOutDate = checkOutDate; 
    }
    public Date getCheckOutDate() { 
        return checkOutDate; 
    }
    
    public void setBookingDate(Timestamp bookingDate) { 
        this.bookingDate = bookingDate; 
    }
    public Timestamp getBookingDate() { 
        return bookingDate; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    public String getStatus() { 
        return status; 
    }
}