/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.ProductBean;
import jampclientside.entity.TxokoBean;
import jampclientside.exceptions.BusinessLogicException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML PC07ProductsController class
 *
 * @author Julen
 */
public class PC07ProductsController {

    
    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("package.class");

    /**
     * 
     */
    private final Tooltip tooltip = new Tooltip();
    
    /**
     * 
     */
    private final Tooltip tooltipID = new Tooltip();
    
    /**
     * 
     */
    private final Tooltip tooltipName = new Tooltip();
   
    /**
     * 
     */
    private final Tooltip tooltipAddButton = new Tooltip();
    
    /**
     * 
     */
    @FXML
    private Menu menu;
    
    /**
     * 
     */
    @FXML
    private MenuItem idMenuGastos;
    
    /**
     * 
     */
    @FXML
    private MenuItem idMenuEventos;
    
    /**
     * 
     */
    @FXML
    private MenuItem idMenuUsuarios;
    
    /**
     * 
     */
    @FXML
    private MenuItem idMenuTel;
   
    /**
     * 
     */
    @FXML
    private MenuItem idMenuFTP;
    
    /**
     * 
     */
    @FXML
    private MenuBar menuBar;
    
    /**
     * 
     */
    @FXML
    private Menu menuMenu;
    
    /**
     * 
     */
    @FXML
    private MenuItem menuLogOut;
    
    /**
     * 
     */
    @FXML
    private Menu menuEvent;
    
    /**
     * 
     */
    @FXML
    private Menu menuGastos;
   
    /**
     * 
     */
    @FXML
    private Menu menuProductos;
    
    /**
     * 
     */
    @FXML
    private Menu menuUsuarios;
    
    /**
     * 
     */
    @FXML
    private Menu menuTelefonos;
    
    /**
     * 
     */
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
    private Label lbllTxoko;
    
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
    private Button btnSearch;
    
    /**
     * 
     */
    @FXML
    private Label labelError;
    
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
    private Button asignProduct;
    
    /**
     * 
     */
    @FXML
    private Button unasignProduct;
    
    /**
     * 
     */
    @FXML
    private Button updateProduct;
    
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
    private TableColumn tbcolPrice;
    
    /**
     * 
     */
    @FXML
    private TableColumn tbcolStock;
    
    /**
     * 
     */
    private ObservableList<ProductBean> productData;
   
    /**
     * 
     */
    private final List<ProductBean> productDatacopy = new ArrayList<>();
    /**
     * 
     */
    private int cerrar;
   
    /**
     * 
     */
    private ProductLogic iLogicProduct;

    /**
     * 
     */
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
    public void initStage(Parent root){
        try {
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
            asignProduct.setOnAction(this::handleAsignProduct);
            unasignProduct.setOnAction(this::handleUnasignProduct);
            cbSearch.setOnAction(this::comboBoxOption);
            btnSearch.setOnAction(this::searchButton);
            tbProducts.getSelectionModel().selectedItemProperty()
                    .addListener(this::handleUsersTableSelectionChanged);
            tbcolName.setCellValueFactory(
                    new PropertyValueFactory<>("name"));
            tbcolName.setCellFactory(TextFieldTableCell.<ProductBean>forTableColumn());
            tbcolName.setOnEditCommit(new EventHandler<CellEditEvent<ProductBean, String>>() {
                @Override
                public void handle(CellEditEvent<ProductBean,String> e) {
                    try {
                        ((ProductBean) tbProducts.getItems().get(
                                e.getTablePosition().getRow())
                                ).setName(e.getNewValue());
                        addUpdateProduct();
                    } catch (BusinessLogicException ex) {
                        Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            tbcolDescription.setCellFactory(TextFieldTableCell.<ProductBean>forTableColumn());
            tbcolDescription.setOnEditCommit(new EventHandler<CellEditEvent<ProductBean, String>>() {
                @Override
                public void handle(CellEditEvent<ProductBean,String> e) {
                    try {
                        ((ProductBean) tbProducts.getItems().get(
                                e.getTablePosition().getRow())
                                ).setDescription(e.getNewValue());
                        addUpdateProduct();
                    } catch (BusinessLogicException ex) {
                        Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            tbcolPrice.setCellFactory(TextFieldTableCell.<ProductBean>forTableColumn());
            tbcolPrice.setOnEditCommit(new EventHandler<CellEditEvent<ProductBean, String>>() {
                @Override
                public void handle(CellEditEvent<ProductBean,String> e) {
                    try {
                        ((ProductBean) tbProducts.getItems().get(
                                e.getTablePosition().getRow())
                                ).setPrice(e.getNewValue());
                        addUpdateProduct();
                    } catch (BusinessLogicException ex) {
                        Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            tbcolStock.setCellFactory(TextFieldTableCell.<ProductBean>forTableColumn());
            tbcolStock.setOnEditCommit(new EventHandler<CellEditEvent<ProductBean, String>>() {
                @Override
                public void handle(CellEditEvent<ProductBean,String> e) {
                    try {
                        ((ProductBean) tbProducts.getItems().get(
                                e.getTablePosition().getRow())
                                ).setStock(e.getNewValue());
                        addUpdateProduct();
                    } catch (BusinessLogicException ex) {
                        Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            tbcolDescription.setCellValueFactory(
                    new PropertyValueFactory<>("description"));
            tbcolPrice.setCellValueFactory(
                    new PropertyValueFactory<>("price"));
            tbcolStock.setCellValueFactory(
                    new PropertyValueFactory<>("stock"));
            stage.show();
            stage.setOnCloseRequest((WindowEvent e) -> {
                int cerrar = 1;
                e.consume();
                cerrarSesionAlert(cerrar);
            });
            
            String idTxoko = "5";
            
            productData = FXCollections.observableArrayList(iLogicProduct.findAllProducts());
            tbProducts.setItems(productData);
            
            productDatacopy.addAll(productData);
                    
            
            
        } catch (BusinessLogicException ex) {
            Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            addProduct.setDisable(true);
            asignProduct.setDisable(true);
            unasignProduct.setDisable(true);
           /* tooltipAddButton.setText("Busca todos los productos para activar el boton");
            addProduct.setTooltip(tooltipAddButton);*/
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
            String idTxoko = "5";
            
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
     * Method that change the selection of the table
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

                tbProducts.getItems().add(product);
                tbProducts.refresh();
 
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    "PC07ProductsController: Error adding product: {0}",
                    e.getMessage());
        }
    }
    
    /**
     * 
     * @param event 
     */
    private void handleAsignProduct(ActionEvent event) {
        try {
            Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("CONFIRMACION");
            dialogoAlerta.setContentText("¿Estas seguro que deseas añadir el producto al txoko?");
            dialogoAlerta.setHeaderText("Añadir un producto al txoko");
            Optional<ButtonType> result = dialogoAlerta.showAndWait();
            Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("buttonAdd");
            Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancelButton.setId("buttonCancel");
            
            ProductBean selectedProduct = ((ProductBean) tbProducts.getSelectionModel().getSelectedItem());

            if (result.get() == ButtonType.OK) {

                List<TxokoBean> txoko = new ArrayList<TxokoBean>();
                TxokoBean aux = new TxokoBean();
                String idTxoko = "5";
                aux.setIdTxoko(idTxoko);
                txoko.add(aux);
                selectedProduct.setTxokos(txoko);
                this.iLogicProduct.updateProduct(selectedProduct);

                tbProducts.refresh();
                
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("El producto "+selectedProduct.getName()+" ha sido añadido a tu txoko.");
                dialogoAlerta.setHeaderText("Añadir un producto al txoko");
                dialogoAlerta.showAndWait();
 
            }else{
                
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("No has añadido el producto "+selectedProduct.getName()+"  a tu txoko!!");
                dialogoAlerta.setHeaderText("Añadir un producto al txoko");
                dialogoAlerta.showAndWait();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    "PC07ProductsController: Error adding product: {0}",
                    e.getMessage());
        }
    }    
    
/**
     * 
     * @param event 
     */
    private void handleUnasignProduct(ActionEvent event) {
        try {
            Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("CONFIRMACION");
            dialogoAlerta.setContentText("¿Estas seguro que deseas quitar el producto del txoko?");
            dialogoAlerta.setHeaderText("Quitar un producto del txoko");
            Optional<ButtonType> result = dialogoAlerta.showAndWait();
            Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("buttonAdd");
            Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancelButton.setId("buttonCancel");
            
            ProductBean selectedProduct = ((ProductBean) tbProducts.getSelectionModel().getSelectedItem());

            if (result.get() == ButtonType.OK) {

                List<TxokoBean> txoko = new ArrayList<TxokoBean>();
                TxokoBean aux = new TxokoBean();
                String idTxoko = "5";
                aux.setIdTxoko(idTxoko);
                txoko.remove(aux);
                selectedProduct.setTxokos(txoko);
                this.iLogicProduct.updateProduct(selectedProduct);

                tbProducts.refresh();
                
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("El producto "+selectedProduct.getName()+" ha sido eliminado de tu txoko.");
                dialogoAlerta.setHeaderText("Quitar un producto del txoko");
                dialogoAlerta.showAndWait();
 
            }else{
                
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("No has quitado el producto "+selectedProduct.getName()+"  de tu txoko!!");
                dialogoAlerta.setHeaderText("Quitar un producto del txoko");
                dialogoAlerta.showAndWait();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    "PC07ProductsController: Error adding product: {0}",
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
        } catch (BusinessLogicException e) {
            LOGGER.log(Level.SEVERE,
                    "PC07ProductsController: Error deleting user: {0}",
                    e.getMessage());
        }
    }

    /**
     * 
     * @param ev 
     */
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

    /**
     * 
     * @param ev 
     */
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

    /**
     * 
     * @param ev 
     */
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

    /**
     * 
     * @param ev 
     */
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
    
    /**
     * 
     * @param ev 
     */
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
    
    /**
     * 
     * @param ev 
     */
    public void comboBoxOption(ActionEvent ev){
        LOGGER.info("clickOn combo box");
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los productos de mi txoko")) {
            try {
                asignProduct.setDisable(true);
                unasignProduct.setDisable(false);
                addProduct.setDisable(true);
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
            } catch (BusinessLogicException ex) {
                Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los productos del catalogo")) {
            try {
                asignProduct.setDisable(false);
                unasignProduct.setDisable(true);
                addProduct.setDisable(false);
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
            } catch (BusinessLogicException ex) {
                Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del producto")) {
            asignProduct.setDisable(false);
            unasignProduct.setDisable(true);
            addProduct.setDisable(true);
            btnSearch.setDisable(false);
            txtSearch.setText("");
            txtSearch.setDisable(false);
            txtSearch.requestFocus();
            tooltipID.setText("Escribe el ID del producto");
            txtSearch.setTooltip(tooltipID);
            labelError.setVisible(false);
            txtSearch.setStyle("-fx-border-color: -fx-box-border;");
 
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Nombre del producto")) {
            asignProduct.setDisable(false);
            unasignProduct.setDisable(true);
            addProduct.setDisable(true);
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

    /**
     * 
     * @param ev 
     */
    public void searchButton(ActionEvent ev){
        txtSearch.setDisable(false);
        LOGGER.info("clickOn search button");
        labelError.setVisible(false);
        txtSearch.setStyle("-fx-border-color: -fx-box-border;");
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del producto")) {
            productData = null;
            boolean tfIDEmpty = textEmptyOrNot();
            if (tfIDEmpty) {           
                try {
                    String idProduct = txtSearch.getText();
                    productData = FXCollections.observableArrayList(iLogicProduct.findProductById(idProduct));
                    if(productData.size()==0){
                        Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                        dialogoAlerta.setTitle("INFORMACION");
                        dialogoAlerta.setHeaderText("No hay productos en la lista");
                        dialogoAlerta.showAndWait();
                    }else{
                        tbProducts.setItems(productData);
                    }
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
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
                try {
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
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC07ProductsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else {
                txtSearch.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el nombre");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    private boolean textEmptyOrNot() {
        boolean empty = false;
        //si no esta vacio
        if (!this.txtSearch.getText().trim().equals("")) {
            empty = true;
        }
        return empty;
    }
    
    /**
     * 
     * @return 
     */
    private boolean isSelected() {
        boolean isSelected = false;
        if (!tbProducts.getSelectionModel().getSelectedItems().isEmpty()) {
            isSelected = true;
        }
        return isSelected;
    }
    
    /**
     * 
     * 
     */
    private void addUpdateProduct() throws BusinessLogicException {
        List<ProductBean> productos = tbProducts.getItems();
      
        for(ProductBean product: productos){
            if(product.getName()!=null && !product.getName().trim().isEmpty()&& 
              product.getDescription()!=null && !product.getDescription().trim().isEmpty()&& 
              product.getPrice()!=null && !product.getPrice().trim().isEmpty() && 
              product.getStock()!=null && !product.getStock().trim().isEmpty()){             
                    
                    List productequals = productDatacopy.stream().filter(p -> p.getIdProduct().equals(product.getIdProduct())).collect(Collectors.toList());
                    if(productequals.size()==0){
                        iLogicProduct.createProduct(product);
                        //addProdcut(product);
                    }else if(!productequals.get(0).equals(product)){
                        iLogicProduct.updateProduct(product);
                    }
            }else{
                    LOGGER.info("CHAPUZAS");
            }
        }
    }
 

    private void addProdcut(ProductBean product) throws BusinessLogicException {

            Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("CONFIRMACION");
            dialogoAlerta.setContentText("¿Estas seguro que deseas añadir el producto "+product.getName()+"?");
            dialogoAlerta.setHeaderText("Añadir un producto");
            Optional<ButtonType> result = dialogoAlerta.showAndWait();
            Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("buttonAdd");
            Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancelButton.setId("buttonCancel");
        
            if (result.get() == ButtonType.OK) {
                
                iLogicProduct.createProduct(product);
                
                tbProducts.getItems().add(product);
                tbProducts.refresh();
 
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("El producto "+product.getName()+" ha sido añadido.");
                dialogoAlerta.setHeaderText("Añadir un producto");
                dialogoAlerta.showAndWait();
            }else{
                
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("El producto "+product.getName()+" no ha sido añadido.");
                dialogoAlerta.setHeaderText("Añadir un producto");
                dialogoAlerta.showAndWait();
                
            }
    }

}
