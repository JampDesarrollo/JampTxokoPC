/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.UiApplicationUser;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * UserManager Controller test Class
 *
 * @author Ander
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PC03UserControllerTest extends ApplicationTest {

    /**
     * Oversized text.
     */
    private static final String OVERSIZED_TEXT = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
            + "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";

    /**
     * Method to start the application for the controller
     *
     * @param stage Stage object.
     * @throws Exception Exception.
     */
    @Override
    public void start(Stage stage) throws Exception {
        new UiApplicationUser().start(stage);
    }

    /**
     * Got to usermanager view and initstage.
     */
    @Test
    public void testa_goToStageAndInitialStage() {
        write("testLogin");
        clickOn("#pfContraseña");
        write("4463a7e7a1");
        clickOn("#btnInicio");
        verifyThat("#userPane", isVisible());

        verifyThat("#btnDeleteUser", isDisabled());
        verifyThat("#btnEditUser", isDisabled());
        verifyThat("#btnLogOut2", isEnabled());
        verifyThat("#lblErrorUser", isInvisible());
        verifyThat("#lblTxoko", org.testfx.matcher.control.LabeledMatchers.hasText("Txoko: Pepos"));
        verifyThat("#lblFullName", org.testfx.matcher.control.LabeledMatchers.hasText("Nombre Completo: testLoginF"));
        verifyThat("#menuBar", isEnabled());
        verifyThat("#tabUsers", isVisible());
        verifyThat("#colIdUser", isVisible());
        verifyThat("#colLogin", isVisible());
        verifyThat("#colEmail", isVisible());
        verifyThat("#colNameSur", isVisible());
        verifyThat("#colStatus", isVisible());
        verifyThat("#colPriv", isVisible());
        TableView table = lookup("#tabUsers").queryTableView();
        assertNotEquals("Table has no data: Cannot test.",
                table.getItems().size(), 0);
    }

    /**
     * Test the menu bar.
     */
    @Test
    public void testb_menuBarTest() {
        clickOn("#menu");
        clickOn("#btnLogOut");
        verifyThat("¿Estas seguro que deseas cerrar la sesión?",
                isVisible());
        clickOn("#cancelButton");
        clickOn("#eventos");
        verifyThat("#btnEvents", isVisible());
        moveTo("#gastos");
        verifyThat("#btnExpenses", isVisible());
        moveTo("#productos");
        verifyThat("#btnProducts", isVisible());
        moveTo("#usuarios");
        verifyThat("#btnUsers", isVisible());
        moveTo("#telefonos");
        verifyThat("#btnPhones", isVisible());
        moveTo("#btnArchivo");
        verifyThat("#btnArchivos", isVisible());
    }

    /**
     * Test selecting an item enables/disabled buttons.
     */
    @Test
    public void testc_selectTableItemButtonsEnabledDisabled() {
        //check that table view has rows
        TableView table = lookup("#tabUsers").queryTableView();
        //get row count
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        //look for 1st row in table view and click it
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        verifyThat("#btnDeleteUser", isEnabled());
        verifyThat("#btnEditUser", isEnabled());
        press(KeyCode.CONTROL);
        clickOn(row);
        verifyThat("#btnDeleteUser", isDisabled());
        verifyThat("#btnEditUser", isDisabled());
        release(KeyCode.CONTROL);
    }

    /**
     * Test cancel deleting a user.
     */
    @Test
    public void testd_cancelDeleteUser() {
        //check that table view has rows
        TableView table = lookup("#tabUsers").queryTableView();
        //get row count
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        //look for 1st row in table view and click it
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        verifyThat("#btnDeleteUser", isEnabled());
        verifyThat("#btnEditUser", isEnabled());
        clickOn("#btnDeleteUser");
        verifyThat("¿Estas seguro que deseas borrar el usuario?",
                isVisible());
        clickOn("#cancelButton");
        assertEquals("A row has been deleted", rowCount, table.getItems().size());
    }

    /**
     * Test deleting a user.
     */
    @Test
    @Ignore
    public void teste_deleteUser() {
        //check that table view has rows
        TableView table = lookup("#tabUsers").queryTableView();
        //get row count
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        //look for 1st row in table view and click it
        Node row = lookup(".table-row-cell").nth(0).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        verifyThat("#btnDeleteUser", isEnabled());
        verifyThat("#btnEditUser", isEnabled());
        clickOn("#btnDeleteUser");
        verifyThat("¿Estas seguro que deseas borrar el usuario?",
                isVisible());
        clickOn("#okButton");
        verifyThat("#lblError", org.testfx.matcher.control.LabeledMatchers.hasText("Usuario eliminado"));
        assertEquals("The row has not been deleted", rowCount - 1, table.getItems().size());
    }

    /**
     * Test cancel updating a user.
     */
    @Test
    public void testf_cancelUpdateUser() {
        //check that table view has rows
        TableView table = lookup("#tabUsers").queryTableView();
        //get row count
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup(".table-row-cell").nth(3).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        verifyThat("#btnDeleteUser", isEnabled());
        verifyThat("#btnEditUser", isEnabled());
        clickOn("#btnEditUser");
        verifyThat("¿Estas seguro que deseas actualizar el usuario?",
                isVisible());
        clickOn("#cancelButton");
    }

    /**
     * Test updating a user.
     */
    @Test
    @Ignore
    public void testg_updateUser() {
        //check that table view has rows
        TableView table = lookup("#tabUsers").queryTableView();
        //get row count
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup(".table-row-cell").nth(3).query();
        assertNotNull("Row is null: table has not that row. ", row);
        clickOn(row);
        verifyThat("#btnDeleteUser", isEnabled());
        verifyThat("#btnEditUser", isEnabled());
        clickOn("#btnEditUser");
        verifyThat("¿Estas seguro que deseas actualizar el usuario?",
                isVisible());
        clickOn("#okButton");
        verifyThat("#lblError", org.testfx.matcher.control.LabeledMatchers.hasText("Usuario modificado"));
    }

    /**
     * Test updating a user.
     */
    @Test
    public void testh_updateUserLongName() {
        //check that table view has rows
        TableView table = lookup("#tabUsers").queryTableView();
        //get row count
        int rowCount = table.getItems().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup("testLoginF").query();
        assertNotNull("Row is null: table has not that row. ", row);
        doubleClickOn(row);
        write(OVERSIZED_TEXT);
        press(KeyCode.ENTER);
        verifyThat("#btnDeleteUser", isEnabled());
        verifyThat("#btnEditUser", isEnabled());
        clickOn("#btnEditUser");
        verifyThat("¿Estas seguro que deseas actualizar el usuario?",
                isVisible());
        clickOn("#okButton");
        verifyThat("#lblErrorUser", org.testfx.matcher.control.LabeledMatchers.hasText("El nombre del Usuario es demasiado largo"));
    }

    /**
     * Test creating a new report works.
     */
    @Test
    @Ignore
    public void testi_btnReportWorks() {
        verifyThat("#btnPrint", isEnabled());
        clickOn("#btnPrint");
        //verifyThat(lookup("JasperViewer"), isVisible());
        lookup("JasperViewer");
    }

    /**
     * Test log out button.
     */
    @Test
    public void testj_btnLogOut() {
        clickOn("#btnLogOut2");
        clickOn("#cancelButton");
        verifyThat("#userPane", isVisible());
        clickOn("#btnLogOut2");
        clickOn("#okButton");
        verifyThat("#loginPane", isVisible());
    }
}
