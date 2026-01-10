/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class ForgotPasswordDaoTest {
    
    public ForgotPasswordDaoTest() {
    }
    
 
    /**
     * Test of emailExists method, of class ForgotPasswordDao.
     */
    @Test
    public void testEmailExists() {
        System.out.println("emailExists");
        String email = "";
        ForgotPasswordDao instance = new ForgotPasswordDao();
        boolean expResult = false;
        boolean result = instance.emailExists(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePassword method, of class ForgotPasswordDao.
     */
    @Test
    public void testUpdatePassword() {
        System.out.println("updatePassword");
        String email = "shriyaawale2007@gmail.com";
        String newPassword = "bookease";
        ForgotPasswordDao instance = new ForgotPasswordDao();
        boolean expResult = false;
        boolean result = instance.updatePassword(email, newPassword);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
