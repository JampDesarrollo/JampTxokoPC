/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.ExpenseBean;
import jampclientside.entity.UserBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.logic.EventLogic;
import jampclientside.logic.ExpenseLogic;
import jampclientside.logic.FTPClientLogic;
import jampclientside.logic.ILogicFactory;
import java.io.IOException;
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
import jampclientside.logic.ProductLogic;
import jampclientside.logic.TelephoneLogic;
import jampclientside.logic.UserLogic;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Opens the expenses window  showing the data of all the expenses of our txoko
 *
 * @author Paula
 */
public class PC04ExpenseController {

    /**
     * List to collect the data and display them in the table
     */
    private ObservableList<ExpenseBean> expenseData;
    private static final Logger LOGGER = Logger.getLogger("package.class");
    /**
     * menu bar
     */
    @FXML
    private Menu menu;
    /**
     * menu item to log out
     */
    @FXML
    private MenuItem btnLogOut;
    /**
     * menu events
     */
    @FXML
    private Menu btnEvents;
    /**
     * menu expenses
     */
    @FXML
    private Menu btnExpenses;
    /**
     * menu produtcs
     */
    @FXML
    private Menu btnProducts;
    /**
     * menu users
     */
    @FXML
    private Menu btnUsers;
    /**
     * menu phones
     */
    @FXML
    private Menu btnPhones;
    /**
     * label date
     */
    @FXML
    private Label lblDate;
    /**
     * label login
     */
    @FXML
    private Label lblLogin;
    /**
     * label full name
     */
    @FXML
    private Label lblFullName;
    /**
     * label email
     */
    @FXML
    private Label lblEmail;
    /**
     * label txoko
     */
    @FXML
    private Label lblTxoko;
    /**
     * button to see the events of the month
     */
    @FXML
    private Button btnSeeMonth;
    /**
     * button to see all the events of the txoko
     */
    @FXML
    private Button btnSeeAll;
    /**
     * button to log out
     */
    @FXML
    private Button btnLogOut2;
    /**
     * table view for expenses
     */
    @FXML
    private TableView tabExpenses;
    /**
     * table column
     */
    @FXML
    private TableColumn colDate;
    /**
     * table column
     */
    @FXML
    private TableColumn colUser;
    /**
     * table column
     */
    @FXML
    private TableColumn colType;
    /**
     * table column
     */
    @FXML
    private TableColumn colDescription;
    /**
     * table column
     */
    @FXML
    private TableColumn colPrice;
    /**
     * menu item to go to events
     */
    @FXML
    private MenuItem idMenuEventos;
    /**
     * menu item to go to produtcs
     */
    @FXML
    private MenuItem idMenuProductos;
    /**
     * menu item to go to user
     */
    @FXML
    private MenuItem idMenuUsuarios;
    /**
     * menu item to go to telephone
     */
    @FXML
    private MenuItem idMenuTel;
    /**
     * menu FTP
     */
    @FXML
    private Menu menuFTP;
    /**
     * menu item to go to FTP
     */
    @FXML
    private MenuItem idMenuFTP;
    @FXML
    private Button btnInforme;

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
     * The Stage object associated to the Scene controlled by this controller.
     * This is an utility method reference that provides quick access inside the
     * controller to the Stage object in order to make its initialization. Note
     * that this makes Application, Controller and Stage being tightly coupled.
     */
    private Stage stage;

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
     * @param iLogic ilogic
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
        idMenuProductos.setOnAction(this::goProducts);
        idMenuUsuarios.setOnAction(this::goUsers);
        idMenuTel.setOnAction(this::goPhones);
        idMenuFTP.setOnAction(this::FTPClientWindow);
        btnSeeAll.setOnAction(this::seeAll);
        btnSeeMonth.setOnAction(this::seeMonth);
        btnInforme.setOnAction(this::generateInforme);
        //las columnas van a coger el valos de los atributos
        colDate.setCellValueFactory(
                new PropertyValueFactory<>("date"));
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
        
        lblDate.setText("Último acceso: " + user.getLastAccess());
        lblEmail.setText("Email: " + user.getEmail());
        lblFullName.setText("Nombre Completo: " + user.getFullname());
        lblLogin.setText("Login: " + user.getLogin());
        lblTxoko.setText("Txoko: " + user.getTxoko().getName());
        
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
        btnInforme.setDisable(true);
        btnLogOut2.setDisable(false);
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

    /**
     * Method to go to the events window
     *
     * @param event event
     */
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
            controller.setUser(user);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            this.stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    /**
     * Method to go to the products window
     *
     * @param event event
     */
    private void goProducts(ActionEvent event) {
        LOGGER.info("clickOn Products Menu");
        try {
            //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            ProductLogic productLogic = ILogicFactory.getProductLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC07Products.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC07ProductsController controller = (PC07ProductsController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogicProduct(productLogic);
            controller.setUser(user);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
             this.stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    /**
     * method to go to users window
     *
     * @param event event
     */
    private void goUsers(ActionEvent event) {
        LOGGER.info("clickOn User Menu");
        try {
            //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            UserLogic userLogic = ILogicFactory.getUserLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC03User.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC03UserController controller = (PC03UserController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(userLogic);
            controller.setUser(user);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
             this.stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    /**
     * method to go to phones window
     *
     * @param event event
     */
    private void goPhones(ActionEvent event) {
        LOGGER.info("clickOn Telephone Menu");
        try {
            //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            TelephoneLogic telephoneLogic = ILogicFactory.getTelephoneLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC08PhoneNumbers.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC08PhoneNumbersController controller = (PC08PhoneNumbersController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(telephoneLogic);
            controller.setUser(user);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
             this.stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    /**
     * method to go to the ftp client window
     *
     * @param event event
     */
    private void FTPClientWindow(ActionEvent event) {
        LOGGER.info("clickOn FTP Client btn");
        try {
            //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            FTPClientLogic ftpLogic = ILogicFactory.getFTPClientLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC06FTPClient.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC06FTPClientController controller = (PC06FTPClientController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(ftpLogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    /**
     * Method to see all the expenses of our txoko
     *
     * @param event event
     */
    @SuppressWarnings("empty-statement")
    private void seeAll(ActionEvent event) {
        try {
            String idTxoko = "1";
            expenseData = FXCollections.observableArrayList(ilogic.findAllExpensesUsers(idTxoko));
            tabExpenses.setItems(expenseData);
            btnInforme.setDisable(false);
            //si no a devuelto nada por que no hay gastos que aparezca un alert
            if (expenseData.size() == 0) {
                Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setHeaderText("No hay ningun gasto en el txoko");
                dialogoAlerta.showAndWait();
            }
        } catch (BusinessLogicException ex) {
            expenseData = null;
            tabExpenses.setItems(expenseData);
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("HTTP 400 Bad Request");
            dialogoAlerta.setHeaderText("Ver los datos");
            dialogoAlerta.showAndWait();
            LOGGER.log(Level.SEVERE,
                    " Error reading ALL EXPENSE {0}",
                    ex.getMessage());
        }

    }

    /**
     * Method to see all the expenses of one month
     *
     * @param event event
     */
    private void seeMonth(ActionEvent event) {
         btnInforme.setDisable(true);
        try {
            String idTxoko = "1";
            expenseData = FXCollections.observableArrayList(ilogic.findMonthExpensesUsers(idTxoko));
            tabExpenses.setItems(expenseData);
            if (expenseData.size() == 0) {
                Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setHeaderText("No hay gastos este mes en el txoko");
                dialogoAlerta.showAndWait();

            }
        } catch (BusinessLogicException ex) {
            expenseData = null;
            tabExpenses.setItems(expenseData);
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("HTTP 400 Bad Request");
            dialogoAlerta.setHeaderText("Ver los datos");
            dialogoAlerta.showAndWait();
            LOGGER.log(Level.SEVERE,
                    " Error reading expense BY MONTH: {0}",
                    ex.getMessage());
        }

    }
    /**
     * 
     *Method to open the report window
     * @param ev event
     */
    private void generateInforme(ActionEvent event){
    
    
       try {
            JasperReport report
                    = JasperCompileManager.compileReport(getClass()
                            .getResourceAsStream("/jampclientside/ui/reports/newReportExpenses.jrxml"));
            //Data for the report: a collection of UserBean passed as a JRDataSource 
            //implementation 
            JRBeanCollectionDataSource dataItems
                    = new JRBeanCollectionDataSource((Collection<ExpenseBean>) this.tabExpenses.getItems());
            //Map of parameter to be passed to the report
            Map<String, Object> parameters = new HashMap<>();
            //Fill report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);
            //Create and show the report window. The second parameter false value makes 
            //report window not to close app.
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);
            // jasperViewer.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        } catch (JRException ex) {
            //If there is an error show message and
            //log it.
            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("Error a la hora de hacer el informe");
            dialogoAlerta.setHeaderText("Expense error");
            dialogoAlerta.showAndWait();
            LOGGER.log(Level.SEVERE,
                    " Expense error: {0}",
                    ex.getMessage());

        }
    
    
    
    }

}
