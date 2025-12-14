/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.HotelDAO;
import model.Hotel;
import java.util.List;

public class hotelcontroller {
    
    private HotelDAO hotelDAO;
    
    public hotelcontroller() {
        this.hotelDAO = new HotelDAO();
    }
    
    // ✅ ONLY THIS for search functionality
    public List<Hotel> searchHotels(String keyword) {
        return hotelDAO.searchHotels(keyword);
    }
}