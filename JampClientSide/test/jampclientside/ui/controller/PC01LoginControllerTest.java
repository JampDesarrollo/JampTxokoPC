/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.UiApplicationUser;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;

/**
 * LogIn Controller test Class
 *
 * @author Ander
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PC01LoginControllerTest extends ApplicationTest {

    /**
     * Method to start the application for the controller
     *
     * @param stage Stage object.
     * @throws Exception Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        new UiApplicationUser().start(stage);
    }

    /**
     * Test of initial state of login view before open PrincipalView.
     */
    @Test
    public void test1_initialStage() {
        verifyThat("#btnInicio", isEnabled());
        verifyThat("#btnOjo", isEnabled());
        verifyThat("#hpLink", isEnabled());
        verifyThat("#hpForgot", isEnabled());
        verifyThat("#tfUsuario", org.testfx.matcher.control.TextInputControlMatchers.hasText("Nombre de usuario"));
        verifyThat("#pfContraseña", org.testfx.matcher.control.TextInputControlMatchers.hasText(""));
        verifyThat("#tfContraseña", isInvisible());
        verifyThat("#imLoading", isInvisible());
        verifyThat("#lblError", isInvisible());
    }

    /**
     * Test sending an email to wrong user.
     */
    @Test
    public void test2_SendsAnEmailWrong() {
        write("testForgotmal");
        clickOn("#hpForgot");
        sleep(1000);
        verifyThat("#lblError", org.testfx.matcher.control.LabeledMatchers.hasText("No existe este login"));
    }

    /**
     * Test sending an email to wrong user.
     */
    @Test
    public void test3_SendsAnEmail() {
        write("testForgot");
        clickOn("#hpForgot");
        sleep(1000);
        verifyThat("#lblError", org.testfx.matcher.control.LabeledMatchers.hasText("Se ha restablecido su contraseña, "
                + "compruebe su email"));
    }

}
