/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import java.util.List;
import model.CustomerBookingModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class CustomerBookingDAOTest {
    
    public CustomerBookingDAOTest() {
    }
 

    /**
     * Test of getAllCustomerBookings method, of class CustomerBookingDAO.
     */
    @Test
    public void testGetAllCustomerBookings() {
        System.out.println("getAllCustomerBookings");
        CustomerBookingDAO instance = new CustomerBookingDAO();
        List<CustomerBookingModel> result = instance.getAllCustomerBookings();
        assertNotNull(result);
    }   
}
