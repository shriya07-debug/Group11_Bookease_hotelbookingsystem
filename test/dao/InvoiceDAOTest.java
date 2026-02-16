/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

import model.InvoiceModel;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author sailenawale
 */
public class InvoiceDAOTest {
    
    public InvoiceDAOTest() {
    }
 

    /**
     * Test of getInvoiceById method, of class InvoiceDAO.
     */
    @Test
    public void testGetInvoiceById() {
        System.out.println("getInvoiceById");
        int invoiceId = -1;
        InvoiceDAO instance = new InvoiceDAO();
        InvoiceModel expResult = null;
        InvoiceModel result = instance.getInvoiceById(invoiceId);
        assertEquals(expResult, result);
    }  
}
