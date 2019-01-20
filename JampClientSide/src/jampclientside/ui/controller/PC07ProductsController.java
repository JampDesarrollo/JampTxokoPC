/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.ProductBean;
import jampclientside.exceptions.ProductExist;
import jampclientside.exceptions.ReadException;
import jampclientside.exceptions.UpdateException;
import jampclientside.logic.EventLogic;
import jampclientside.logic.ExpenseLogic;
import jampclientside.logic.ILogicFactory;
import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML PC07ProductsController class
 *
 * @author Julen
 */
public class PC07ProductsController {

    private static final Logger LOGGER = Logger.getLogger("package.class");

    private final Tooltip tooltip = new Tooltip();
    private final Tooltip tooltipID = new Tooltip();
    private final Tooltip tooltipName = new Tooltip();
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
    private MenuItem idMenuFTP;
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
    @FXML
    private Label lblDate;
    @FXML
    private Label lblLogin;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lbllTxoko;
    @FXML
    private ComboBox<String> cbSearch;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Label labelError;
    @FXML
    private Button addProduct;
    @FXML
    private Button delProduct;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnLogOut2;
    @FXML
    private TableView<ProductBean> tbProducts;
    @FXML
    private TableColumn tbcolName;
    @FXML
    private TableColumn tbcolDescription;
    @FXML
    private TableColumn tbcolPrice;
    @FXML
    private TableColumn tbcolVenta;
    @FXML
    private TableColumn tbcolStock;
    
    private ObservableList<ProductBean> productData;
   
    private int cerrar;
   
    private ProductLogic iLogicProduct;

    private UserBean user;
    
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
     * @param iLogicProduct
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
     * @throws jampclientside.exceptions.ReadException
     */
    public void initStage(Parent root) throws IOException, ReadException {
        LOGGER.info("Initializing Product Window.");
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setTitle("Productos");
        stage.setOnShowing(this::windowShow);
        menuLogOut.setOnAction(this::logOutAction);
        btnLogOut2.setOnAction(this::logOutAction);
        idMenuEventos.setOnAction(this::eventWindow);
        idMenuGastos.setOnAction(this::expenseWindow);
        idMenuTel.setOnAction(this::telephoneWindow);
        idMenuUsuarios.setOnAction(this::usersWindow);
        idMenuFTP.setOnAction(this::usersWindow);
        addProduct.setOnAction(this::handleAddProduct);
        delProduct.setOnAction(this::handleDeleteProduct);
        cbSearch.setOnAction(this::comboBoxOption);
        btnSearch.setOnAction(this::searchButton);      
        tbProducts.getSelectionModel().selectedItemProperty()
                .addListener(this::handleUsersTableSelectionChanged);
        tbcolName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        tbcolDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        tbcolPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        tbcolStock.setCellValueFactory(
                new PropertyValueFactory<>("stock"));
        tbcolVenta.setCellValueFactory(
                new PropertyValueFactory<>("venta"));
        tbcolVenta.setCellFactory(CheckBoxTableCell.forTableColumn(tbcolVenta));
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
        cbSearch.getItems().addAll("Todos los productos de mi txoko", "Todos los productos del catalogo", "Id del producto", "Nombre del producto");
        cbSearch.getSelectionModel().selectFirst();
        labelError.setVisible(false);
        cbSearch.requestFocus();
        txtSearch.setDisable(true);
        tbProducts.setEditable(true);
        btnSearch.setDisable(true);
        delProduct.setDisable(true);
        
        addProduct.setMnemonicParsing(true);
        addProduct.setText("_Añadir Producto");
        delProduct.setMnemonicParsing(true);
        delProduct.setText("_Eliminar Producto");
        menuMenu.setMnemonicParsing(true);
        menuMenu.setText("_Menu");
        menuLogOut.setMnemonicParsing(true);
        menuLogOut.setText("_Cerrar Sesion");
        menuLogOut.setAccelerator(
                new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        idMenuEventos.setMnemonicParsing(true);
        idMenuEventos.setText("Ir a la ventana de E_ventos");
        idMenuEventos.setAccelerator(
                new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));

        btnLogOut2.setMnemonicParsing(true);
        btnLogOut2.setText("_Cerrar Sesion");
        
        //String idTxoko = lbllTxoko.getText();
        String idTxoko = "1";
        
        productData = FXCollections.observableArrayList(iLogicProduct.findAllProductsByTxoko(idTxoko));
        tbProducts.setItems(productData);
    }
    
    /**
     * Close current view and open Login view method.
     *
     * @param event Action Event
     */
    public void logOutAction(ActionEvent event) {
        LOGGER.info("Beginning Productos::logout action");
        cerrar = 2;
        cerrarSesionAlert(cerrar);
    }

    /**
     * Method that show a confirm dialog to close session
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
     * 
     * @param observable
     * @param oldValue
     * @param newValue 
     */
    private void handleUsersTableSelectionChanged(ObservableValue observable,
             Object oldValue,
             Object newValue) {
        if(newValue!=null){
            delProduct.setDisable(false);
        }else{
            delProduct.setDisable(true);
        }   
    }

    /**
     * Action event handler for create button. It validates new user data, send
     * it to the business logic tier and updates user table view with new user
     * data.
     *
     * @param event The ActionEvent object for the event.
     */
    private void handleAddProduct(ActionEvent event) {
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
            }
        } catch (Exception e) {
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
    private void handleUpdateProduct(ActionEvent event) {
        try {
            //Get selected user data from table view.
            ProductBean selectedProduct = ((ProductBean) tbProducts.getSelectionModel()
                    .getSelectedItem());
            //check if loin vaalue for selectedrowin table
            //is equal to loginfield content
            if (!selectedProduct.getIdProduct().toString().equals(txtSearch.getText())) {
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
        } catch (ProductExist | UpdateException e) {
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
     * @param ev The ActionEvent object for the event.
     */
    private void handleDeleteProduct(ActionEvent ev){
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
                    ProductBean selectedProduct=((ProductBean)tbProducts.getSelectionModel()
                                                            .getSelectedItem());
                    this.iLogicProduct.deleteProduct(selectedProduct);
                    tbProducts.getItems().remove(tbProducts.getSelectionModel().getSelectedItem());
                    tbProducts.refresh();
                    
                    dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAlerta.setTitle("INFORMACION");
                    dialogoAlerta.setContentText("El producto "+selectedProduct.getName()+" ha sido eliminado.");
                    dialogoAlerta.setHeaderText("Eliminar un producto");
                    dialogoAlerta.showAndWait();
                    
                } else{
                    
                    dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAlerta.setTitle("INFORMACION");
                    dialogoAlerta.setContentText("No has eliminado el producto!!");
                    dialogoAlerta.setHeaderText("Eliminar un producto");
                    dialogoAlerta.showAndWait();
                }
                
            } else {

                Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
                dialogoAlerta.setTitle("ERROR");
                dialogoAlerta.setContentText("Tienes que seleccionar una fila!!");
                dialogoAlerta.setHeaderText("Eliminar un producto");
                dialogoAlerta.showAndWait();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    "PC07ProductsController: Error deleting user: {0}",
                    e.getMessage());
        }
    }

    public void eventWindow(ActionEvent ev) {
        LOGGER.info("clickOn Products Menu");
        try {
            EventLogic iLogicEvent = ILogicFactory.getEventLogic();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC05Events.fxml"));
            Parent root = (Parent) loader.load();
            PC05EventsController controller = (PC05EventsController) loader.getController(); 
            controller.setILogic(iLogicEvent);
            controller.setStage(stage);
            controller.initStage(root);
            cbSearch.requestFocus();
            stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    public void telephoneWindow(ActionEvent ev) {
        LOGGER.info("clickOn Telephone Menu");
        try {
            TelephoneLogic iLogicTelephone = ILogicFactory.getTelephoneLogic();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC08PhoneNumbers.fxml"));
            Parent root = (Parent) loader.load();
            PC08PhoneNumbersController controller = (PC08PhoneNumbersController) loader.getController();
            controller.setILogic(iLogicTelephone);
            controller.setStage(stage);
            controller.initStage(root);
            cbSearch.requestFocus();
            stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    public void expenseWindow(ActionEvent ev) {
        LOGGER.info("clickOn Gastos Menu");
        try {
            ExpenseLogic iLogicExpense = ILogicFactory.getExpenseLogic();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC04Expense.fxml"));
            Parent root = (Parent) loader.load();
            PC04ExpenseController controller = (PC04ExpenseController) loader.getController();
            controller.setILogic(iLogicExpense);
            controller.setStage(stage);
            controller.initStage(root);
            cbSearch.requestFocus();
            stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    public void usersWindow(ActionEvent ev) {
        LOGGER.info("clickOn User Menu");
        try {
            UserLogic iLogicUser = ILogicFactory.getUserLogic();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC03User.fxml"));
            Parent root = (Parent) loader.load();
            PC03UserController controller = (PC03UserController) loader.getController();
            controller.setILogic(iLogicUser);
            controller.setStage(stage);
            controller.initStage(root);
            cbSearch.requestFocus();
            stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }
    
    public void FTPClientWindow(ActionEvent ev) {
        LOGGER.info("clickOn FTP Client btn");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC06FTPClient.fxml"));
            Parent root = (Parent) loader.load();
            PC06FTPClientController controller = (PC06FTPClientController) loader.getController();
         //   controller.setILogic(ilogicFTP);
            controller.setStage(stage);
            controller.initStage(root);
            cbSearch.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }
    
    public void comboBoxOption(ActionEvent ev) {
        LOGGER.info("clickOn combo box");
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los productos de mi txoko")) {
            btnSearch.setDisable(true);
            txtSearch.setDisable(true);
            //String idTxoko = lbllTxoko.getText();
            String idTxoko = "1";
            productData = FXCollections.observableArrayList(iLogicProduct.findAllProductsByTxoko(idTxoko));
            if(productData == null){
               Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
               dialogoAlerta.setTitle("INFORMACION");
               dialogoAlerta.setHeaderText("No hay prodcutos en la lista");
               dialogoAlerta.showAndWait(); 
            }else{
                tbProducts.setItems(productData);
            } 
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los productos del catalogo")) {
            btnSearch.setDisable(true);
            txtSearch.setDisable(true);
            productData = FXCollections.observableArrayList(iLogicProduct.findAllProducts());
            if(productData == null){
               Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
               dialogoAlerta.setTitle("INFORMACION");
               dialogoAlerta.setHeaderText("No hay prodcutos en la lista");
               dialogoAlerta.showAndWait(); 
            }else{
                tbProducts.setItems(productData);
            } 
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del producto")) {
            btnSearch.setDisable(false);
            txtSearch.setText("");
            txtSearch.setDisable(false);
            txtSearch.requestFocus();
            tooltipID.setText("Escribe el ID del producto");
            txtSearch.setTooltip(tooltipID);
            labelError.setVisible(false);
            txtSearch.setStyle("-fx-border-color: -fx-box-border;");
 
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Nombre del producto")) {
            btnSearch.setDisable(false);
            txtSearch.setText("");
            txtSearch.setDisable(false);
            txtSearch.requestFocus();
            tooltipName.setText("Escribe el nombre del producto");
            txtSearch.setTooltip(tooltipName);
            labelError.setVisible(false);
            txtSearch.setStyle("-fx-border-color: -fx-box-border;");
        } 
    }

    public void searchButton(ActionEvent ev) {
        txtSearch.setDisable(false);
        LOGGER.info("clickOn search button");
        labelError.setVisible(false);
        txtSearch.setStyle("-fx-border-color: -fx-box-border;");
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del producto")) {
            productData = null;
            boolean tfIDEmpty = textEmptyOrNot();
            if (tfIDEmpty) {           
            String idProduct = txtSearch.getText();
            productData = FXCollections.observableArrayList(iLogicProduct.findProductById(idProduct));
                if(productData == null){
                   Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                   dialogoAlerta.setTitle("INFORMACION");
                   dialogoAlerta.setHeaderText("No hay productos en la lista");
                   dialogoAlerta.showAndWait(); 
                }else{
                    tbProducts.setItems(productData);
                }
            }else {
                txtSearch.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el id de un evento");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Nombre del producto")) {
            productData = null;
            boolean tfNameEmpty = textEmptyOrNot();
            if (tfNameEmpty) {
                String nameProduct = txtSearch.getText().trim();
                String idTxoko = "1";
                productData = FXCollections.observableArrayList(iLogicProduct.findProductByName(nameProduct, idTxoko));
                if(productData.isEmpty()){
                   Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                   dialogoAlerta.setTitle("INFORMACION");
                   dialogoAlerta.setHeaderText("No hay productos en la lista");
                   dialogoAlerta.showAndWait(); 
                }else{
                    tbProducts.setItems(productData);
                } 
            }else {
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
    
    private boolean isSelected() {
        boolean isSelected = false;
        if (!tbProducts.getSelectionModel().getSelectedItems().isEmpty()) {
            isSelected = true;
        }
        return isSelected;
    }
}
