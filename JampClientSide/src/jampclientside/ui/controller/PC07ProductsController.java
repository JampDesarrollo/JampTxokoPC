/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.Product;
import jampclientside.exceptions.ReadException;
import jampclientside.logic.ILogicProduct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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

/**
 * FXML PC07ProductsController class
 *
 * @author Julen
 */
public class PC07ProductsController{

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
    private ComboBox<?> cbSearch;
    /**
     * 
     */
    @FXML
    private TextField txtSearch;
    /**
     * 
     */
    @FXML
    private ImageView btnSearch;
    /**
     * 
     */
    @FXML
    private Button addProduct;
    /**
     * 
     */
    @FXML
    private Button delProduct;
    /**
     * 
     */
    @FXML
    private Button btnLogOut2;
    
    /**
     * 
     */
    @FXML
    private TableView tbProducts;
    /**
    * User's login data table column.
    */
    @FXML
    private TableColumn tbcolName;
    /**
    * User's name data table column.
    */
    @FXML
    private TableColumn tbcolDescription;
    /**
    * User's profile data table column.
    */
    @FXML
    private TableColumn tbcolPrice;
    /**
    * User's department data table column.
    */
    @FXML
    private TableColumn tbcolStock;
    /**
     * User's table data model.
     */
    private ObservableList<Product> productData;

    /**
     * To close app or session
     */
    private int cerrar;

    /**
     * The business logic object containing all business methods.
     */
    private ILogicProduct iLogicProduct;

    /**
     * UserBean object
     */
    private UserBean user;

    /**
     * Logger object used to log messages for application.
     */
    protected static final Logger LOGGER = Logger.getLogger("jamp.pc.ui.controller");
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
     * @param ILogicProduct iLogicProduct
     */
    public void setILogicProduct(ILogicProduct iLogicProduct) {
        this.iLogicProduct = iLogicProduct;
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
    public void initStage(Parent root) throws IOException, ReadException {
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
       
        //tbProducts.getSelectionModel().selectedItemProperty()
        //            .addListener(() -> this.handleProductsTableSelectionChanged());

        //Set department combo data model.
        //ObservableList<DepartmentBean> departments=
        //        FXCollections.observableArrayList(usersManager.getAllDepartments());
        //cbDepartamentos.setItems(departments);
        //Add focus event handler.
        txtSearch.focusedProperty().addListener(this::focusChanged);
        //Set factories for cell values in users table columns.
        tbcolName.setCellValueFactory(
                new PropertyValueFactory<>("productName"));
        tbcolDescription.setCellValueFactory(
                new PropertyValueFactory<>("productDescription"));
        tbcolPrice.setCellValueFactory(
                new PropertyValueFactory<>("productPrice"));
        tbcolStock.setCellValueFactory(
                new PropertyValueFactory<>("productStock"));
        //Create an observable list for users table.

        productData=FXCollections.observableArrayList(iLogicProduct.findAllProducts());

        //Set table model.
        tbProducts.setItems(productData);
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
     * A focus change event event handler. This is an example that only logs a message.
     * @param observable the observable focus property.
     * @param oldValue the old boolean value for the property.
     * @param newValue the new boolean value for the property.
     */
    private void focusChanged(ObservableValue observable,
             Boolean oldValue,
             Boolean newValue){
        if(newValue)
            LOGGER.info("onFocus");
        else if(oldValue)
            LOGGER.info("onBlur");
    }  

    /**
     * Action event handler for create button. It validates new user data, send it
     * to the business logic tier and updates user table view with new user data.
     * @param event The ActionEvent object for the event.
     */
    @FXML
    private void handleCrearAction(ActionEvent event){
        try{
            //Check if the is already a user with the login value defined in 
            //the window
            iLogicProduct.isProductExist(12);
           
            Product producto=new Product();         
            //Send user data to business logic tier
            this.iLogicProduct.createProduct(producto);
            //Add to user data to TableView model
            tbProducts.getItems().add(user);
            //Clean fields
            txtSearch.setText("");
            cbSearch.getSelectionModel().clearSelection();
        }catch(Exception e){
            //If there is an error in the business logic tier show message and
            //log it.
            LOGGER.log(Level.SEVERE,
                        "PC07ProductsController: Error updating user: {0}",
                        e.getMessage());           
        }
    }
    
    /** 
     * Action event handler for modify button. It validates user data, send it
     * to the business logic tier and updates user table view with new user data.
     * @param event The ActionEvent object for the event.
     */
    @FXML
    private void handleModificarAction(ActionEvent event){
        try{
            //Get selected user data from table view.
            Product selectedProduct=((Product)tbProducts.getSelectionModel()
                                                    .getSelectedItem());
            //check if loin vaalue for selectedrowin table
            //is equal to loginfield content
            if(!selectedProduct.getIdProduct().equals(txtSearch.getText())){
                //If not, validate login existence.
                    iLogicProduct.isProductExist(12);
                    selectedProduct.setName(txtSearch.getText().trim());
            } 
            //If login value does not exist: 
            //send data to modify user data in business tier
            this.iLogicProduct.updateProduct(selectedProduct);
            //Clean entry text fields
            txtSearch.setText("");
            cbSearch.getSelectionModel().clearSelection();
            //Deseleccionamos la fila seleccionada en la tabla
            tbProducts.getSelectionModel().clearSelection();
            //Refrescamos la tabla para que muestre los nuevos datos
            tbProducts.refresh();
        }catch(Exception e){
            //If there is an error in the business logic tier show message and
            //log it.
            LOGGER.log(Level.SEVERE,
                        "PC07ProductsController: Error updating user: {0}",
                        e.getMessage());           
        }
    }
    
    /**
     * Action event handler for delete button. It asks user for confirmation on delete,
     * sends delete message to the business logic tier and updates user table view.
     * @param event The ActionEvent object for the event.
     */   
    @FXML
    private void handleDeleteProduct() {
        Alert alert=null;
        try{

            Product selectedProduct = ((Product)tbProducts.getSelectionModel()
                                                            .getSelectedItem());
            alert=new Alert(Alert.AlertType.CONFIRMATION,
                                    "¿Borrar la fila seleccionada?\n"
                                    + "Esta operación no se puede deshacer.",
                                    ButtonType.OK,ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait(); 
            //If Botton OK
            if(result.isPresent() && result.get() == ButtonType.OK){
               //delete user from server side
                this.iLogicProduct.deleteProduct(selectedProduct);
                //removes selected item from table
                tbProducts.getItems().remove(selectedProduct);
                tbProducts.refresh();
                //clears editing fields
                txtSearch.setText("");
                //Clear selection and refresh tble view
                tbProducts.getSelectionModel().clearSelection();
                tbProducts.refresh();
            }
        }catch(Exception e){
            //If there is an error in the business logic tier show message and
            //log it.
            LOGGER.log(Level.SEVERE,
                        "PC07ProductsController: Error deleting user: {0}",
                        e.getMessage());           
        }
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

}
