/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.HotelDAO;
import model.HotelModel;
import view.hotelcard;

public class HotelController {
    private final HotelDAO hotelDAO;
    
    public HotelController() {
        hotelDAO = new HotelDAO();
    }
    
    public HotelModel searchHotel(String hotelName) {
//        return hotelDAO.searchHotelByName(hotelName);
        try {
        return hotelDAO.searchHotelByName(hotelName);
    } catch (Exception e) {
        System.out.println("Controller error: " + e.getMessage());
        return null;
    }
  }
    
    public hotelcard createHotelCard(HotelModel hotel) {
        hotelcard card = new hotelcard();
        
        if (hotel != null) {
            card.setHotelId(String.valueOf(hotel.getHotelId()));
            card.setHotelName(hotel.getHotelName());
            card.setLocation(hotel.getLocation());
            card.setRoomStatus(hotel.getRoomStatus());
            card.setRating(hotel.getRating());
            card.setImage(hotel.getImagePath());
        }
        
        return card;
    }
    
    public boolean bookHotel(int hotelId, int userId, String checkIn, String checkOut) {
        // Add booking logic here
        return true;
    }
}
