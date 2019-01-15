/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.ProductBean;
import jampclientside.exceptions.ReadException;
import jampclientside.logic.EventLogic;
import jampclientside.logic.ExpenseLogic;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
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
import jampclientside.logic.ProductLogic;
import jampclientside.logic.TelephoneLogic;
import jampclientside.logic.UserLogic;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML PC07ProductsController class
 *
 * @author Julen
 */
public class PC07ProductsController {

    @FXML
    private Menu menu;
    @FXML
    private MenuItem idMenuGastos;
    @FXML
    private MenuItem idMenuEventos;
    @FXML
    private MenuItem idMenuUsuarios;
    @FXML
    private MenuItem idMenuTel;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuMenu;
    @FXML
    private MenuItem menuLogOut;
    @FXML
    private Menu menuEvent;
    @FXML
    private Menu menuGastos;
    @FXML
    private Menu menuProductos;
    @FXML
    private Menu menuUsuarios;
    @FXML
    private Menu menuTelefonos;

    @FXML
    private VBox principalPane;

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
    private ComboBox<String> cbSearch;
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
    private Label labelError;
    
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
    private Button btnLogOut;

    /**
     *
     */
    @FXML
    private Button btnLogOut2;

    /**
     *
     */
    @FXML
    private TableView<ProductBean> tbProducts;
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
    private ObservableList<ProductBean> productData;

    /**
     * To close app or session
     */
    private int cerrar;

    /**
     * The business logic object containing all business methods.
     */
    private ProductLogic iLogicProduct;
    private UserLogic iLogicUser;
    private TelephoneLogic iLogicTelephone;
    private EventLogic iLogicEvent;
    private ExpenseLogic iLogicExpense;

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
    public void setILogicProduct(ProductLogic iLogicProduct) {
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
        LOGGER.info("Initializing Product Window.");
        //Create a scene associated to the node graph root.
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //Associate scene to primaryStage(Window)
        stage.setScene(scene);
        stage.setResizable(true);
        //Set window properties
        stage.setTitle("Productos");
        //Set window's events handlers
        stage.setOnShowing(this::windowShow);
        //menu item de cerrar sesion
        menuLogOut.setOnAction(this::logOutAction);
        //boton de cerrar sesion
        btnLogOut2.setOnAction(this::logOutAction);
        //ir a la ventana de eventos
        idMenuEventos.setOnAction(this::eventWindow);
        //gastos
        idMenuGastos.setOnAction(this::expenseWindow);
        //ir a la ventana de telefonos
        idMenuTel.setOnAction(this::telephoneWindow);
        //ventana de los usuarios
        // idMenuUsuarios.setOnAction(this::usersWindow);
        
        //boton añadir producto
        addProduct.setOnAction(this::handleCrearAction);
        //boton borrar producto
       // delProduct.setOnAction(this::handleDeleteProduct);
        //dependiendo la opcion que pulse del combo box
        cbSearch.setOnAction(this::comboBoxOption);

        //boton de busqueda
//        btnSearch.setOnAction(this::searchButton);


        //las columnas van a coger el valos de los atributos
        tbcolName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        tbcolDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        tbcolPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        tbcolStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));
        //Create an observable list for users table.

        //productData = FXCollections.observableArrayList(iLogicProduct.findAllProducts());

        //Set table model.
//        tbProducts.setItems(productData);
       
        //Show primary window
        stage.show();

        stage.setOnCloseRequest((WindowEvent e) -> {
            int cerrar = 1;
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
        LOGGER.info("Beginning Product Window::windowShow");
/*
        String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(user.getLastAccess());

        lblDate.setText("Último acceso: " + date);
        lblEmail.setText("Email: " + user.getEmail());
        lblFullName.setText("Nombre Completo: " + user.getFullname());
        lblLogin.setText("Login: " + user.getLogin());
*/
        cbSearch.getItems().removeAll(cbSearch.getItems());
        cbSearch.getItems().addAll("Todos los productos de mi txoko", "Id del producto", "Nombre del producto");
        labelError.setVisible(false);
        cbSearch.requestFocus();
        txtSearch.setDisable(true);
        
        tbProducts.setEditable(true);
        btnSearch.setDisable(true);
        addProduct.setDisable(true);
        delProduct.setDisable(true);
        
        menu.setMnemonicParsing(true);
        menu.setText("_Menu");

        menuLogOut.setMnemonicParsing(true);
        menuLogOut.setText("_Cerrar Sesion");
        menuLogOut.setAccelerator(
                new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        btnLogOut2.setMnemonicParsing(true);
        btnLogOut2.setText("_Cerrar Sesion");
    }

    /**
     * A focus change event event handler. This is an example that only logs a
     * message.
     *
     * @param observable the observable focus property.
     * @param oldValue the old boolean value for the property.
     * @param newValue the new boolean value for the property.
     */
    private void focusChanged(ObservableValue observable,
            Boolean oldValue,
            Boolean newValue) {
        if (newValue) {
            LOGGER.info("onFocus");
        } else if (oldValue) {
            LOGGER.info("onBlur");
        }
    }

    /**
     * Action event handler for create button. It validates new user data, send
     * it to the business logic tier and updates user table view with new user
     * data.
     *
     * @param event The ActionEvent object for the event.
     */
    @FXML
    private void handleCrearAction(ActionEvent event) {
        try {
            Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("CONFIRMACION");
            dialogoAlerta.setContentText("¿Estas seguro que deseas añadir un producto?");
            dialogoAlerta.setHeaderText("Añadir un producto");
            Optional<ButtonType> result = dialogoAlerta.showAndWait();
            Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("buttonAdd");
            Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancelButton.setId("buttonCancel");
           
            if (result.get() == ButtonType.OK) {

                //añadir una fila a la tabla
                //el txoko se puede coger del label o de la base de datos
                //añadir a la base de datos y eliminar la fila 
                ProductBean product = new ProductBean();
                //añadir fila en blanco
                tbProducts.getItems().add(null);
                tbcolName.setCellValueFactory(
                        new PropertyValueFactory<>("name"));
                tbcolName.setCellFactory(TextFieldTableCell.forTableColumn());
           
                tbcolDescription.setCellValueFactory(
                        new PropertyValueFactory<>("description"));
                tbcolDescription.setCellFactory(TextFieldTableCell.forTableColumn());

                tbcolPrice.setCellValueFactory(
                        new PropertyValueFactory<>("price"));
                tbcolPrice.setCellFactory(TextFieldTableCell.forTableColumn());
                
                tbcolStock.setCellValueFactory(
                        new PropertyValueFactory<>("stock"));
                tbcolStock.setCellFactory(TextFieldTableCell.forTableColumn());
                
                Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
                dialog.setTitle("CONFIRMACION");
                dialog.setContentText("El nuevo evento va a ser añadido");
                dialog.setHeaderText("Añadir un evento");
                Optional<ButtonType> resultado = dialog.showAndWait();
                Button aceptar = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
                aceptar.setId("aceptar");
            }
        } catch (Exception e) {
            //If there is an error in the business logic tier show message and
            //log it.
            LOGGER.log(Level.SEVERE,
                    "PC07ProductsController: Error updating user: {0}",
                    e.getMessage());
        }
    }

    /**
     * Action event handler for modify button. It validates user data, send it
     * to the business logic tier and updates user table view with new user
     * data.
     *
     * @param event The ActionEvent object for the event.
     */
    @FXML
    private void handleModificarAction(ActionEvent event) {
        try {
            //Get selected user data from table view.
            ProductBean selectedProduct = ((ProductBean) tbProducts.getSelectionModel()
                    .getSelectedItem());
            //check if loin vaalue for selectedrowin table
            //is equal to loginfield content
            if (!selectedProduct.getIdProduct().equals(txtSearch.getText())) {
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
        } catch (Exception e) {
            //If there is an error in the business logic tier show message and
            //log it.
            LOGGER.log(Level.SEVERE,
                    "PC07ProductsController: Error updating user: {0}",
                    e.getMessage());
        }
    }

    /**
     * Action event handler for delete button. It asks user for confirmation on
     * delete, sends delete message to the business logic tier and updates user
     * table view.
     *
     * @param event The ActionEvent object for the event.
     */
    @FXML
    private void handleDeleteProduct() {
        boolean isSelected = isSelected();
        try {
            if (isSelected) {
                Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
                dialogoAlerta.setTitle("CONFIRMACION");
                dialogoAlerta.setContentText("¿Estas seguro que deseas eliminar un producto?");
                dialogoAlerta.setHeaderText("Eliminar un producto");
                Optional<ButtonType> resultado = dialogoAlerta.showAndWait();
                Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
                okButton.setId("buttonDelete");
                Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
                cancelButton.setId("buttonNotDelete");
                if (resultado.get() == ButtonType.OK) {
                    //si pulsa en en aceptar que elimine
                    //tengo que saber el txoko de la persona
                    //el txoko se puede coger del label o de la base de datos
                    // tengo que saber el id del evento
                    //eliminar de la base de datos y eliminar la fila
                    tbProducts.getItems().remove(tbProducts.getSelectionModel().getSelectedItem());
                    tbProducts.refresh();
                }
            } else {

                Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
                dialogoAlerta.setTitle("ERROR");
                dialogoAlerta.setContentText("Tienes que seleccionar una fila!!");
                dialogoAlerta.setHeaderText("Eliminar un producto");
                dialogoAlerta.showAndWait();
            }
        } catch (Exception e) {
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
            cbSearch.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    //ir a la ventana de los telefonos
    public void telephoneWindow(ActionEvent ev) {
        LOGGER.info("clickOn Telephone Menu");
        try {
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC08PhoneNumbers.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC08PhoneNumbersController controller = (PC08PhoneNumbersController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogicTelephone);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearch.requestFocus();
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
            cbSearch.requestFocus();
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
            cbSearch.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    public void comboBoxOption(ActionEvent ev) {
        LOGGER.info("clickOn combo box");
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los eventos de mi txoko")) {
            //el boton de busqueda estara habilitado
            btnSearch.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            txtSearch.setText("");
            //deshabilitar el textfield
            txtSearch.setDisable(true);
            btnSearch.requestFocus();
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            txtSearch.setVisible(false);
            txtSearch.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del evento")) {
            //el boton de busqueda estara habilitado
            btnSearch.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            txtSearch.setText("");
            //habilitar el textfield
            txtSearch.setDisable(false);
            //que se ponga el foco en el text field
            txtSearch.requestFocus();
            //tooltipID.setText("Escribe el ID del evento");
            //txtSearch.setTooltip(tooltipID);
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            labelError.setVisible(false);
            txtSearch.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Nombre del evento")) {
            //el boton de busqueda estara habilitado
            btnSearch.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            txtSearch.setText("");
            //habilitar el textfield
            txtSearch.setDisable(false);
            //que se ponga el foco en el text field
            txtSearch.requestFocus();
            //tooltipName.setText("Escribe el nombre del evento");
            //txtSearch.setTooltip(tooltipName);
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            labelError.setVisible(false);
            txtSearch.setStyle("-fx-border-color: -fx-box-border;");
        }
    }
    
     //buscar evento
    public void searchButton(ActionEvent ev) throws ReadException {
        //se habilitan los botones de añadir evento, eliminar evento y busqueda de la imagen
        addProduct.setDisable(false);
        delProduct.setDisable(false);
        //btnImgEvent.setDisable(false);
        LOGGER.info("clickOn search button");
        //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
        labelError.setVisible(false);
        txtSearch.setStyle("-fx-border-color: -fx-box-border;");
        //si ha seleccionado "todos"
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los eventos de mi txoko")) {
            productData = FXCollections.observableArrayList(iLogicProduct.findAllProducts());
            tbProducts.setItems(productData);
            //vamos a buscar los eventos a la base de datos
            /* List <EventBean> listaEventos = events();
            if(listaEventos!=null){
            //aparecen todos los eventos
            
            //un alert
            Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
            dialogoAlerta.setTitle("INFORMACION");
            dialogoAlerta.setHeaderText("Si deseas ver la imagen del evento, pulsa en el botón Cliente FTP");
            dialogoAlerta.showAndWait();
        }*/
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del evento")) {
            //miramos si el textfield esta vacio o no
            boolean tfIDEmpty = textEmptyOrNot();
            //si no esta vacio
            if (tfIDEmpty) {
                /*
                //vamos a buscar a la base de datos
                EventBean evento = eventIDExist();
                //if(evento!=null){
                //carga los datos de ese evento en la tabla
                
                //un alert
                Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setHeaderText("Si deseas ver la imagen del evento, pulsa en el botón Cliente FTP");
                dialogoAlerta.showAndWait();
                */}
            //si esta vecio
            else {
                txtSearch.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el id de un evento");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }
        }//si busca por nombre
        else if (cbSearch.getSelectionModel().getSelectedItem().equals("Nombre del evento")) {
            //miramos si el text field esta vacio
            boolean tfNameEmpty = textEmptyOrNot();
            //si no esta vacio
            if (tfNameEmpty) {
                /*
                //miramos el nombre del evento en la base de datos
                //EventBean event = eventNameExist();
                //si existe
                //if(event!=null){
                //carga los datos de ese evento en la tabla
                
                //un alert
                Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setHeaderText("Si deseas ver la imagen del evento, pulsa en el botón Cliente FTP");
                dialogoAlerta.showAndWait();
                }*/
            }//si esta vacio
            else {
                txtSearch.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el nombre");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }

        }

    }
    
    private boolean textEmptyOrNot() {
        boolean empty = false;
        //si no esta vacio
        if (!this.txtSearch.getText().trim().equals("")) {
            empty = true;
        }
        return empty;
    }
    
    //para mirar si ha seleccionado la fila de la tabla
    private boolean isSelected() {
        boolean isSelected = false;
        //si es diferente a vacio
        if (!tbProducts.getSelectionModel().getSelectedItems().isEmpty()) {
            //hay algo seleccionado
            isSelected = true;
        }
        return isSelected;
    }

}
