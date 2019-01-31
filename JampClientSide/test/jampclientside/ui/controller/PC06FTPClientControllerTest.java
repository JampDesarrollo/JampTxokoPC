/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.UiApplicationUser;
import javafx.scene.Node;
import javafx.scene.control.TreeView;
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
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * FTPCLient Controller test Class
 *
 * @author Ander
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PC06FTPClientControllerTest extends ApplicationTest {

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
     * Go to FTP CLient view.
     */
    @Test
    public void testa_goToFTPClientView() {
        write("testLogin");
        clickOn("#pfContraseña");
        write("8ad94c7ac7");
        clickOn("#btnInicio");
        verifyThat("#userPane", isVisible());
        clickOn("#btnArchivo");
        clickOn("#btnArchivos");
        verifyThat("#ftpPane", isVisible());
    }

    /**
     * Test initStage for FTP CLient.
     */
    @Test
    public void testb_initStage() {
        verifyThat("#btnOut", isEnabled());
        verifyThat("#btnUpload", isDisabled());
        verifyThat("#btnDownload", isDisabled());
        verifyThat("#btnDeleteFile", isDisabled());
        verifyThat("#btnCreateDir", isDisabled());
        verifyThat("#btnDeleteDir", isDisabled());
        verifyThat("#lblInfo", org.testfx.matcher.control.LabeledMatchers
                .hasText("Conectado a Servidor FTP"));
        TreeView tView = lookup("#tView").query();
        assertNotEquals("TreeView has no data: Cannot test.",
                tView.getRoot().getChildren().size(), 0);
    }

    /**
     * Test selecting a directory enables/disables buttons.
     */
    @Test
    public void testc_directorySelectionButtonsEnabledDisabled() {
        TreeView tView = lookup("#tView").query();
        int rowCount = tView.getRoot().getChildren().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup("/").nth(0).query();
        assertNotNull("Row is null: treeView has not that row. ", row);
        clickOn(row);
        verifyThat("#lblInfo", isInvisible());
        verifyThat("#btnUpload", isEnabled());
        verifyThat("#btnCreateDir", isEnabled());
        verifyThat("#btnDeleteDir", isEnabled());
        press(KeyCode.CONTROL);
        clickOn(row);
        verifyThat("#btnUpload", isDisabled());
        verifyThat("#btnCreateDir", isDisabled());
        verifyThat("#btnDeleteDir", isDisabled());
    }

    /**
     * Test selecting a file enables/disables buttons.
     */
    @Test
    public void testd_fileSelectionButtonsEnabledDisabled() {
        TreeView tView = lookup("#tView").query();
        int rowCount = tView.getRoot().getChildren().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup("/").nth(0).query();
        assertNotNull("Row is null: treeView has not that row. ", row);
        doubleClickOn(row);
        Node row2 = lookup("adios.txt").nth(0).query();
        assertNotNull("Row is null: treeView has not that row. ", row2);
        clickOn(row2);
        verifyThat("#lblInfo", isInvisible());
        verifyThat("#btnDownload", isEnabled());
        verifyThat("#btnDeleteFile", isEnabled());
        press(KeyCode.CONTROL);
        clickOn(row2);
        verifyThat("#btnDownload", isDisabled());
        verifyThat("#btnDeleteFile", isDisabled());
    }

    /**
     * Test Creating a directory.
     */
    @Test
    public void teste_createDir() {
        TreeView tView = lookup("#tView").query();
        int rowCount = tView.getRoot().getChildren().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup("/").nth(0).query();
        assertNotNull("Row is null: treeView has not that row. ", row);
        clickOn(row);
        clickOn("#btnCreateDir");
        write("CarpetaTest");
        clickOn("Aceptar");
        verifyThat("#lblInfo", org.testfx.matcher.control.LabeledMatchers
                .hasText("Se ha creado una carpeta nueva correctamente"));
        assertEquals("The dir has not been added", rowCount + 1, tView.getRoot().getChildren().size());
    }

    /**
     * Test deleting a directory.
     */
    @Test
    public void testf_deleteDir() {
        TreeView tView = lookup("#tView").query();
        int rowCount = tView.getRoot().getChildren().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup("CarpetaTest").query();
        assertNotNull("Row is null: treeView has not that row. ", row);
        clickOn(row);
        clickOn("#btnDeleteDir");
        verifyThat("¿Estas seguro de que deseas borrar el directorio?",
                isVisible());
        clickOn("Aceptar");
        verifyThat("#lblInfo", org.testfx.matcher.control.LabeledMatchers
                .hasText("La carpeta ha sido eliminada correctamente"));
        assertEquals("The dir has not been deleted", rowCount - 1, tView.getRoot().getChildren().size());
    }

    /**
     * Test Uploading a file.
     */
    @Test
    @Ignore
    public void testg_uploadFile() {
        TreeView tView = lookup("#tView").query();
        int rowCount = tView.getRoot().getChildren().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup("/").nth(0).query();
        assertNotNull("Row is null: treeView has not that row. ", row);
        clickOn(row);
        clickOn("#btnUpload");
        doubleClickOn("Documentos");
        clickOn("pepe.jpg");
        clickOn("Aceptar");
        verifyThat("#lblInfo", org.testfx.matcher.control.LabeledMatchers
                .hasText("El archivo ha sido subido correctamente"));
        assertEquals("The dir has not been added", rowCount+1, tView.getRoot().getChildren().size());
    }
    /**
     * Test deleting a file.
     */
    @Test
    @Ignore
    public void testh_deleteFile() {
        TreeView tView = lookup("#tView").query();
        int rowCount = tView.getRoot().getChildren().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup("adios.txt").nth(0).query();
        assertNotNull("Row is null: treeView has not that row. ", row);
        clickOn(row);
        clickOn("#btnDeleteFile");
        verifyThat("¿Estas seguro de que deseas borrar el archivo?",
                isVisible());
        clickOn("Aceptar");
        verifyThat("#lblInfo", org.testfx.matcher.control.LabeledMatchers
                .hasText("El archivo ha sido eliminado correctamente"));
        assertEquals("The dir has not been added", rowCount-1, tView.getRoot().getChildren().size());
    }
    /**
     * Test downloading a file.
     */
    @Test
    @Ignore
    public void testi_downloadFile() {
        TreeView tView = lookup("#tView").query();
        int rowCount = tView.getRoot().getChildren().size();
        assertNotEquals("Table has no data: Cannot test.",
                rowCount, 0);
        Node row = lookup("adios.txt").nth(0).query();
        assertNotNull("Row is null: treeView has not that row. ", row);
        clickOn(row);
        clickOn("#btnDownload");
        clickOn("Seleccionar Carpeta");
        verifyThat("#lblInfo", isVisible());
    }
    /**
     * Test button back.
     */
    @Test
    public void testj_buttonBack() {
        clickOn("#btnOut");
        verifyThat("#userPane", isVisible());
    }
}
