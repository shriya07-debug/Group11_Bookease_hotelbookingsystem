/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import java.util.List;
import model.UserModel;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class UserDAOTest {
    
    public UserDAOTest() {
    }
    
  

    /**
     * Test of emailExists method, of class UserDAO.
     */
    @Test
    public void testEmailExists() {
        System.out.println("emailExists");
        String email = "shriyaawale2007@gmail.com";
        UserDAO instance = new UserDAO();
        boolean expResult = false;
        boolean result = instance.emailExists(email);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class UserDAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String email = "shriyaawale2007@gmail.com";
        String password = "shriyaawale";
        UserDAO instance = new UserDAO();
        UserModel expResult = null;
        UserModel result = instance.login(email, password);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of signup method, of class UserDAO.
     */
    @Test
    public void testSignup() {
        System.out.println("signup");
        UserModel user = new UserModel("Shriya","awaleshriya@gmail.com","shriyaawale","user","active");
        UserDAO instance = new UserDAO();
        boolean expResult = true;
        boolean result = instance.signup(user);
//        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of createHotelAdmin method, of class UserDAO.
     */
    @Test
    public void testCreateHotelAdmin() {
        System.out.println("createHotelAdmin");
        int hotelId = 0;
        String email = "";
        String password = "";
        UserDAO instance = new UserDAO();
        boolean expResult = false;
        boolean result = instance.createHotelAdmin(hotelId, email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllHotelAdmins method, of class UserDAO.
     */
    @Test
    public void testGetAllHotelAdmins() {
        System.out.println("getAllHotelAdmins");
        UserDAO instance = new UserDAO();
        List<UserModel> expResult = null;
        List<UserModel> result = instance.getAllHotelAdmins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
