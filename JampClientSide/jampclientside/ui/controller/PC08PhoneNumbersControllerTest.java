/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.TelephoneBean;
import jampclientside.logic.TelephoneLogic;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrador
 */
public class PC08PhoneNumbersControllerTest {
    
    public PC08PhoneNumbersControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStage method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testGetStage() {
        System.out.println("getStage");
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        Stage expResult = null;
        Stage result = instance.getStage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStage method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testSetStage() {
        System.out.println("setStage");
        Stage stage = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.setStage(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setILogic method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testSetILogic() {
        System.out.println("setILogic");
        TelephoneLogic iLogicTelephone = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.setILogic(iLogicTelephone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUser method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        TelephoneBean telephone = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.setUser(telephone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initStage method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testInitStage() throws Exception {
        System.out.println("initStage");
        Parent root = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.initStage(root);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logOutAction method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testLogOutAction() {
        System.out.println("logOutAction");
        ActionEvent event = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.logOutAction(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cerrarSesionAlert method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testCerrarSesionAlert() {
        System.out.println("cerrarSesionAlert");
        int cerrar = 0;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.cerrarSesionAlert(cerrar);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleAddTelephone method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testHandleAddTelephone() {
        System.out.println("handleAddTelephone");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.handleAddTelephone(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleDeleteTelephone method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testHandleDeleteTelephone() {
        System.out.println("handleDeleteTelephone");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.handleDeleteTelephone(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eventWindow method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testEventWindow() {
        System.out.println("eventWindow");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.eventWindow(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of productWindow method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testProductWindow() {
        System.out.println("productWindow");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.productWindow(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of expenseWindow method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testExpenseWindow() {
        System.out.println("expenseWindow");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.expenseWindow(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of usersWindow method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testUsersWindow() {
        System.out.println("usersWindow");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.usersWindow(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of FTPClientWindow method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testFTPClientWindow() {
        System.out.println("FTPClientWindow");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.FTPClientWindow(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of comboBoxOption method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testComboBoxOption() {
        System.out.println("comboBoxOption");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.comboBoxOption(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchButton method, of class PC08PhoneNumbersController.
     */
    @Test
    public void testSearchButton() {
        System.out.println("searchButton");
        ActionEvent ev = null;
        PC08PhoneNumbersController instance = new PC08PhoneNumbersController();
        instance.searchButton(ev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
