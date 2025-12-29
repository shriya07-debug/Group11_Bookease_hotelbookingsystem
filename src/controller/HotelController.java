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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public hotelcard createHotelCard(HotelModel hotel) {
        hotelcard card = new hotelcard();
        
        if (hotel != null) {
            // Set text data
            card.getHotelIdLabel().setText("Hotel_id: " + hotel.getHotelId());
            card.getHotelNameLabel().setText("Hotel_name: " + hotel.getHotelName());
            card.getLocationLabel().setText("Location: " + hotel.getLocation());
            card.getRoomStatusLabel().setText("Room_status: " + hotel.getRoomStatus());
            card.getRatingLabel().setText("Rating: " + hotel.getRating());
            
            // Set image with SCALING
            String imagePath = hotel.getImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                try {
                    // Load image
                    ImageIcon icon = new ImageIcon(imagePath);
                    
                    // Scale to 280x180 (your jLabel1 size)
                    Image img = icon.getImage();
                    Image scaledImg = img.getScaledInstance(300, 180, Image.SCALE_SMOOTH);
                    
                    // Set scaled image
                    card.setImage(new ImageIcon(scaledImg));
                    
                } catch (Exception e) {
                    System.out.println("Image not loaded: " + imagePath);
                }
            }
            
            // Set button action
            card.addViewDetailsListener(e -> {
                showHotelDetails(hotel);
            });
        }
        
        return card;
    }
    
    private void showHotelDetails(HotelModel hotel) {
        String details = "Hotel Details:\n\n" +
                        "ID: " + hotel.getHotelId() + "\n" +
                        "Name: " + hotel.getHotelName() + "\n" +
                        "Location: " + hotel.getLocation() + "\n" +
                        "Status: " + hotel.getRoomStatus() + "\n" +
                        "Rating: " + hotel.getRating();
        
        JOptionPane.showMessageDialog(null, details, "Hotel Details", JOptionPane.INFORMATION_MESSAGE);
    }
}