/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nirajhamal
 */
public class ViewdetailsModel {
    

    private int hotelId;
    private String hotelName;
    private String location;
    private String googleMapsUrl;
    private String contact;
    private double price;
    private double ratings;
    private String image;
    
    // Constructors
    public ViewdetailsModel () {}
    
    public ViewdetailsModel(String hotelName, String location, String googleMapsUrl, 
                 String contact, double price, double ratings, String image) {
        this.hotelName = hotelName;
        this.location = location;
        this.googleMapsUrl = googleMapsUrl;
        this.contact = contact;
        this.price = price;
        this.ratings = ratings;
        
        this.image = image;
    }
    
    
    public int getHotelId() { return hotelId; }
    public void setHotelId(int hotelId) { this.hotelId = hotelId; }
    
    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getGoogleMapsUrl() { return googleMapsUrl; }
    public void setGoogleMapsUrl(String googleMapsUrl) { this.googleMapsUrl = googleMapsUrl; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public double getRatings() { return ratings; }
    public void setRatings(double ratings) { this.ratings = ratings; }
    
 
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return image;
    }
}

