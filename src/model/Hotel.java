/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author sailenawale
 */
public class Hotel {
    private int hotel_id;
    private String name;
    private String location;
    private String description;
    private double rating;
    private String image;
    
    
    public Hotel() {}
    
  
    public Hotel(int hotel_id, String name, String location, double rating, String image) {
        this.hotel_id = hotel_id;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.image = image;
    }
    
    public void setHotelId(int hotel_id) { 
        this.hotel_id = hotel_id; 
    }
    public int getHotelId() { 
        return hotel_id; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    
    public String getName() { 
        return name; 
    }
    public void setLocation(String location) { 
        this.location = location; 
    }
    public String getLocation() { 
        return location; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }
    public String getDescription() { 
        return description; 
    }
     public void setRating(double rating) { 
        this.rating = rating; 
    }
    public double getRating() { 
        return rating; 
    }
    public void setImage(String image) { 
        this.image = image; 
    }
    public String getImage() { 
        return image; 
    }
}

