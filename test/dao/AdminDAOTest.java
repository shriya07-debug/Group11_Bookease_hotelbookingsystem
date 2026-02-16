/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import model.Admin;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class AdminDAOTest {
    
    public AdminDAOTest() {
    }


    /**
     * Test of getAdminById method, of class AdminDAO.
     */
    @Test
    public void testGetAdminById() {
        System.out.println("getAdminById");
        int adminId = -1;
        AdminDAO instance = new AdminDAO();
        Admin expResult = null;
        Admin result = instance.getAdminById(adminId);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateAdmin method, of class AdminDAO.
     */
    @Test
    public void testUpdateAdmin() {
        System.out.println("updateAdmin");
        Admin admin = new Admin();
        admin.setId(10000);           
        AdminDAO instance = new AdminDAO();
        boolean expResult = false;
        boolean result = instance.updateAdmin(admin);
        assertEquals(expResult, result);
    }
    
}
