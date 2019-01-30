/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.UiApplication;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * Test class for the expense controller
 *
 * @author Paula
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PC04ExpenseControllerIT extends ApplicationTest {

    public void start(Stage stage) throws Exception {
        new UiApplication().start(stage);//llamo a la aplicacion que me abre la ventana
    }

    //venir hasta la venta de gastos
    @Test
    public void test0_clickOnExpenseMenuItem() {
        clickOn("#menuGastos");
        clickOn("#idMenuGastos");
        verifyThat("#principalPaneExpense", isVisible());

        verifyThat("#btnSeeAll", isEnabled());
        verifyThat("#btnSeeMonth", isEnabled());
        verifyThat("#btnLogOut2", isEnabled());
        verifyThat("#colDate", isVisible());
        verifyThat("#colUser", isVisible());
        verifyThat("#colType", isVisible());
        verifyThat("#colDescription", isVisible());
        verifyThat("#colPrice", isVisible());
/*
        clickOn("#btnSeeAll");
        clickOn("#btnSeeMonth");

        clickOn("#btnProducts");
        clickOn("#idMenuProductos");
        verifyThat("#principalPaneProducts", isVisible());
        clickOn("#menuGastos");
        clickOn("#idMenuGastos");
        verifyThat("#principalPaneExpense", isVisible());

        clickOn("#btnUsers");
        clickOn("#idMenuUsuarios");
        verifyThat("#principalPaneUser", isVisible());
        clickOn("#btnEvents");
        clickOn("#idMenuUsuarios");
        verifyThat("#principalPaneExpense", isVisible());

        clickOn("#btnPhones");
        clickOn("#idMenuTel");
        verifyThat("#principalPanePhones", isVisible());
        clickOn("#menuGastos");
        clickOn("#idMenuGastos");
        verifyThat("#principalPaneExpense", isVisible());

        clickOn("#menuFTP");
        clickOn("#idMenuFTP");
        verifyThat("#signUpPane", isVisible());
        clickOn("#menuGastos");
        clickOn("#idMenuGastos");
        verifyThat("#principalPaneExpense", isVisible());

        clickOn("#menu");
        clickOn("#btnLogOut");
        clickOn("Cancelar");

        clickOn("#menu");
        clickOn("#btnLogOut");
        clickOn("Aceptar");
*/
    }

    public void test1_InitStage() {
        verifyThat("#btnSeeAll", isEnabled());
        verifyThat("#btnSeeMonth", isEnabled());
        verifyThat("#btnLogOut2", isEnabled());
        verifyThat("#colDate", isVisible());
        verifyThat("#colUser", isVisible());
        verifyThat("#colType", isVisible());
        verifyThat("#colDescription", isVisible());
        verifyThat("#colPrice", isVisible());
    }
    @Test
    public void test2_clickOnBtnSeeAll() {
        clickOn("#btnSeeAll");
    }

    public void test3_clickOnBtnSeeMonth() {
        clickOn("btnSeeMonth");
    }

    public void test5_goToProductWindow() {
        clickOn("#btnProducts");
        clickOn("#idMenuProductos");
        verifyThat("#principalPaneProducts", isVisible());
        clickOn("#menuGastos");
        clickOn("#idMenuGastos");
        verifyThat("#principalPaneExpense", isVisible());
    }

    public void test6_goToUserWindow() {
        clickOn("#btnUsers");
        clickOn("#idMenuUsuarios");
        verifyThat("#principalPaneUser", isVisible());
        clickOn("#btnEvents");
        clickOn("#idMenuUsuarios");
        verifyThat("#principalPaneExpense", isVisible());

    }

    public void test7_goToPhonesWindow() {
        clickOn("#btnPhones");
        clickOn("#idMenuTel");
        verifyThat("#principalPanePhones", isVisible());
        clickOn("#menuGastos");
        clickOn("#idMenuGastos");
        verifyThat("#principalPaneExpense", isVisible());

    }

    public void test8_goToClientFTPWindow() {
        clickOn("#menuFTP");
        clickOn("#idMenuFTP");
        verifyThat("#signUpPane", isVisible());
        clickOn("#menuGastos");
        clickOn("#idMenuGastos");
        verifyThat("#principalPaneExpense", isVisible());

    }

    public void test9_LogOut() {
        clickOn("#menu");
        clickOn("#btnLogOut");
        clickOn("Cancelar");

        clickOn("#menu");
        clickOn("#btnLogOut");
        clickOn("Aceptar");
    }

}
