/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import java.util.List;
import model.BookingModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class BookingDAOTest {
    
    public BookingDAOTest() {
    }

    /**
     * Test of getUserBookings method, of class BookingDAO.
     */
    @Test
    public void testGetUserBookings() {
        System.out.println("getUserBookings");
        int userId = -1;
        BookingDAO instance = new BookingDAO();
        List<BookingModel> result = instance.getUserBookings(userId);     
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    
}
