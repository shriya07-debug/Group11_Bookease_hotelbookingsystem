/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;
import java.util.List;
import model.SuperAdminModel;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author sailenawale
 */
public class SuperAdminDAOTest {
    
    public SuperAdminDAOTest() {
    }
/*
     * Test of createHotelAdmin method, of class SuperAdminDAO.
     */
    @Test
    public void testCreateHotelAdmin() {
        System.out.println("createHotelAdmin");
       
        SuperAdminDAO instance = new SuperAdminDAO();
        SuperAdminModel admin = new SuperAdminModel();
        admin.setHotelId(107);  
        admin.setPassword("test123");
        admin.setEmail("testhoteladmin@test.com");
        admin.setHotelName("Test Hotel");
        boolean expResult = false;
        boolean result = instance.createHotelAdmin(admin);
        assertEquals(expResult, result);
    }

    /**
     * Test of emailExists method, of class SuperAdminDAO.
     */
    @Test
    public void testEmailExists() {
        System.out.println("emailExists");
        String email = "test123@gmail.com";
        SuperAdminDAO instance = new SuperAdminDAO();
        boolean expResult = false;
        boolean result = instance.emailExists(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of hotelExists method, of class SuperAdminDAO.
     */
    @Test
    public void testHotelExists() {
        System.out.println("hotelExists");
        int hotelId = -1;
        SuperAdminDAO instance = new SuperAdminDAO();
        boolean expResult = false;
        boolean result = instance.hotelExists(hotelId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllHotelAdmins method, of class SuperAdminDAO.
     */
    @Test
    public void testGetAllHotelAdmins() {
        System.out.println("getAllHotelAdmins");
        SuperAdminDAO instance = new SuperAdminDAO();
        List<SuperAdminModel> result = instance.getAllHotelAdmins();
        assertNotNull(result);
    }
}
