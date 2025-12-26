/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sailenawale
 */
public class HotelModel {
    private int hotel_id;
    private String hotel_name;
    private String location;
    private String room_status;
    private double rating;
    private String image;
    
    
    public HotelModel() {}
    
  
    public HotelModel(int hotel_id, String hotel_name, String location,String room_status, double rating, String image) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.location = location;
        this.room_status = room_status;
        this.rating = rating;
        this.image = image;
    }
    
    public void setHotelId(int hotel_id) { 
        this.hotel_id = hotel_id; 
    }
    public int getHotelId() { 
        return hotel_id; 
    }
    public void setHotelName(String hotel_name) { 
        this.hotel_name = hotel_name;    
    }
    
    public String getHotelName() { 
        return hotel_name; 
    }
    public void setLocation(String location) { 
        this.location = location; 
    }
    public String getLocation() { 
        return location; 
    }
    public void setRoomStatus(String room_status) { 
        this.room_status = room_status; 
    }
    public String getRoomStatus() { 
        return room_status; 
    }
     public void setRating(double rating) { 
        this.rating = rating; 
    }
    public double getRating() { 
        return rating; 
    }
    public void setImagePath(String image) { 
        this.image = image; 
    }
    public String getImagePath() { 
        return image; 
    }
}

