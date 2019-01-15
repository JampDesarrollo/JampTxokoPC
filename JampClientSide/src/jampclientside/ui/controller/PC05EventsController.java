/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.EventBean;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import messageuserbean.UserBean;
import jampclientside.logic.EventLogic;
import jampclientside.exceptions.IdNotOkException;
import jampclientside.exceptions.ReadException;
import jampclientside.logic.ExpenseLogic;
import jampclientside.logic.ILogicFactory;
import jampclientside.logic.ILogicProduct;
import jampclientside.logic.ILogicTelephone;
import jampclientside.ui.controller.PC03UserController;
import jampclientside.ui.controller.PC04ExpenseController;
import jampclientside.ui.controller.PC06FTPClientController;
import jampclientside.ui.controller.PC07ProductsController;
import jampclientside.ui.controller.PC08PhoneNumberController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller class
 *
 * @author paula
 */
public class PC05EventsController {

    private static final Logger LOGGER = Logger.getLogger("package.class");
    private Stage stage;
    private EventLogic ilogic;
    private UserBean user;
    private Tooltip tooltip = new Tooltip();
    private Tooltip tooltipID = new Tooltip();
    private Tooltip tooltipName = new Tooltip();
    @FXML
    private VBox principalPane;
    @FXML
    private MenuItem idMenuGastos;
    @FXML
    private MenuItem idMenuProductos;
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
    private TextField tfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Label labelError;
    @FXML
    private Separator separator;
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button btnDeleteEvent;
    @FXML
    private Button btnImgEvent;
    @FXML
    private Button btnLogOut2;
    @FXML
    private TableView<EventBean> tableView;
    @FXML
    private ComboBox<String> cbSearch;
    @FXML
    private TableColumn tbcolName;
    @FXML
    private TableColumn tbcolDescription;
    @FXML
    private TableColumn tbcolDate;
    @FXML
    private TableColumn tbcolImg;
    @FXML
    private TableColumn tbcolPrice;
    @FXML
    private DatePicker datePicker;
    private ObservableList<EventBean> eventsData;

    /**
     * Initializes the controller class.
     */
    public void setILogic(EventLogic iLogic) {
        this.ilogic = iLogic;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

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
        stage.setTitle("Eventos del Txoko");
        //Set window's events handlers
        stage.setOnShowing(this::handleWindowShowing);
        //menu item de cerrar sesion
        menuLogOut.setOnAction(this::logOutAction);
        //boton de cerrar sesion
        btnLogOut2.setOnAction(this::logOutAction);
        //ir a la ventana de productos
        idMenuProductos.setOnAction(this::productsWindow);
        //ir a la ventana de telefonos
        idMenuTel.setOnAction(this::telephoneWindow);
        //gastos
        idMenuGastos.setOnAction(this::expenseWindow);
        //ventana de los usuarios
//        idMenuUsuarios.setOnAction(this::usersWindow);
        //cliente FTP
//        btnImgEvent.setOnAction(this::FTPClientWindow);
        //boton añadir evento
        btnAddEvent.setOnAction(this::addEvent);
        //boton eleminar evento
        btnDeleteEvent.setOnAction(this::deleteEvent);
        //dependiendo la opcion que pulse del combo box
        cbSearch.setOnAction(this::comboBoxOption);
        //boton de busqueda
        btnSearch.setOnAction(this::searchButton);
        //las columnas van a coger el valos de los atributos
        tbcolName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        tbcolDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));
        /*tbcolDate.setCellValueFactory(
                    new PropertyValueFactory<>("date"));*/
        tbcolImg.setCellValueFactory(
                new PropertyValueFactory<>("img"));
        tbcolPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));

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
    private void handleWindowShowing(WindowEvent event) {
        LOGGER.info("Beginning Principal::windowShow");
        /*   
        String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(user.getLastAccess());
        lblDate.setText("Último acceso: " + date);
        lblEmail.setText("Email: " + user.getEmail());
        lblFullName.setText("Nombre Completo: " + user.getFullname());
        lblLogin.setText("Login: " + user.getLogin());
        lbllTxoko.setText("Txoko: "+user.getTxoko());
         */

        cbSearch.getItems().removeAll(cbSearch.getItems());
        cbSearch.getItems().addAll("Todos los eventos de mi txoko", "Id del evento", "Nombre del evento");
        labelError.setVisible(false);
        cbSearch.requestFocus();
        tfSearch.setDisable(true);
        tableView.setEditable(true);
        btnSearch.setDisable(true);
        btnAddEvent.setDisable(true);
        btnDeleteEvent.setDisable(true);
        btnImgEvent.setDisable(true);
        datePicker.setDisable(true);
        tooltip.setText("Para ver la imagen del evento");
        btnImgEvent.setTooltip(tooltip);
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
    //cuando pulse en el boton de cerrar sesion que aparezca un alert
    public void logOutAction(ActionEvent event) {
        LOGGER.info("Beginning Principal::logout action");
        int cerrar = 2;
        cerrarSesionAlert(cerrar);
    }

    /**
     * Method that show a confirm dialog to close session
     *
     * @param cerrar Difference for close app or close session
     */
    //el alert de cerrar sesion
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

    public void productsWindow(ActionEvent ev) {
        LOGGER.info("clickOn Products Menu");
        try {
             //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            ILogicProduct productLogic = ILogicFactory.getProductLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC07Products.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC07ProductsController controller = (PC07ProductsController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogicProduct(productLogic);
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
            //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            ILogicTelephone telephoneLogic = ILogicFactory.getTelephoneLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC08PhoneNumber.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC08PhoneNumberController controller = (PC08PhoneNumberController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(telephoneLogic);
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
            //vamos a cargar un objeto de la logica , para eso llamamos a la factoria 
            ExpenseLogic expenseLogic = ILogicFactory.getExpenseLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC04Expense.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC04ExpenseController controller = (PC04ExpenseController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(expenseLogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearch.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }/*

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
            controller.setILogic(ilogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearch.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    //ventana del cliente FTP
    public void FTPClientWindow(ActionEvent ev) {
        LOGGER.info("clickOn FTP Client btn");
        try {
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC06FTPClient.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC06FTPClientController controller = (PC06FTPClientController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(ilogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            cbSearch.requestFocus();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }*/

    //añadir evento
    public void addEvent(ActionEvent ev) {
        Alert dialogoAlerta = new Alert(AlertType.CONFIRMATION);
        dialogoAlerta.setTitle("CONFIRMACION");
        dialogoAlerta.setContentText("¿Estas seguro que deseas añadir un evento?");
        dialogoAlerta.setHeaderText("Añadir un evento");
        Optional<ButtonType> result = dialogoAlerta.showAndWait();
        Button okButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("buttonAdd");
        Button cancelButton = (Button) dialogoAlerta.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("buttonCancel");
        //si da en el buttonAdd que se añada una fila
        if (result.get() == ButtonType.OK) {
            //se habilita el date picker
            datePicker.setDisable(false);
            //añadir una fila a la tabla
            //el txoko se puede coger del label o de la base de datos
            //añadir a la base de datos y eliminar la fila 
            EventBean event = new EventBean();
            //añadir fila en blanco
            tableView.getItems().add(null);
            tbcolName.setCellValueFactory(
                    new PropertyValueFactory<>("name"));
            tbcolName.setCellFactory(TextFieldTableCell.forTableColumn());
           
            tbcolDescription.setCellValueFactory(
                    new PropertyValueFactory<>("description"));
            tbcolDescription.setCellFactory(TextFieldTableCell.forTableColumn());
            /*  tbcolDate.setCellValueFactory(
                    new PropertyValueFactory<>("date"));
            tbcolDate.setCellFactory(TextFieldTableCell.forTableColumn());*/
            tbcolImg.setCellValueFactory(
                    new PropertyValueFactory<>("img"));
            tbcolImg.setCellFactory(TextFieldTableCell.forTableColumn());
            /*tbcolPrice.setCellValueFactory(
                    new PropertyValueFactory<>("price"));
            tbcolPrice.setCellFactory(TextFieldTableCell.forTableColumn());*/
            Alert dialog = new Alert(AlertType.CONFIRMATION);
            dialog.setTitle("CONFIRMACION");
            dialog.setContentText("El nuevo evento va a ser añadido");
            dialog.setHeaderText("Añadir un evento");
            Optional<ButtonType> resultado = dialog.showAndWait();
            Button aceptar = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            aceptar.setId("aceptar");

        }

    }

    //eliminar evento
    public void deleteEvent(ActionEvent ev) {
        boolean isSelected = isSelected();
        if (isSelected) {
            Alert dialogoAlerta = new Alert(AlertType.CONFIRMATION);
            dialogoAlerta.setTitle("CONFIRMACION");
            dialogoAlerta.setContentText("¿Estas seguro que deseas eliminar un evento?");
            dialogoAlerta.setHeaderText("Eliminar un evento");
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
                tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                tableView.refresh();
            }
        } else {

            Alert dialogoAlerta = new Alert(AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("Tienes que seleccionar una fila!!");
            dialogoAlerta.setHeaderText("Eliminar un evento");
            dialogoAlerta.showAndWait();
        }
    }

    //para mirar si ha seleccionado la fila de la tabla
    private boolean isSelected() {
        boolean isSelected = false;
        //si es diferente a vacio
        if (!tableView.getSelectionModel().getSelectedItems().isEmpty()) {
            //hay algo seleccionado
            isSelected = true;
        }
        return isSelected;
    }
    //dependiendo la opcion del combo box que haya elegido, el textfield estara activado o no

    public void comboBoxOption(ActionEvent ev) {
        LOGGER.info("clickOn combo box");
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los eventos de mi txoko")) {
            //el boton de busqueda estara habilitado
            btnSearch.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            tfSearch.setText("");
            //deshabilitar el textfield
            tfSearch.setDisable(true);
            btnSearch.requestFocus();
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            labelError.setVisible(false);
            tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del evento")) {
            //el boton de busqueda estara habilitado
            btnSearch.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            tfSearch.setText("");
            //habilitar el textfield
            tfSearch.setDisable(false);
            //que se ponga el foco en el text field
            tfSearch.requestFocus();
            tooltipID.setText("Escribe el ID del evento");
            tfSearch.setTooltip(tooltipID);
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            labelError.setVisible(false);
            tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Nombre del evento")) {
            //el boton de busqueda estara habilitado
            btnSearch.setDisable(false);
            //si anteriormente hay algun texto, quitarlo
            tfSearch.setText("");
            //habilitar el textfield
            tfSearch.setDisable(false);
            //que se ponga el foco en el text field
            tfSearch.requestFocus();
            tooltipName.setText("Escribe el nombre del evento");
            tfSearch.setTooltip(tooltipName);
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            labelError.setVisible(false);
            tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        }
    }

    //buscar evento
    public void searchButton(ActionEvent ev) {
        //se habilitan los botones de añadir evento, eliminar evento y busqueda de la imagen
        btnAddEvent.setDisable(false);
        btnDeleteEvent.setDisable(false);
        btnImgEvent.setDisable(false);
        LOGGER.info("clickOn search button");
        //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
        labelError.setVisible(false);
        tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        //si ha seleccionado "todos"
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los eventos de mi txoko")) {
            eventsData = FXCollections.observableArrayList(ilogic.getAllEvents());
            tableView.setItems(eventsData);
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
                tfSearch.setStyle("-fx-border-color: red;");
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
                tfSearch.setStyle("-fx-border-color: red;");
                labelError.setText("Tienes que escribir el nombre");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }

        }

    }

    //sacamos la lista de todos los eventos de mi txoko
    /*  
    public List<EventBean> events() {
        List<EventBean> events=null;
        try{
          
        }catch(ReadException e){
             LOGGER.log(Level.SEVERE, "Error{0}", e.getCause());
        }       
        return events;
    }    
     */
    //miramos si el textfield esta vacio o no cuando ha seleccionado ID o NOMBRE
    private boolean textEmptyOrNot() {
        boolean empty = false;
        //si no esta vacio
        if (!this.tfSearch.getText().trim().equals("")) {
            empty = true;
        }
        return empty;
    }

    //mirar si ese id existe o no
    /*
    private EventBean eventIDExist() {
        EventBean returnEvent = null;
        try {         
          
            EventBean evento = new EventBean(tfSearch.getText(),lblTxoko.getText());
            
          returnEvent = ilogic.findEventById(evento);
        } catch (IdNotOkException e) {
            LOGGER.log(Level.SEVERE, "Id not exist exception {0}", e.getCause());
            //se pone el foco en el boton
            btnSearch.requestFocus();
            //que este en rojo
            tfSearch.setStyle("-fx-border-color: red;");
            //que el texto sea visible 
            labelError.setText("Id incorrecto");
            labelError.setVisible(true);
            labelError.setStyle("-fx-text-inner-color: red;");
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "Error {0}", e.getCause());
            labelError.setText("Error al conectar con la base de datos");
            labelError.setStyle("-fx-text-inner-color: red;");
            labelError.setVisible(true);
        }
        return returnEvent;
    }
     */
    //miramos si ese nombre existe
    /*
    private EventBean eventNameExist() {
        EventBean returnEvent = null;
        try {
          
          EventBean evento = new EventBean(tfSearch.getText(), lbllTxoko.getText());
          returnEvent = ilogic.findEventByName(evento);
        } catch (NameNotOkException e) {
            LOGGER.log(Level.SEVERE, "Id not exist exception {0}", e.getCause());
            //se pone el foco en el boton
            btnSearch.requestFocus();
            //que este en rojo
            tfSearch.setStyle("-fx-border-color: red;");
            //que el texto sea visible 
            labelError.setText("Nombre incorrecto");
            labelError.setVisible(true);
            labelError.setStyle("-fx-text-inner-color: red;");
        } catch (ReadException e) {
            LOGGER.log(Level.SEVERE, "Error {0}", e.getCause());
            labelError.setText("Error al conectar con la base de datos");
            labelError.setStyle("-fx-text-inner-color: red;");
            labelError.setVisible(true);
        }
        return returnEvent;
    }
     */
}
