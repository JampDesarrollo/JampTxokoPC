/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.TelephoneBean;
import jampclientside.entity.UserBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.logic.EventLogic;
import jampclientside.logic.ExpenseLogic;
import jampclientside.logic.FTPClientLogic;
import jampclientside.logic.ILogicFactory;
import jampclientside.logic.ProductLogic;
import java.io.IOException;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jampclientside.logic.TelephoneLogic;
import jampclientside.logic.UserLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Julen
 */
public class PC08PhoneNumbersController{
    private TextField textField;
    
    
    /**
     * Maximum characters that can be inserted
     */
    private static final int MAX_CARACT = 255;

    /**
     * 
     */
    private static final Logger LOGGER = Logger.getLogger("package.class");
    /**
     * 
     */
    private Stage stage;
    /**
     * 
     */
    private TelephoneLogic ilogicTelephone;
    /**
     * 
     */
    private UserBean user;
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
    @FXML
    private VBox principalPane;
    /**
     * 
     */
    @FXML
    private MenuItem idMenuExpense;
    /**
     * 
     */
    @FXML
    private MenuItem idMenuProduct;
    /**
     * 
     */
    @FXML
    private MenuItem idMenuUser;
    /**
     * 
     */
    @FXML
    private MenuItem idMenuEvent;
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
    private Label lblTxoko;
    /**
     * 
     */
    @FXML
    private TextField txtSearchTel;
    /**
     * 
     */
    @FXML
    private Button btnSearchTel;
    /**
     * 
     */
    @FXML
    private Label labelError;
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
    private TableView<TelephoneBean> tbTelephone;
    /**
     * 
     */
    @FXML
    private ComboBox<String> cbSearchTel;
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
    private TableColumn tbcolTelephone;
    /**
     * 
     */
    @FXML
    private TableColumn tbcolTown;
    /**
     * 
     */
    @FXML
    private Label requiredTel;

    /**
     * 
     */
    private ObservableList<TelephoneBean> telephoneData;
    
    /**
     * 
     */
    private int cerrar;

    /**
     * The business logic object containing all business methods.
     */
    private TelephoneLogic iLogicTelephone;

    /**
     * Telephone object
     */
    private TelephoneBean telephone;
    
    /**
     * 
     */
    private final List<TelephoneBean> telephoneDatacopy = new ArrayList<>();

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
     * @param user
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
        LOGGER.info("Initializing Telephone stage.");
        //Create a scene associated to the node graph root.
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //Associate scene to primaryStage(Window)
        stage.setScene(scene);
        //Set window properties
        stage.setTitle("Telefonos");
        //Set window's events handlers
        stage.setOnShowing(this::windowShow);
        //menu item de cerrar sesion
        menuLogOut.setOnAction(this::logOutAction);
        //boton de cerrar sesion
        btnLogOut2.setOnAction(this::logOutAction);
        //ir a la ventana de productos
        idMenuProduct.setOnAction(this::productWindow);
        //ir a la ventana de eventos
        idMenuEvent.setOnAction(this::eventWindow);
        //gastos
        idMenuExpense.setOnAction(this::expenseWindow);
        //ventana de los usuarios
        idMenuUser.setOnAction(this::usersWindow);
        //ventana de los usuarios
        idMenuFTP.setOnAction(this::usersWindow);
        //boton añadir evento
        addTelephone.setOnAction(this::handleAddTelephone);
        //boton eleminar evento
        delTelephone.setOnAction(this::handleDeleteTelephone);
        //dependiendo la opcion que pulse del combo box
        cbSearchTel.setOnAction(this::comboBoxOption);
        //boton de busqueda
        btnSearchTel.setOnAction(this::searchButton);
        tbcolName.setCellFactory(TextFieldTableCell.<TelephoneBean>forTableColumn());
        tbcolName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TelephoneBean, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TelephoneBean,String> e) {
                try {
                    ((TelephoneBean) tbTelephone.getItems().get(
                            e.getTablePosition().getRow())
                            ).setName(e.getNewValue());
                            addUpdateTelephone();
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tbcolDescription.setCellFactory(TextFieldTableCell.<TelephoneBean>forTableColumn());
        tbcolDescription.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TelephoneBean, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TelephoneBean,String> e) {
                try {
                    ((TelephoneBean) tbTelephone.getItems().get(
                            e.getTablePosition().getRow())
                            ).setDescription(e.getNewValue());
                    addUpdateTelephone();
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tbcolTelephone.setCellFactory(TextFieldTableCell.<TelephoneBean>forTableColumn());
        tbcolTelephone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TelephoneBean, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TelephoneBean,String> e) {
                try {
                    ((TelephoneBean) tbTelephone.getItems().get(
                            e.getTablePosition().getRow())
                            ).setTelephone(e.getNewValue());
                    addUpdateTelephone();
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tbcolTown.setCellFactory(TextFieldTableCell.<TelephoneBean>forTableColumn());
        tbcolTown.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TelephoneBean, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TelephoneBean,String> e) {
                try {
                    ((TelephoneBean) tbTelephone.getItems().get(
                            e.getTablePosition().getRow())
                            ).setTown(e.getNewValue());
                    addUpdateTelephone();
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //las columnas van a coger el valor de los atributos
        tbTelephone.getSelectionModel().selectedItemProperty()
                .addListener(this::handleUsersTableSelectionChanged);
        tbcolDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        tbcolName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        tbcolTelephone.setCellValueFactory(
                new PropertyValueFactory<>("telephone"));
        tbcolTown.setCellValueFactory(
                new PropertyValueFactory<>("town"));
        //Show primary window
        stage.show();
        stage.setOnCloseRequest((WindowEvent e) -> {
            cerrar = 1;
            e.consume();
            cerrarSesionAlert(cerrar);
            
        });
        /*    telephoneData = FXCollections.observableArrayList(iLogicTelephone.findAllTelephone());
        tbTelephone.setItems(telephoneData);
        
        telephoneDatacopy.addAll(telephoneData);*/
    }

    /**
     * Initializes the window when shown.
     *
     * @param event WindowEvent event
     */
    private void windowShow(WindowEvent event) {
        LOGGER.info("Beginning Telephone window::windowShow");
        
         lblDate.setText("Último acceso: " + user.getLastAccess());
        lblEmail.setText("Email: " + user.getEmail());
        lblFullName.setText("Nombre Completo: " + user.getFullname());
        lblLogin.setText("Login: " + user.getLogin());
        lblTxoko.setText("Txoko: " + user.getTxoko().getName());
        
        cbSearchTel.getItems().removeAll(cbSearchTel.getItems());
        cbSearchTel.getItems().addAll("Todos los telefonos del catalogo", "Id del telefono", "Nombre del telefono");
        cbSearchTel.getSelectionModel().selectFirst();
        labelError.setVisible(false);
        cbSearchTel.requestFocus();
        txtSearchTel.setDisable(true);
        tbTelephone.setEditable(true);
        btnSearchTel.setDisable(true);
        delTelephone.setDisable(true);
        addTelephone.setMnemonicParsing(true);
        addTelephone.setText("_Añadir Telefono");
        delTelephone.setMnemonicParsing(true);
        delTelephone.setText("_Eliminar Telefono");
        menuMenu.setMnemonicParsing(true);
        menuMenu.setText("_Menu");
        menuLogOut.setMnemonicParsing(true);
        menuLogOut.setText("_Cerrar Sesion");
        menuLogOut.setAccelerator(
                new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
        btnLogOut2.setMnemonicParsing(true);
        btnLogOut2.setText("_Cerrar Sesion");
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
            delTelephone.setDisable(false);
        }else{
            delTelephone.setDisable(true);
        }   
    }

    /**
     * Action event handler for create button. 
     * It validates new user data, send it to the business logic 
     * tier and updates telephone table view with new telephone data.
     * 
     * @param ev 
     */
    public void handleAddTelephone(ActionEvent ev) {
        try{
        Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoAlerta.setTitle("CONFIRMACION");
        dialogoAlerta.setContentText("¿Estas seguro que deseas añadir un telefono?");
        dialogoAlerta.setHeaderText("Añadir un telefono");
        Optional<ButtonType> result = dialogoAlerta.showAndWait();
        Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("buttonAdd");
        Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("buttonCancel");

        if (result.get() == ButtonType.OK) {

            TelephoneBean event = new TelephoneBean();
            
            Platform.runLater(new Runnable() 
            { 
                @Override 
                public void run() 
                { 
                 tbTelephone.requestFocus(); 
                 tbTelephone.getSelectionModel().selectLast();
                 tbTelephone.getFocusModel().focus(0); 
                } 
            }); 

            tbTelephone.getSelectionModel().selectFirst();
            
            tbTelephone.getItems().add(event);
            tbTelephone.refresh();

        }

        }catch(Exception e){
            LOGGER.log(Level.SEVERE,
                    "PC08TelephoneController: Error adding telephone: {0}",
                    e.getMessage());
        }
    }
    
    /**
     * Action event handler for delete button. It asks user for confirmation on
     * delete, sends delete message to the business logic tier and updates user
     * table view.
     * 
     * @param ev 
     */
    public void handleDeleteTelephone(ActionEvent ev) {
        boolean isSelected = isSelected();
        if (isSelected) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("CONFIRMACION");
            dialogoAlerta.setContentText("¿Estas seguro que deseas eliminar un telefono?");
            dialogoAlerta.setHeaderText("Eliminar un telefono");
            Optional<ButtonType> resultado = dialogoAlerta.showAndWait();
            Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("buttonDelete");
            Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancelButton.setId("buttonNotDelete");
            if (resultado.get() == ButtonType.OK) {
                try {
                    TelephoneBean selectedTelephone=((TelephoneBean)tbTelephone.getSelectionModel()
                            .getSelectedItem());
                    this.iLogicTelephone.deleteTelephone(selectedTelephone);
                    
                    tbTelephone.getItems().remove(tbTelephone.getSelectionModel().getSelectedItem());
                    tbTelephone.refresh();
                    
                    dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAlerta.setTitle("INFORMACION");
                    dialogoAlerta.setContentText("El telefono "+selectedTelephone.getName()+" ha sido eliminado.");
                    dialogoAlerta.setHeaderText("Eliminar un Telefono");
                    dialogoAlerta.showAndWait();
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                    dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAlerta.setTitle("INFORMACION");
                    dialogoAlerta.setContentText("No has eliminado el telefono!!");
                    dialogoAlerta.setHeaderText("Eliminar un telefono");
                    dialogoAlerta.showAndWait();
                }
        } else {

            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("Tienes que seleccionar una fila!!");
            dialogoAlerta.setHeaderText("Eliminar un telfono");
            dialogoAlerta.showAndWait();
        }
    }

    /**
     * This method is to go to the eventPane
     * 
     * @param ev 
     */
    public void eventWindow(ActionEvent ev) {
        LOGGER.info("clickOn Event Menu");
        try {
            EventLogic iLogicEvent = ILogicFactory.getEventLogic();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC05EventsController.fxml"));
            Parent root = (Parent) loader.load();
            PC05EventsController controller = (PC05EventsController) loader.getController();
            controller.setILogic(iLogicEvent);
            controller.setStage(stage);
            controller.initStage(root);
            cbSearchTel.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    /**
     * This method is to go to the productPane
     * 
     * @param ev 
     */
    public void productWindow(ActionEvent ev) {
        LOGGER.info("clickOn Telephone Menu");
        try {
            ProductLogic iLogicProduct = ILogicFactory.getProductLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC07Products.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC07ProductsController controller = (PC07ProductsController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogicProduct(iLogicProduct);
            controller.setUser(user);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearchTel.requestFocus();
            stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }

    }

    /**
     * This method is to go to the expensePane
     * 
     * @param ev 
     */
    public void expenseWindow(ActionEvent ev) {
        LOGGER.info("clickOn Gastos Menu");
        try {
            ExpenseLogic iLogicExpense = ILogicFactory.getExpenseLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC04Expense.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC04ExpenseController controller = (PC04ExpenseController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogicExpense);
            controller.setUser(user);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearchTel.requestFocus();
            stage.hide();            
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }
    
    /**
     * This method is to go to the userPane
     * 
     * @param ev 
     */
    public void usersWindow(ActionEvent ev) {
        LOGGER.info("clickOn User Menu");
        try {
            UserLogic iLogicUser = ILogicFactory.getUserLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC03User.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC03UserController controller = (PC03UserController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogicUser);
            controller.setUser(user);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearchTel.requestFocus();
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
            FTPClientLogic iLogic = ILogicFactory.getFTPClientLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC06FTPClient.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC06FTPClientController controller = (PC06FTPClientController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearchTel.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }
    
    /**
     * 
     * @param ev 
     */
    public void comboBoxOption(ActionEvent ev) {
        LOGGER.info("clickOn combo box");
        switch (cbSearchTel.getSelectionModel().getSelectedItem()) {
            case "Todos los telefonos del catalogo":
                try {
                    addTelephone.setDisable(false);
                    telephoneData = FXCollections.observableArrayList(iLogicTelephone.findAllTelephone());
                    if(telephoneData == null){
                        Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
                        dialogoAlerta.setTitle("INFORMACION");
                        dialogoAlerta.setHeaderText("No hay telefonos en la lista");
                        dialogoAlerta.showAndWait();
                    }else{
                        tbTelephone.setItems(telephoneData);
                    }
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case "Id del telefono":
                tbTelephone.getItems().clear();
                addTelephone.setDisable(true);
                btnSearchTel.setDisable(false);
                txtSearchTel.setText("");
                txtSearchTel.setDisable(false);
                txtSearchTel.requestFocus();
                tooltipID.setText("Escribe el ID del telefono");
                txtSearchTel.setTooltip(tooltipID);
                labelError.setVisible(false);
                txtSearchTel.setStyle("-fx-border-color: -fx-box-border;");
                break;
            case "Nombre del telefono":                
                tbTelephone.getItems().clear();
                addTelephone.setDisable(true);
                btnSearchTel.setDisable(false);
                txtSearchTel.setText("");
                txtSearchTel.setDisable(false);
                txtSearchTel.requestFocus();
                tooltipName.setText("Escribe el nombre del telefono");
                txtSearchTel.setTooltip(tooltipName);
                labelError.setVisible(false);
                txtSearchTel.setStyle("-fx-border-color: -fx-box-border;");
                break;
            default:
                break;
        }
    }
    
    /**
     * This method is for the search button.
     * When you click on the button, first check that text field it isn't empty, 
     * if it is empty, a label error appears with a message.
     * Also check that the size does not exceed 255 characters.
     * If the conditions are ok, the method stores the telephones in a collection,
     * if the collection is empty, a dialogue with the message appears.
     * 
     * @param ev 
     */
    public void searchButton(ActionEvent ev) {
        LOGGER.info("clickOn search button");
        //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
        labelError.setVisible(false);
        txtSearchTel.setStyle("-fx-border-color: -fx-box-border;");

        if (cbSearchTel.getSelectionModel().getSelectedItem().equals("Id del telefono")) {
            //miramos si el textfield esta vacio o no
            boolean tfIDEmpty = textEmptyOrNot();
            //si no esta vacio
            if (tfIDEmpty) {                    
                if(txtSearchTel.getText().trim().length() < MAX_CARACT){      
                    try {
                        int idTelephone = Integer.parseInt(txtSearchTel.getText().trim());

                        telephoneData = FXCollections.observableArrayList(iLogicTelephone.findTelephoneById(idTelephone));
                        if(telephoneData == null){
                            Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                            dialogoAlerta.setTitle("INFORMACION");
                            dialogoAlerta.setHeaderText("No hay telefonos en la lista");
                            dialogoAlerta.showAndWait();
                        }else{
                            tbTelephone.setItems(telephoneData);
                        }
                    } catch (BusinessLogicException ex) {
                        Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    txtSearchTel.setStyle("-fx-border-color: red;");
                    labelError.setText("Se ha introducido un numero superior de caracteres al permitido");
                    labelError.setVisible(true);
                    labelError.setStyle("-fx-text-inner-color: red;");
                }
            } else {
                txtSearchTel.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el id de un evento");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }
        } else if (cbSearchTel.getSelectionModel().getSelectedItem().equals("Nombre del telefono")) {
            boolean tfNameEmpty = textEmptyOrNot();
            if (tfNameEmpty) {
                if(txtSearchTel.getText().trim().length() < MAX_CARACT){ 
                    try {
                        String name = txtSearchTel.getText();

                        telephoneData = FXCollections.observableArrayList(iLogicTelephone.findTelephoneByName(name));
                        if(telephoneData == null){
                            Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                            dialogoAlerta.setTitle("INFORMACION");
                            dialogoAlerta.setHeaderText("No hay telefonos en la lista");
                            dialogoAlerta.showAndWait();
                        }else{
                            tbTelephone.setItems(telephoneData);
                        }
                    }
                    catch (BusinessLogicException ex) {
                        Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    txtSearchTel.setStyle("-fx-border-color: red;");
                    labelError.setText("Se ha introducido un numero superior de caracteres al permitido");
                    labelError.setVisible(true);
                    labelError.setStyle("-fx-text-inner-color: red;");
                }
            }
            else {
                txtSearchTel.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el nombre");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }
        }
    }
    
    /** 
     * This method compares the data in the table with the data in the database 
     * to update or to create a new telephone. 
     * In a list, keep all the products that are in the database and 
     * in another list the telephones in the table.
     * First check if all the fields are informed. 
     * If yes, compare the two lists and if a telephone is repeated, 
     * keep it in a third list. 
     * If the third list contains any telephone, update the telephone, 
     * otherwise create the telephone. 
     */
    private void addUpdateTelephone() throws BusinessLogicException {
        List<TelephoneBean> telephones = tbTelephone.getItems();
      
        for(TelephoneBean tel: telephones){
            if(tel.getName()!=null && !tel.getName().trim().isEmpty()&& 
              tel.getDescription()!=null && !tel.getDescription().trim().isEmpty()&& 
              tel.getTown()!=null && !tel.getTown().trim().isEmpty() &&
              tel.getTelephone()!=null && !tel.getTelephone().trim().isEmpty()) 
              {             
                    
                    List telephoneEquals = telephoneDatacopy.stream().filter(t -> t.getTelephone().equals(tel.getTelephone())).collect(Collectors.toList());
                    if(telephoneEquals.isEmpty()){
                        addTelephone(tel);
                    }else if(!telephoneEquals.get(0).equals(tel)){
                        updateTelephone(tel);
                    }
            }
        }
    }

    /**
     * This method is to add the selected telephone in the table. 
     * First ask for the confirmation, if it is positive, add the telephone 
     * and confirm it in a dialog box.
     * If it is negative, give the confirmation in a dialog and don't add it.
     * 
     * @param telephone
     * @throws BusinessLogicException 
     */
    private void addTelephone(TelephoneBean telephone) {
            Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("CONFIRMACION");
            dialogoAlerta.setContentText("¿Estas seguro que deseas añadir el teléfono "+telephone.getName()+"?");
            dialogoAlerta.setHeaderText("Añadir un teléfono");
            Optional<ButtonType> result = dialogoAlerta.showAndWait();
            Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("buttonAdd");
            Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancelButton.setId("buttonCancel");
        
            if (result.get() == ButtonType.OK) {
                
                try {
                    iLogicTelephone.createTelephone(telephone);
                    
                    dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAlerta.setTitle("INFORMACION");
                    dialogoAlerta.setContentText("El teléfono "+telephone.getName()+" ha sido añadido.");
                    dialogoAlerta.setHeaderText("Añadir un teléfono");
                    dialogoAlerta.showAndWait();
                } catch (BusinessLogicException ex) {
                    Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("El teléfono "+telephone.getName()+" no ha sido añadido.");
                dialogoAlerta.setHeaderText("Añadir un teléfono");
                dialogoAlerta.showAndWait();
                
            }
    } 
    
    /**
     * This method is to update the selected telephone in the table. 
     * First ask for the confirmation, if it is positive, update the telephone 
     * and confirm it in a dialog box.
     * If it is negative, give the confirmation in a dialog and don't update it.
     * 
     * @param telephone
     * @throws BusinessLogicException 
     */
    private void updateTelephone(TelephoneBean telephone) throws BusinessLogicException {

            Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("CONFIRMACION");
            dialogoAlerta.setContentText("¿Estas seguro que deseas actualizar el telefono "
                    +telephone.getName()+" "+telephone.getDescription()+"?");
            dialogoAlerta.setHeaderText("Actualizar un telefono");
            Optional<ButtonType> result = dialogoAlerta.showAndWait();
            Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
            okButton.setId("buttonAdd");
            Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
            cancelButton.setId("buttonCancel");
        
            if (result.get() == ButtonType.OK) {
                
                iLogicTelephone.updateTelephone(telephone);
 
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("El telefono "+telephone.getName()
                        +" "+telephone.getDescription()+" ha sido actualizado.");
                dialogoAlerta.setHeaderText("Actualizar un telefono");
                dialogoAlerta.showAndWait();
            }else{
                
                dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                dialogoAlerta.setTitle("INFORMACION");
                dialogoAlerta.setContentText("El telefono "+telephone.getName()+" "
                        +telephone.getDescription()+" no ha sido actualizado.");
                dialogoAlerta.setHeaderText("Actualizar un telefono");
                dialogoAlerta.showAndWait();
                
            }
    }
    
    /**
     * This method checks if the field is empty
     * 
     * @return boolean, is empty, return true, if not, return false
     */
    private boolean textEmptyOrNot() {
        boolean empty = false;
        //si no esta vacio
        if (!this.txtSearchTel.getText().trim().equals("")) {
            empty = true;
        }
        return empty;
    }
    
    /**
     * This method checks if the row of the table is selected
     * 
     * @return boolean is selected return true, is not selected return false
     */
    private boolean isSelected() {
        boolean isSelected = false;
        //si es diferente a vacio
        if (!tbTelephone.getSelectionModel().getSelectedItems().isEmpty()) {
            //hay algo seleccionado
            isSelected = true;
        }
        return isSelected;
    }
    
    /**
     * Close current view and open Login view method.
     *
     * @param event Action Event
     */
    public void logOutAction(ActionEvent event) {
        LOGGER.info("Beginning Telephones::logout action");
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
