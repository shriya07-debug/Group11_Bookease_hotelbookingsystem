/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import model.ProfileModel;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sailenawale
 */
public class ProfileDAOTest {
    
    public ProfileDAOTest() {
    }

    /**
     * Test of getProfileById method, of class ProfileDAO.
     */
    @Test
    public void testGetProfileById() {
        System.out.println("getProfileById");
        int userId = -1;
        ProfileDAO instance = new ProfileDAO();
        ProfileModel expResult = null;
        ProfileModel result = instance.getProfileById(userId);
        assertEquals(expResult, result);

    }

    /**
     * Test of updateProfile method, of class ProfileDAO.
     */
    @Test
    public void testUpdateProfile() {
        System.out.println("updateProfile");
        ProfileModel profile = null;
        ProfileDAO instance = new ProfileDAO();
        boolean expResult = false;
        boolean result = instance.updateProfile(profile);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateProfileWithPhoto method, of class ProfileDAO.
     */
    @Test
    public void testUpdateProfileWithPhoto() {
        System.out.println("updateProfileWithPhoto");
     
        ProfileDAO instance = new ProfileDAO();
        ProfileModel profile = new ProfileModel();
        profile.setUserId(-1); 
        profile.setFullName("test");
        boolean expResult = false;
        boolean result = instance.updateProfileWithPhoto(profile);
        assertEquals(expResult, result);

    }
    
}
