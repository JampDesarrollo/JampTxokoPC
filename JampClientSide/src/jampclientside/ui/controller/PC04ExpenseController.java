/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.ExpenseBean;
import jampclientside.logic.EventLogic;
import jampclientside.logic.ExpenseLogic;
import jampclientside.logic.ILogic;
import jampclientside.logic.ILogicFactory;
import jampclientside.logic.ILogicProduct;
import java.io.IOException;
import static java.lang.Math.E;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.E;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import messageuserbean.UserBean;
import jampclientside.logic.UserLogic;

/**
 * FXML Controller class
 *
 * @author Ander
 */
public class PC04ExpenseController {

    @FXML
    private Menu menu;
    @FXML
    private MenuItem btnLogOut;
    @FXML
    private Menu btnEvents;
    @FXML
    private Menu btnExpenses;
    @FXML
    private Menu btnProducts;
    @FXML
    private Menu btnUsers;
    @FXML
    private Menu btnPhones;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblLogin;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblTxoko;
    @FXML
    private Button btnSeeMonth;
    @FXML
    private Button btnSeeAll;
    @FXML
    private Button btnLogOut2;
    @FXML
    private TableView tabExpenses;
    @FXML
    private TableColumn colDate;
    @FXML
    private TableColumn colUser;
    @FXML
    private TableColumn colType;
    @FXML
    private TableColumn colDescription;
    @FXML
    private TableColumn colPrice;
    @FXML
    private MenuItem idMenuEventos;
    @FXML
    private MenuItem idMenuProductos;
    @FXML
    private MenuItem idMenuUsuarios;
    @FXML
    private MenuItem idMenuTel;
    private ObservableList<ExpenseBean> expenseData;
    private ObservableList<UserBean> userData;

    /**
     * To close app or session
     */
    private int cerrar;

    /**
     * The business logic object containing all business methods.
     */

    private ExpenseLogic ilogic;

    /**
     * UserBean object
     */
    private UserBean user;

    /**
     * Logger object used to log messages for application.
     */
    protected static final Logger LOGGER = Logger.getLogger("jampclientside.ui.controller");
    /**
     * The Stage object associated to the Scene controlled by this controller.
     * This is an utility method reference that provides quick access inside the
     * controller to the Stage object in order to make its initialization. Note
     * that this makes Application, Controller and Stage being tightly coupled.
     */
    protected Stage stage;

    /**
     * Gets the Stage object related to this controller.
     *
     * @return The Stage object initialized by this controller.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets the Stage object related to this controller.
     *
     * @param stage The Stage object to be initialized.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set logic for this view controller
     *
     * @param ILogic ilogic
     */

    public void setILogic(ExpenseLogic iLogic) {
        this.ilogic = iLogic;
    }

    /**
     * Set the user received in Login view for this view.
     *
     * @param user Userbean user
     */
    public void setUser(UserBean user) {
        this.user = user;

    }

    /**
     * Initializes the controller class.
     *
     * @param root root
     * @throws java.io.IOException InputOuput exception
     */
    public void initStage(Parent root) throws IOException {
        LOGGER.info("Initializing PrincipalExpense stage.");
        //Create a scene associated to the node graph root.
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //Associate scene to primaryStage(Window)
        stage.setScene(scene);
        stage.setResizable(true);
        //Set window properties
        stage.setTitle("Principal");
        //Set window's events handlers
        stage.setOnShowing(this::windowShow);
        btnLogOut.setOnAction(this::logOutAction);
        btnLogOut2.setOnAction(this::logOutAction);
        idMenuEventos.setOnAction(this::goEvents);
//        idMenuProductos.setOnAction(this::goProducts);
//        idMenuUsuarios.setOnAction(this::goUsers);
//        idMenuTel.setOnAction(this::goPhones);
        btnSeeAll.setOnAction(this::seeAll);
        btnSeeMonth.setOnAction(this::seeMonth);
        //las columnas van a coger el valos de los atributos
        /* colDate.setCellValueFactory(
                new PropertyValueFactory<>("date"));*/
    /*    userData = FXCollections.observableArrayList(ilogic.getAllUsers());
        tabExpenses.setItems(userData);*/
        colUser.setCellValueFactory(
                new PropertyValueFactory<>("user"));
        colType.setCellValueFactory(
                new PropertyValueFactory<>("type"));
        colDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        //Show primary window
        stage.show();

        stage.setOnCloseRequest((WindowEvent e) -> {
            cerrar = 1;
            e.consume();
            cerrarSesionAlert(cerrar);

        });
    }

    /**
     * Initializes the window when shown.
     *
     * @param event WindowEvent event
     */
    private void windowShow(WindowEvent event) {
        LOGGER.info("Beginning PrincipalExpense::windowShow");
        /*
        String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(user.getLastAccess());

        lblDate.setText("Último acceso: " + date);
        lblEmail.setText("Email: " + user.getEmail());
        lblFullName.setText("Nombre Completo: " + user.getFullname());
        lblLogin.setText("Login: " + user.getLogin());
        //TODO
        lblTxoko.setText("Txoko: ");
         */
        menu.setMnemonicParsing(true);
        menu.setText("_Menu");

        btnLogOut.setMnemonicParsing(true);
        btnLogOut.setText("_Cerrar Sesion");
        btnLogOut.setAccelerator(
                new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        btnLogOut2.setMnemonicParsing(true);
        btnLogOut2.setText("_Cerrar Sesion");
        btnSeeAll.setDisable(false);
        btnSeeMonth.setDisable(false);
        tabExpenses.setEditable(false);

    }

    /**
     * Close current view and open Login view method.
     *
     * @param event Action Event
     */
    public void logOutAction(ActionEvent event) {
        LOGGER.info("Beginning Principal::logout action");
        cerrar = 2;
        cerrarSesionAlert(cerrar);
    }

    /**
     * Method that show a confirm dialog to close session
     *
     * @param cerrar Difference for close app or close session
     */
    public void cerrarSesionAlert(int cerrar) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dialogo de confirmacion");
        alert.setContentText("Estas seguro que deseas cerrar la sesion");
        alert.setHeaderText("Cerrar Sesion");

        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("okButton");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("cancelButton");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (cerrar == 1) {
                System.exit(0);
            } else {
                stage.hide();
            }

        }
    }

    private void goEvents(ActionEvent event) {
        LOGGER.info("clickOn Eventos Menu");
        try {
            //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            EventLogic eventLogic = ILogicFactory.getEventLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC05Events.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC05EventsController controller = (PC05EventsController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(eventLogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    /*
    private void goProducts(ActionEvent event) {
          LOGGER.info("clickOn Productos Menu");
          try{
           //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            ILogicProduct productLogic = ILogicFactory.getProductLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC07Products.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC07ProductsController controller = (PC07ProductsController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(productLogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
        }catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    private void goUsers(ActionEvent event) {
          LOGGER.info("clickOn Usuarios Menu");
          try{
          //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            ILogic usersLogic = ILogicFactory.getIlogicImplementation();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC03User.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC03UserController controller = (PC03UserController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(usersLogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
        }catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    private void goPhones(ActionEvent event) {
          LOGGER.info("clickOn Telefonos Menu");
         try{
          //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            ILogicTelephone usersLogic = ILogicFactory.getTelephoneLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC08PhoneNumber.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC08PhoneNumberController controller = (PC08PhoneNumberController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(usersLogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
         }catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }
     */
    @SuppressWarnings("empty-statement")
    private void seeAll(ActionEvent event) {
        // ObservableList<Expense> expenses = ilogic.findExpensesAll(
         //   Integer.parseInt(lblTxoko.getText()));

        expenseData = FXCollections.observableArrayList(ilogic.getAllExpense());
        tabExpenses.setItems(expenseData);

        // tabExpenses.setItems(expenses);
        // tabExpenses.getColumns().addAll(colDate, colidUser, colType, colPrice, colDescription);
    }

    private void seeMonth(ActionEvent event) {
        // ObservableList<Expense> expenses = ilogic.findExpensesMonth(
        //       Integer.parseInt(lblTxoko.getText()));

        // tabExpenses.setItems(expenses);
        //tabExpenses.getColumns().addAll(colDate, colidUser, colType, colPrice, colDescription);
    }

}
