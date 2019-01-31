/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.UiApplicationUser;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * SignUp Controller test Class
 *
 * @author Ander
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PC02SignUpControllerTest extends ApplicationTest {

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
     * Go to SignUp view.
     */
    @Test
    public void test1_goToStage() {
        clickOn("#hpLink");
        verifyThat("#signUpPane", isVisible());
    }

    /**
     * Test of initial state of signup view.
     */
    @Test
    public void test2_initialStage() {
        verifyThat("#tfLogin", isEnabled());
        verifyThat("#lblLoginW", isInvisible());

        verifyThat("#tfFullName", isEnabled());
        verifyThat("#lblFNameW", isInvisible());

        verifyThat("#tfEmail", isEnabled());
        verifyThat("#lblEmailW", isInvisible());

        verifyThat("#pfPassw", isEnabled());
        verifyThat("#tfPassw", isInvisible());
        verifyThat("#lblPasswW", isInvisible());

        verifyThat("#pfRpassw", isEnabled());
        verifyThat("#tfRpassw", isInvisible());
        verifyThat("#lblRpasswW", isInvisible());
        verifyThat("#btnEye", isEnabled());

        verifyThat("#btnBack", isEnabled());
        verifyThat("#btnSignUp", isDisabled());
        verifyThat("#imgLoading", isInvisible());

        verifyThat("#rbUser", (RadioButton b) -> b.isSelected());
        verifyThat("#rbAdmin", (RadioButton b) -> !b.isSelected());
    }

    /**
     * Test of Back button.
     */
    @Test
    public void test3_backButton() {

        clickOn("#hpLink");
        sleep(1000);
        clickOn("#btnBack");
        verifyThat("#loginPane", isVisible());
    }
}
