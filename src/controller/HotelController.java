package controller;

import dao.HotelDAO;
import model.HotelModel;
import view.hotelcard;
import javax.swing.*;
import java.awt.Image;

public class HotelController {
    private final HotelDAO hotelDAO;
    
    public HotelController() {
        hotelDAO = new HotelDAO();
    }
    
    public HotelModel searchHotel(String hotelName) {
        
        try {
            return hotelDAO.searchHotelByName(hotelName);
        } 
        
        catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public hotelcard createHotelCard(HotelModel hotel) {
    hotelcard card = new hotelcard();
    
    if (hotel != null) {
        
        card.getHotelIdLabel().setText("Hotel_id: " + hotel.getHotelId());
        card.getHotelNameLabel().setText("Hotel_name: " + hotel.getHotelName());
        card.getLocationLabel().setText("Location: " + hotel.getLocation());
        card.getRoomStatusLabel().setText("Room_status: " + hotel.getRoomStatus());
        card.getRatingLabel().setText("Rating: " + hotel.getRating());
        
        
        String imagePath = hotel.getImagePath();
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
               
                ImageIcon icon = new ImageIcon(imagePath);
                Image img = icon.getImage();
                Image scaledImg = img.getScaledInstance(300, 180, Image.SCALE_SMOOTH);
 
                card.setImage(new ImageIcon(scaledImg));
                
            } 
            
            catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }
    
    return card;
  }   
}