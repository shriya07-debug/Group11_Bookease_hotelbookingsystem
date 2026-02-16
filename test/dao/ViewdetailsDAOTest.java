/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import model.ViewdetailsModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class ViewdetailsDAOTest {
    
    public ViewdetailsDAOTest() {
    }
    
    /**
     * Test of getHotelById method, of class ViewdetailsDAO.
     */
    @Test
    public void testGetHotelById() {
        System.out.println("getHotelById");
        int hotelId = 101;
        ViewdetailsDAO instance = new ViewdetailsDAO();
        ViewdetailsModel result = instance.getHotelById(hotelId);
        assertNotNull(result);
    }
    
}
