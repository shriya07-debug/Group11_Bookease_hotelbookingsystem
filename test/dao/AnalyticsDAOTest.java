/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import model.AdminPerformanceModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class AnalyticsDAOTest {
    
    public AnalyticsDAOTest() {
    }
    /**
     * Test of getHotelAdminsForTable method, of class AnalyticsDAO.
     */
    @Test
    public void testGetHotelAdminsForTable() throws Exception {
        System.out.println("getHotelAdminsForTable");
        Object[][] result = new Object[1][4];
        result[0][0] = 101; 
        result[0][1] = "admin1"; 
        result[0][2] = "admin1@example.com"; 
        result[0][3] = "Show"; 

        assertNotNull(result);
        assertEquals(4, result[0].length);
    }

    /**
     * Test of getAdminPerformance method, of class AnalyticsDAO.
     */
    @Test
    public void testGetAdminPerformance() throws Exception {
        System.out.println("getAdminPerformance");
        int hotelId = 101;
        AdminPerformanceModel result = new AdminPerformanceModel(hotelId,"admin1",5,1000.0);
        assertNotNull(result);
        assertEquals(hotelId, result.getHotelId());
        assertEquals("admin1", result.getUsername());
        assertTrue(result.getTotalBookings() >= 0);
        assertTrue(result.getTotalRevenue() >= 0.0); 
       
    }
    
}
