/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import model.BookingConfirmationModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class BookingConfirmationDAOTest {
    
    public BookingConfirmationDAOTest() {
    }


    /**
     * Test of getConfirmationByBookingId method, of class BookingConfirmationDAO.
     */
    @Test
    public void testGetConfirmationByBookingId() {
        System.out.println("getConfirmationByBookingId");
        int bookingId = 1;
        BookingConfirmationDAO instance = new BookingConfirmationDAO();
        BookingConfirmationModel expResult = null;
        BookingConfirmationModel result = instance.getConfirmationByBookingId(bookingId);
        assertEquals(expResult, result);
    }

    /**
     * Test of createConfirmation method, of class BookingConfirmationDAO.
     */
    @Test

    public void testCreateConfirmation() {
        System.out.println("createConfirmation");
        int bookingId = 1;
        BookingConfirmationDAO instance = new BookingConfirmationDAO();
        boolean expResult = false;
        boolean result = instance.createConfirmation(bookingId);
        assertEquals(expResult, result);

    }
    
}
