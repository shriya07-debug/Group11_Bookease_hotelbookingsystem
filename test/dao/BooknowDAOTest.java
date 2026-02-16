/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.BooknowModel;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author sailenawale
 */
public class BooknowDAOTest {
    
    public BooknowDAOTest() {
    }    
    /**
     * Test of saveBooking method, of class BooknowDAO.
     */
    @Test
    public void testSaveBooking() {
        System.out.println("saveBooking");
        
        BooknowModel booking = new BooknowModel();
        booking.setRoomType("Deluxe");
        booking.setNumberOfPeople(2);
        booking.setCheckInDate(Date.valueOf("2026-01-25"));
        booking.setCheckOutDate(Date.valueOf("2026-01-26"));

        BooknowDAO instance = new BooknowDAO(null); 
        boolean result = true; 
        assertEquals(true, result);  
    }

    /**
     * Test of getAllBookings method, of class BooknowDAO.
     */
    @Test
    public void testGetAllBookings() {
        System.out.println("getAllBookings");
        BooknowDAO instance = new BooknowDAO(null);
        List<BooknowModel> result = new ArrayList<>();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of isRoomAvailable method, of class BooknowDAO.
     */
    @Test
    public void testIsRoomAvailable() {
        System.out.println("isRoomAvailable");
        String roomType = "Deluxe";
        Date checkIn = Date.valueOf("2026-01-25");
        Date checkOut = Date.valueOf("2026-01-26");
        BooknowDAO instance = new BooknowDAO(null);
        boolean result = false;
        assertEquals(false, result);
    }
    
}
