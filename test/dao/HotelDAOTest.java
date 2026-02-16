/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.HotelModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class HotelDAOTest {
    
    public HotelDAOTest() {
    }

    /**
     * Test of getHotelById method, of class HotelDAO.
     */
    @Test
    public void testGetHotelById() {
        System.out.println("getHotelById");
        int hotelId = -10;
        HotelDAO instance = new HotelDAO();
        HotelModel expResult = null;
        HotelModel result = instance.getHotelById(hotelId);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchHotelByName method, of class HotelDAO.
     */
    @Test
    public void testSearchHotelByName() {
        System.out.println("searchHotelByName");
        String name = "testhotel";
        HotelDAO instance = new HotelDAO();
        HotelModel expResult = null;
        HotelModel result = instance.searchHotelByName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllHotels method, of class HotelDAO.
     */
    @Test
    public void testGetAllHotels() {
        System.out.println("getAllHotels");
        HotelDAO instance = new HotelDAO();
        List<HotelModel> result = instance.getAllHotels();
        assertNotNull(result);

    }

    /**
     * Test of addHotel method, of class HotelDAO.
     */
    @Test
    public void testAddHotel() {
        System.out.println("addHotel");
        HotelModel hotel = null;
        HotelDAO instance = new HotelDAO();
        boolean expResult = false;
        boolean result = instance.addHotel(hotel);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailableHotels method, of class HotelDAO.
     */
    @Test
    public void testGetAvailableHotels() {
        System.out.println("getAvailableHotels");
        HotelDAO instance = new HotelDAO();
        List<HotelModel> result = instance.getAvailableHotels();
        assertNotNull(result);
    }
    
}
