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
import javafx.scene.Node;
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

    @Test @Ignore
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
    @Test @Ignore
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
    public void testC_secondLogin() {
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
    @Test @Ignore
    public void testD_goToEventPane() {
        clickOn("#menuEvent");
        clickOn("#idMenuEvent");
        verifyThat("#eventPane", isVisible());
        clickOn("#menuProduct");
        clickOn("#idMenuProduct");
        verifyThat("#productPane", isVisible());
    }
    
    /**
     * Test of menu to go to expense
     */
    @Test @Ignore
    public void testF_goToExpensePane() {
        clickOn("#menuExpense");
        clickOn("#idMenuExpense");
        verifyThat("#expensePane", isVisible());
        clickOn("#menuProduct");
        clickOn("#idMenuProduct");
        verifyThat("#productPane", isVisible());
    }
    /**
     * Test of menu to go to user
     */
    @Test @Ignore
    public void testG_goToUserPane() {
        clickOn("#menuUser");
        clickOn("#idMenuUser");
        verifyThat("#userPane", isVisible());
        clickOn("#menuProduct");
        clickOn("#idMenuProduct");
        verifyThat("#productPane", isVisible());
    }
    /**
     * Test of menu to go to telephone
     */
    @Test
    public void testH_goToTelephonePane() {
        clickOn("#menuTelephon");
        clickOn("#idMenuTelephon");
        verifyThat("#telephonPane", isVisible());
        clickOn("#menuProduct");
        clickOn("#idMenuProduct");
        verifyThat("#productPane", isVisible());
    }
    
     /**
     * Test of menu to go to clientFTP
     */
    @Test @Ignore
    public void testI_goToClientFTPPane() {
        clickOn("#menuFtp");
        clickOn("#idMenuFtp");
        verifyThat("#telephonPane", isVisible());
        clickOn("#menuProduct");
        clickOn("#idMenuProduct");
        verifyThat("#productPane", isVisible());
    }

    /**
     * Test of add Button
     */
    @Test
    public void testJ_addProduct() {
        clickOn("#addProduct");
        clickOn("Cancelar");
        /*clickOn("#addProduct");
        clickOn("Aceptar");
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        clickOn("#addProduct");
        clickOn("Cancelar");
        clickOn("#addProduct");
        clickOn("Aceptar");*/
    }   
    
    /**
     * Test of asign Button
     */
    @Test
    public void testK_asignProduct() {
        clickOn("#cbSearch");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#btnSearch");
        verifyThat("#labelError ", hasText("Tienes que escribir el id de un producto"));
        clickOn("#txtSearch");
        write("4");
        clickOn("#btnSearch");
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        clickOn("#asignProduct");
        clickOn("Aceptar");
        clickOn("Aceptar");
        clickOn("#asignProduct");
        clickOn("Cancelar");
        clickOn("Aceptar");
    } 
    
    /**
     * Test of unasign Button
     */
    @Test
    public void testL_unasignProduct() {
        clickOn("#cbSearch");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        clickOn("#unasignProduct");
        clickOn("Cancelar");
        clickOn("Aceptar");
        clickOn("#unasignProduct");
        clickOn("Aceptar");
        clickOn("Aceptar");
    } 
    
    /**
     * Test of deleteButton
     */
    @Test @Ignore
    public void testN_deleteProduct() {
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        clickOn("#delProduct");
        clickOn("Cancelar");
        clickOn("Aceptar");
        clickOn("#delProduct");
        //clickOn("Aceptar");
    }
   /**
     * Test to bottom_right Button for close session
     */
    @Test @Ignore
    public void testZ_btnLogOut2() {
        clickOn("#btnLogOut2");
        clickOn("#cancelButton");
        verifyThat("#btnLogOut2", isVisible());
        clickOn("#btnLogOut2");
        clickOn("#okButton");
       // verifyThat("#loginPane", isVisible());
    }
}