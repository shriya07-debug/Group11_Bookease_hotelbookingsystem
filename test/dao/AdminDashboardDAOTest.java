/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import model.AdminDashboardModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class AdminDashboardDAOTest {
    
    public AdminDashboardDAOTest() {
    }
    

    /**
     * Test of getData method, of class AdminDashboardDAO.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        AdminDashboardModel result = new AdminDashboardModel();
        result.setimage("testImage.png");
        assertNotNull(result);                
        assertEquals("testImage.png", result.getimage());
    }
}
