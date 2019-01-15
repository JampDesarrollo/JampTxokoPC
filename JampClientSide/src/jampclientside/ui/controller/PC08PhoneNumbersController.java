/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.Product;
import jampclientside.entity.Telephone;
import jampclientside.exceptions.ReadException;
import jampclientside.logic.EventLogic;
import jampclientside.logic.ExpenseLogic;
import jampclientside.logic.ProductLogic;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import messageuserbean.UserBean;
import jampclientside.logic.TelephoneLogic;
import jampclientside.logic.UserLogic;
import java.util.logging.Level;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author Julen
 */
public class PC08PhoneNumbersController{


    /**
     * Log out menu item
     */
    @FXML
    private MenuItem btnLogOut;
    /**
     * Menu 
     */
    @FXML
    private Menu menu;
    /**
     * 
     */
    @FXML
    private VBox principalPane;
    /**
     * 
     */
    @FXML
    private MenuBar menuBar;
    /**
     * 
     */
    @FXML
    private Label lblDate;
    /**
     * 
     */
    @FXML
    private Label lblLogin;
    /**
     * 
     */
    @FXML
    private Label lblFullName;
    /**
     * 
     */
    @FXML
    private Label lblEmail;
    /**
     * 
     */
    @FXML
    private ComboBox<?> cbSearchTel;
    /**
     * 
     */
    @FXML
    private TextField txtSearchTel;
    /**
     * 
     */
    @FXML
    private ImageView btnSearchTel;
    /**
     * 
     */
    @FXML
    private Label requiredTel;
    /**
     * 
     */
    @FXML
    private Button addTelephone;
    /**
     * 
     */
    @FXML
    private Button delTelephone;
    /**
     * 
     */
    @FXML
    private Button btnLogOut2;
    /**
     * 
     */
    @FXML
    private TableView tbTelephone;
    /**
     * 
     */
    @FXML
    private TableColumn tbcolName;
    /**
     * 
     */
    @FXML
    private TableColumn tbcolDescription;
    /**
     * 
     */
    @FXML
    private TableColumn tbcolNumber;
    /**
     * User's table data model.
     */
    private ObservableList<Product> telephoneData;
    
    /**
     * To close app or session
     */
    private int cerrar;

    /**
     * The business logic object containing all business methods.
     */
    private TelephoneLogic iLogicTelephone;
    private UserLogic iLogicUser;
    private ProductLogic iLogicProduct;
    private EventLogic iLogicEvent;
    private ExpenseLogic iLogicExpense;

    /**
     * Telephone object
     */
    private Telephone telephone;

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
     * 
     * @return 
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * 
     * @param stage 
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * 
     * @param iLogicTelephone 
     */
    public void setILogic(TelephoneLogic iLogicTelephone) {
        this.iLogicTelephone = iLogicTelephone;
    }

    /**
     * 
     * @param telephone 
     */
    public void setUser(Telephone telephone) {
        this.telephone = telephone;

    }

    /**
     * Initializes the controller class.
     *
     * @param root root
     * @throws java.io.IOException InputOuput exception
     */
    public void initStage(Parent root) throws IOException {
        LOGGER.info("Initializing Principal stage.");
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
        //TABLE
        //tbTelephone.getSelectionModel().selectedItemProperty()
        //            .addListener(() -> this.handleTelephoneTableSelectionChanged());

        //Set department combo data model.
        //ObservableList<DepartmentBean> departments=
        //        FXCollections.observableArrayList(usersManager.getAllDepartments());
        //cbDepartamentos.setItems(departments);
        //Add focus event handler.
        //tfLogin.focusedProperty().addListener(this::focusChanged);
        //Set factories for cell values in users table columns.
        tbcolDescription.setCellValueFactory(
                new PropertyValueFactory<>("productDescription"));
        tbcolName.setCellValueFactory(
                new PropertyValueFactory<>("telephoneName"));
        tbcolNumber.setCellValueFactory(
                new PropertyValueFactory<>("telephoneNumber"));
        //Create an obsrvable list for users table.
        
        //telephoneData = FXCollections.observableArrayList(iLogicTelephone.findAllTelephone());
        
        //Set table model.
        tbTelephone.setItems(telephoneData);
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
        LOGGER.info("Beginning Principal::windowShow");
        
        String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(user.getLastAccess());
        
        lblDate.setText("Último acceso: " + date);
        lblEmail.setText("Email: " + user.getEmail());
        lblFullName.setText("Nombre Completo: " + user.getFullname());
        lblLogin.setText("Login: " + user.getLogin());
        
        menu.setMnemonicParsing(true);
        menu.setText("_Menu");
        
        btnLogOut.setMnemonicParsing(true);
        btnLogOut.setText("_Cerrar Sesion");
        btnLogOut.setAccelerator(
                new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        btnLogOut2.setMnemonicParsing(true);
        btnLogOut2.setText("_Cerrar Sesion");
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
     * @param cerrar Difference for close app or close session
     */
    public void cerrarSesionAlert(int cerrar){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dialogo de confirmacion");
        alert.setContentText("Estas seguro que deseas cerrar la sesion");
        alert.setHeaderText("Cerrar Sesion");
        
        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("okButton");
        
        Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("cancelButton");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if(cerrar == 1){
                System.exit(0);
            }else{
                stage.hide();
            }
  
        }
    }
    
     //metodo para ir a la ventana de productos

    public void eventWindow(ActionEvent ev) {
        LOGGER.info("clickOn Products Menu");
        try {
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC05EventsController.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC05EventsController controller = (PC05EventsController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogicEvent);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearchTel.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    //ir a la ventana de los telefonos
    public void productWindow(ActionEvent ev) throws ReadException {
        LOGGER.info("clickOn Telephone Menu");
        try {
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC07Products.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC07ProductsController controller = (PC07ProductsController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogicProduct(iLogicProduct);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearchTel.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    //ventana de los gastos
    public void expenseWindow(ActionEvent ev) {
        LOGGER.info("clickOn Gastos Menu");
        try {
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC04Expense.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC04ExpenseController controller = (PC04ExpenseController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogicExpense);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearchTel.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    //ventana de los usuarios
    public void usersWindow(ActionEvent ev) {
        LOGGER.info("clickOn User Menu");
        try {
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC03User.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC03UserController controller = (PC03UserController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogicUser);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearchTel.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

}
