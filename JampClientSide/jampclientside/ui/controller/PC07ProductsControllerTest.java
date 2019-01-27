/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.UiApplication;
import jampclientside.UiApplicationProduct;
import jampclientside.logic.ProductLogic;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import messageuserbean.UserBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Test class for PC07Products Controller
 * 
 * @author Julen
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PC07ProductsControllerTest extends ApplicationTest {
    
    /**
     * Method start the application test
     * 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        new UiApplicationProduct().start(stage);//llamo a la aplicacion que me abre la ventana
    }

    @Test
    public void testA_initialStage() {

        /*clickOn("#tfUsuario");
        write("ander");
        clickOn("#pfContraseña");
        write("12345678");
        clickOn("#btnInicio");*/
        
        //verifyThat("#principalPane", isVisible());
        
        //clickOn("#menuProductos");
        //clickOn("#idMenuProductos");
        
        verifyThat("#menuBar", isVisible());
        verifyThat("#menuMenu", isVisible());
        verifyThat("#menuEvent", isVisible());
        verifyThat("#menuExpense", isVisible());
        verifyThat("#menuProduct", isVisible());
        verifyThat("#menuUser", isVisible());
        verifyThat("#menuTelephon", isVisible());
        verifyThat("#txtSearch", isDisabled());
        //verifyThat("#tbProducts", isEditable());
        verifyThat("#btnSearch", isDisabled());
        verifyThat("#delProduct", isDisabled());
        verifyThat("#asignProduct", isDisabled());
        verifyThat("#unasignProduct", isDisabled());
        verifyThat("#tbProducts", isVisible());
        verifyThat("#tbcolName", isVisible());
        verifyThat("#tbcolDescription", isVisible());
        verifyThat("#tbcolPrice", isVisible());
        verifyThat("#tbcolStock", isVisible());
        //verifyThat("#lblLogin", hasText("Login: ander"));
        //verifyThat("#lblFullName", hasText("Nombre Completo: ander olivas"));
        //verifyThat("#lblEmail", hasText("Email: anderolivas@gmail.com"));
        
        clickOn("#cbSearch");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        verifyThat("#tbProducts", isVisible());
        verifyThat("#tbcolName", isVisible());
        verifyThat("#tbcolDescription", isVisible());
        verifyThat("#tbcolPrice", isVisible());
        verifyThat("#tbcolStock", isVisible());
        verifyThat("#asignProduct", isDisabled());
        verifyThat("#unasignProduct", isEnabled());
        verifyThat("#addProduct", isDisabled());
        verifyThat("#btnSearch", isDisabled());
        verifyThat("#txtSearch", isDisabled());
        
        clickOn("#cbSearch");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        verifyThat("#tbProducts", isVisible());
        verifyThat("#tbcolName", isVisible());
        verifyThat("#tbcolDescription", isVisible());
        verifyThat("#tbcolPrice", isVisible());
        verifyThat("#tbcolStock", isVisible());
        verifyThat("#asignProduct", isEnabled());
        verifyThat("#unasignProduct", isDisabled());
        verifyThat("#addProduct", isDisabled());
        verifyThat("#btnSearch", isEnabled());
        verifyThat("#txtSearch", isEnabled());
       // verifyThat("#txtSearch", hasText(""));
        verifyThat("#labelError", isInvisible());
                
        clickOn("#cbSearch");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        verifyThat("#tbProducts", isVisible());
        verifyThat("#tbcolName", isVisible());
        verifyThat("#tbcolDescription", isVisible());
        verifyThat("#tbcolPrice", isVisible());
        verifyThat("#tbcolStock", isVisible());
        verifyThat("#asignProduct", isEnabled());
        verifyThat("#unasignProduct", isDisabled());
        verifyThat("#addProduct", isDisabled());
        verifyThat("#btnSearch", isEnabled());
        verifyThat("#txtSearch", isEnabled());
       // verifyThat("#txtSearch", hasText(""));
        verifyThat("#labelError", isInvisible());
    }
    
    /**
     * Test Method to the Log Out menu item with close selection
     */
    @Test
    public void testB_logOut() {
        clickOn("#menuMenu");
        clickOn("#menuLogOut");
        clickOn("#cancelButton");
        verifyThat("#btnLogOut2", isVisible());
        clickOn("#menuMenu");
        clickOn("#menuLogOut");
        clickOn("#okButton");
        //verifyThat("#loginPane", isVisible());
    }
    
    /**
     * Test of initial state of login view before open PrincipalView.
     */
    @Test @Ignore
    public void testC_initIteraction() {
        clickOn("#tfUsuario");
        write("julen");
        clickOn("#pfContraseña");
        write("12345678");
        clickOn("#btnInicio");
        verifyThat("#principalPane", isVisible());
        //clickOn("#menuProductos");
        //clickOn("#idMenuProductos");
    }
    
    /**
     * Test of menu to go to event
     */
    @Test
    public void testD_initIteraction() {
        clickOn("#menuEvent");
        clickOn("#idMenuEvent");
        verifyThat("#eventPane", isVisible());
    }
    
    /**
     * Test of menu to go to expense
     */
    @Test
    public void testF_initIteraction() {
        clickOn("#menuExpense");
        clickOn("#idMenuExpense");
        verifyThat("#expensePane", isVisible());
    }
    /**
     * Test of menu to go to user
     */
    @Test
    public void testG_initIteraction() {
        clickOn("#menuUser");
        clickOn("#idMenuUser");
        verifyThat("#userPane", isVisible());
    }
    /**
     * Test of menu to go to telephone
     */
    @Test
    public void testH_initIteraction() {
        clickOn("#menuTelephon");
        clickOn("#idMenuTelephon");
        verifyThat("#telephonPane", isVisible());
    }
    
     /**
     * Test of menu to go to clientFTP
     */
    @Test
    public void testI_initIteraction() {
        clickOn("#menuFtp");
        clickOn("#idMenuFtp");
        verifyThat("#telephonPane", isVisible());
    }
    
   /**
     * Test to bottom_right Button for close session
     */
    @Test
    public void testZ_btnLogOut2() {
        clickOn("#btnLogOut2");
        clickOn("#cancelButton");
        verifyThat("#btnLogOut2", isVisible());
        clickOn("#btnLogOut2");
        clickOn("#okButton");
       // verifyThat("#loginPane", isVisible());
    }
}