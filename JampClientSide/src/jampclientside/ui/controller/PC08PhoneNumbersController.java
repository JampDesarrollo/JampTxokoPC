/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.EventBean;
import jampclientside.entity.ProductBean;
import jampclientside.entity.TelephoneBean;
import jampclientside.exceptions.ReadException;
import jampclientside.logic.EventLogic;
import jampclientside.logic.ExpenseLogic;
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
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author Julen
 */
public class PC08PhoneNumbersController{

    private static final Logger LOGGER = Logger.getLogger("package.class");
    private Stage stage;
    private TelephoneLogic ilogicTelephone;
    private UserBean user;
    private Tooltip tooltip = new Tooltip();
    private Tooltip tooltipID = new Tooltip();
    private Tooltip tooltipName = new Tooltip();
    @FXML
    private VBox principalPane;
    /**
     * Log out menu item
     */
    @FXML
    private MenuItem idMenuGastos;
    @FXML
    private MenuItem idMenuProductos;
    @FXML
    private MenuItem idMenuUsuarios;
    @FXML
    private MenuItem idMenuEvent;
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
    private TextField txtSearchTel;
    @FXML
    private Button btnSearchTel;
    @FXML
    private Label labelError;
    @FXML
    private Button addTelephone;
    @FXML
    private Button delTelephone;
    @FXML
    private Button btnLogOut2;
    @FXML
    private TableView<TelephoneBean> tbTelephone;
    @FXML
    private ComboBox<String> cbSearchTel;
    @FXML
    private TableColumn tbcolName;
    @FXML
    private TableColumn tbcolDescription;
    @FXML
    private TableColumn tbcolNumber;
    @FXML
    private Label requiredTel;

    private ObservableList<TelephoneBean> telephoneData;
    
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
    private TelephoneBean telephone;

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
    public void setUser(TelephoneBean telephone) {
        this.telephone = telephone;

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
        stage.setResizable(true);
        //Set window properties
        stage.setTitle("Principal");
        //Set window's events handlers
        stage.setOnShowing(this::windowShow);
        //menu item de cerrar sesion
        menuLogOut.setOnAction(this::logOutAction);
        //boton de cerrar sesion
        btnLogOut2.setOnAction(this::logOutAction);
        //ir a la ventana de productos
        idMenuProductos.setOnAction(this::productWindow);
        //ir a la ventana de eventos
        idMenuEvent.setOnAction(this::eventWindow);
        //gastos
        idMenuGastos.setOnAction(this::expenseWindow);
        //ventana de los usuarios
//        idMenuUsuarios.setOnAction(this::usersWindow);
        //cliente FTP
//        btnImgEvent.setOnAction(this::FTPClientWindow);
        //boton añadir evento
        addTelephone.setOnAction(this::handleAddTelephone);
        //boton eleminar evento
        delTelephone.setOnAction(this::handleDeleteTelephone);
        //dependiendo la opcion que pulse del combo box
        cbSearchTel.setOnAction(this::comboBoxOption);
        //boton de busqueda
        btnSearchTel.setOnAction(this::searchButton);

        tbcolDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        tbcolName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        tbcolNumber.setCellValueFactory(
                new PropertyValueFactory<>("telephone"));

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
        /*
        String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(user.getLastAccess());
        lblDate.setText("Último acceso: " + date);
        lblEmail.setText("Email: " + user.getEmail());
        lblFullName.setText("Nombre Completo: " + user.getFullname());
        lblLogin.setText("Login: " + user.getLogin());
        */
        
        cbSearchTel.getItems().removeAll(cbSearchTel.getItems());
        cbSearchTel.getItems().addAll("Todos los telefonos de mi txoko", "Id del telefono", "Nombre del telefono");
        labelError.setVisible(false);
        cbSearchTel.requestFocus();
        txtSearchTel.setDisable(true);
        tbTelephone.setEditable(true);
        btnSearchTel.setDisable(true);
        addTelephone.setDisable(true);
        delTelephone.setDisable(true);

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

    public void handleAddTelephone(ActionEvent ev) {
        Alert dialogoAlerta = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoAlerta.setTitle("CONFIRMACION");
        dialogoAlerta.setContentText("¿Estas seguro que deseas añadir un telefono?");
        dialogoAlerta.setHeaderText("Añadir un telefono");
        Optional<ButtonType> result = dialogoAlerta.showAndWait();
        Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("buttonAdd");
        Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("buttonCancel");
        //si da en el buttonAdd que se añada una fila
        if (result.get() == ButtonType.OK) {
            //añadir una fila a la tabla
            //el txoko se puede coger del label o de la base de datos
            //añadir a la base de datos y eliminar la fila 
            EventBean event = new EventBean();
            //añadir fila en blanco
            tbTelephone.getItems().add(null);
            tbcolName.setCellValueFactory(
                    new PropertyValueFactory<>("name"));
            tbcolName.setCellFactory(TextFieldTableCell.forTableColumn());
           
            tbcolDescription.setCellValueFactory(
                    new PropertyValueFactory<>("description"));
            tbcolDescription.setCellFactory(TextFieldTableCell.forTableColumn());

            tbcolNumber.setCellValueFactory(
                    new PropertyValueFactory<>("telephone"));
            tbcolNumber.setCellFactory(TextFieldTableCell.forTableColumn());
            
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("CONFIRMACION");
            dialog.setContentText("El nuevotelefono ha sido añadido");
            dialog.setHeaderText("Añadir un telefono");
            Optional<ButtonType> resultado = dialog.showAndWait();
            Button aceptar = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            aceptar.setId("aceptar");

        }

    }
    
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
                //si pulsa en en aceptar que elimine
                //tengo que saber el txoko de la persona
                //el txoko se puede coger del label o de la base de datos
                // tengo que saber el id del evento
                //eliminar de la base de datos y eliminar la fila
                tbTelephone.getItems().remove(tbTelephone.getSelectionModel().getSelectedItem());
                tbTelephone.refresh();
            }
        } else {

            Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("Tienes que seleccionar una fila!!");
            dialogoAlerta.setHeaderText("Eliminar un telfono");
            dialogoAlerta.showAndWait();
        }
    }

    public void eventWindow(ActionEvent ev) {
        LOGGER.info("clickOn Event Menu");
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
    public void productWindow(ActionEvent ev) {
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
        } catch (ReadException ex) {
            Logger.getLogger(PC08PhoneNumbersController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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
    
    public void comboBoxOption(ActionEvent ev) {
        LOGGER.info("clickOn combo box");
        if (cbSearchTel.getSelectionModel().getSelectedItem().equals("Todos los telefonos de mi txoko")) {
            //el boton de busqueda estara habilitado
            btnSearchTel.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            txtSearchTel.setText("");
            //deshabilitar el textfield
            txtSearchTel.setDisable(true);
            btnSearchTel.requestFocus();
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            labelError.setVisible(false);
            txtSearchTel.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearchTel.getSelectionModel().getSelectedItem().equals("Id del evento")) {
            //el boton de busqueda estara habilitado
            btnSearchTel.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            txtSearchTel.setText("");
            //habilitar el textfield
            txtSearchTel.setDisable(false);
            //que se ponga el foco en el text field
            txtSearchTel.requestFocus();
            tooltipID.setText("Escribe el ID del evento");
            txtSearchTel.setTooltip(tooltipID);
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            labelError.setVisible(false);
            txtSearchTel.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearchTel.getSelectionModel().getSelectedItem().equals("Nombre del evento")) {
            //el boton de busqueda estara habilitado
            btnSearchTel.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            txtSearchTel.setText("");
            //habilitar el textfield
            txtSearchTel.setDisable(false);
            //que se ponga el foco en el text field
            txtSearchTel.requestFocus();
            tooltipName.setText("Escribe el nombre del evento");
            txtSearchTel.setTooltip(tooltipName);
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            labelError.setVisible(false);
            txtSearchTel.setStyle("-fx-border-color: -fx-box-border;");
        }
    }
    
    public void searchButton(ActionEvent ev) {
        //se habilitan los botones de añadir evento, eliminar evento y busqueda de la imagen
        addTelephone.setDisable(false);
        delTelephone.setDisable(false);
       // btnImgEvent.setDisable(false);
        LOGGER.info("clickOn search button");
        //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
        labelError.setVisible(false);
        txtSearchTel.setStyle("-fx-border-color: -fx-box-border;");
        //si ha seleccionado "todos"
        if (cbSearchTel.getSelectionModel().getSelectedItem().equals("Todos los telefonos de mi txoko")) {
            telephoneData = FXCollections.observableArrayList(ilogicTelephone.findAllTelephone());
            tbTelephone.setItems(telephoneData);
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
        } else if (cbSearchTel.getSelectionModel().getSelectedItem().equals("Id del telefono")) {
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
                txtSearchTel.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el id de un evento");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }
        }//si busca por nombre
        else if (cbSearchTel.getSelectionModel().getSelectedItem().equals("Nombre del evento")) {
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
                txtSearchTel.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el nombre");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }
        }
    }

    private boolean textEmptyOrNot() {
        boolean empty = false;
        //si no esta vacio
        if (!this.txtSearchTel.getText().trim().equals("")) {
            empty = true;
        }
        return empty;
    }
    
    //para mirar si ha seleccionado la fila de la tabla
    private boolean isSelected() {
        boolean isSelected = false;
        //si es diferente a vacio
        if (!tbTelephone.getSelectionModel().getSelectedItems().isEmpty()) {
            //hay algo seleccionado
            isSelected = true;
        }
        return isSelected;
    }
}
