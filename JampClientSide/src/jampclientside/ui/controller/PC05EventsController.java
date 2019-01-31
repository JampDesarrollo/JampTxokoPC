/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import jampclientside.entity.EventBean;
import jampclientside.entity.TxokoBean;
import jampclientside.entity.UserBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.exceptions.IdNotOkException;
import jampclientside.exceptions.NameNotOkException;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
import jampclientside.logic.EventLogic;
import jampclientside.exceptions.ReadException;
import jampclientside.logic.ExpenseLogic;
import jampclientside.logic.FTPClientLogic;
import jampclientside.logic.ILogicFactory;
import jampclientside.ui.controller.PC03UserController;
import jampclientside.ui.controller.PC04ExpenseController;
import jampclientside.ui.controller.PC06FTPClientController;
import jampclientside.ui.controller.PC07ProductsController;
import jampclientside.ui.controller.PC08PhoneNumbersController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import jampclientside.logic.ProductLogic;
import jampclientside.logic.TelephoneLogic;
import jampclientside.logic.UserLogic;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Opens Events Windows, showing the data of all the events and the events of
 * our txoko
 *
 * @author paula
 */
public class PC05EventsController {

    /**
     * Attribute to appear the information text.
     */
    private static final Logger LOGGER = Logger.getLogger("package.class");
    /**
     * Attribute for the stage.
     */
    private Stage stage;
    /**
     * The business logic object containing all business methods.
     */
    private EventLogic ilogic;
    /**
     * Tooltip for a button
     */
    private Tooltip tooltip = new Tooltip();
    /**
     * Tooltip for id
     */
    private Tooltip tooltipID = new Tooltip();
    /**
     * Tooltip for the name
     */
    private Tooltip tooltipName = new Tooltip();
    /**
     * List to collect the data and then display them in the table
     */
    private ObservableList<EventBean> eventsData;
    /**
     * Principal pane of the window
     */
    @FXML
    private VBox principalPane;
    /**
     * Menu item of expense
     */
    @FXML
    private MenuItem idMenuGastos;
    /**
     * Menu item of products
     */
    @FXML
    private MenuItem idMenuProductos;
    /**
     * Menu item of users
     */
    @FXML
    private MenuItem idMenuUsuarios;
    /**
     * Menu item of phones
     */
    @FXML
    private MenuItem idMenuTel;
    /**
     * The menu bar
     */
    @FXML
    private MenuBar menuBar;
    /**
     * The menu "menu"
     */
    @FXML
    private Menu menuMenu;
    /**
     * The menu item for log out
     */
    @FXML
    private MenuItem menuLogOut;
    /**
     * the menu client ftp
     */
    @FXML
    private Menu menuFTP;
    /**
     * the menu item for client ftp
     */
    @FXML
    private MenuItem idMenuFTP;
    /**
     * the menu event
     */
    @FXML
    private Menu menuEvent;
    /**
     * the menu expense
     */
    @FXML
    private Menu menuGastos;
    /**
     * the menu products
     */
    @FXML
    private Menu menuProductos;
    /**
     * the menu user
     */
    @FXML
    private Menu menuUsuarios;
    /**
     * the menu phones
     */
    @FXML
    private Menu menuTelefonos;
    /**
     * label for the date
     */
    @FXML
    private Label lblDate;
    /**
     * label for the login
     */
    @FXML
    private Label lblLogin;
    /**
     * label fot the name
     */
    @FXML
    private Label lblFullName;
    /**
     * label for the email
     */
    @FXML
    private Label lblEmail;
    /**
     * label for the id of the txoko
     */
    @FXML
    private Label lbllTxoko;
    /**
     * text field for search
     */
    @FXML
    private TextField tfSearch;
    /**
     * button to search
     */
    @FXML
    private Button btnSearch;
    /**
     * button to assign an event to mi txoko
     */
    @FXML
    private Button btnAsignar;
    /**
     * label error
     */
    @FXML
    private Label labelError;
    /**
     * separator
     */
    @FXML
    private Separator separator;
    /**
     * button to add an event
     */
    @FXML
    private Button btnAddEvent;
    /**
     * button to delete an event
     */
    @FXML
    private Button btnDeleteEvent;
    /**
     * button to go to the ftp client window
     */
    @FXML
    private Button btnImgEvent;
    /**
     * button to log out
     */
    @FXML
    private Button btnLogOut2;
    /**
     * the table view
     */
    @FXML
    private TableView<EventBean> tableView;
    /**
     * combo box
     */
    @FXML
    private ComboBox<String> cbSearch;
    /**
     * column of the table
     */
    @FXML
    private TableColumn tbcolName;
    /**
     * column of the table
     */
    @FXML
    private TableColumn tbcolDescription;
    /**
     * column of the table
     */
    @FXML
    private TableColumn tbcolDate;
    /**
     * column of the table
     */
    @FXML
    private TableColumn tbcolImg;
    /**
     * column of the table
     */
    @FXML
    private TableColumn tbcolPrice;
    /**
     * button to do Jasper Report
     */
    @FXML
    private Button btnInforme;
    
    /**
     *
     * Method that receives the ilogic param of the class application.
     *
     * @param ILogic it receives the logic object that came from the application
     * class
     */
    public void setILogic(EventLogic iLogic) {
        this.ilogic = iLogic;
    }

    /**
     * Sets the Stage object related to this controller.
     *
     * @param stage it receives the stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * The UserBean Object
     */
    private UserBean user;
    /**
     * Set the user received in Login view for this view.
     *
     * @param user Userbean user
     */
    public void setUser(UserBean user) {
        this.user = user;

    }
    

    /**
     * Method that initializes the "Login" window. It receives the param root,
     * where is the fxml file.
     *
     * @param root receives the root parameter
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
        idMenuUsuarios.setOnAction(this::usersWindow);
        //cliente FTP
        btnImgEvent.setOnAction(this::FTPClientWindow);
        idMenuFTP.setOnAction(this::FTPClientWindow);
        //boton añadir evento
        btnAddEvent.setOnAction(this::addEvent);
        //boton eleminar evento
        btnDeleteEvent.setOnAction(this::deleteEvent);
        //dependiendo la opcion que pulse del combo box
        cbSearch.setOnAction(this::comboBoxOption);
        //boton de busqueda
        btnSearch.setOnAction(this::searchButton);
        //boton de asignar el evento a mi txoko
        btnAsignar.setOnAction(this::updateEvent);
        //boton de generar informe
        btnInforme.setOnAction(this::informeGenerate);
        //las columnas van a coger el valos de los atributos
        tbcolName.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        tbcolDescription.setCellValueFactory(
                new PropertyValueFactory<>("description"));

        tbcolDate.setCellValueFactory(
                new PropertyValueFactory<>("date"));

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
     * Method that initializes the state of the components of the window.
     *
     * @param event parameter to do an action
     */
    private void handleWindowShowing(WindowEvent event) {
        LOGGER.info("Beginning Principal::windowShow");
        
        lblDate.setText("Último acceso: " + user.getLastAccess());
        lblEmail.setText("Email: " + user.getEmail());
        lblFullName.setText("Nombre Completo: " + user.getFullname());
        lblLogin.setText("Login: " + user.getLogin());
        lbllTxoko.setText("Txoko: " + user.getTxoko().getName());

        cbSearch.getItems().removeAll(cbSearch.getItems());
        cbSearch.getItems().addAll("Todos los eventos", "Todos los eventos de mi txoko", "Id del evento", "Nombre del evento");
        //por defecto aparezca la primera opcion seleccionada
        cbSearch.getSelectionModel().select(0);
        btnSearch.setDisable(false);
        btnSearch.requestFocus();
        labelError.setVisible(false);
        btnInforme.setDisable(true);
        tfSearch.setDisable(true);
        tableView.setEditable(false);
        btnAddEvent.setDisable(true);
        btnDeleteEvent.setDisable(true);
        btnAsignar.setDisable(true);
        btnImgEvent.setDisable(true);
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
    /**
     * Method to go to the products window
     *
     * @param ev event
     */
    public void productsWindow(ActionEvent ev) {
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
     * Method to go to telephone window
     *
     * @param ev event
     */
    //ir a la ventana de los telefonos
    public void telephoneWindow(ActionEvent ev) {
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
     * Method to go to the expense window
     *
     * @param ev event
     */
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
     * Method to go to the users window
     *
     * @param ev event
     */
    //ventana de los usuarios
    public void usersWindow(ActionEvent ev) {
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
     * Method to go to FTP Client window
     *
     * @param ev event
     */
    //ventana del cliente FTP
    public void FTPClientWindow(ActionEvent ev) {
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
     * Method to go to the add event window
     *
     * @param ev event
     */
    //añadir un evento a la table eventos
    public void addEvent(ActionEvent ev) {
        LOGGER.info("clickOn addevent");
        try {
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/addEvent.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //tengo que crear un nuevo escenario
            // stage = new Stage();
            //obtener el controlador
            AddEventController controller = (AddEventController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(ilogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            //que aparezca como al principio
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    /**
     * Method to update an event
     *
     * @param ev event
     */
    //asignarle un txoko al evento
    public void updateEvent(ActionEvent ev) {
        LOGGER.log(Level.INFO,
                "UPDATE EVENT");
        //mirar si la fila esta seleccionada
        boolean isSelected = isSelected();
        if (isSelected) {
            try {
                String idTxoko = "1";
                //Obtenemos los datos de la fila seleccionada 
                EventBean selectedEvent = ((EventBean) tableView.getSelectionModel().getSelectedItem());
                eventsData = FXCollections.observableArrayList(ilogic.findAllEvents(idTxoko));
                List<TxokoBean> txoko = new ArrayList<TxokoBean>();
                TxokoBean aux = new TxokoBean();
                aux.setIdTxoko(Integer.parseInt(idTxoko));
                txoko.add(aux);
                selectedEvent.setTxokos(txoko);
                this.ilogic.updateEvent(selectedEvent);
                //Deseleccionamos la fila seleccionada en la tabla
                tableView.getSelectionModel().clearSelection();
                //Refrescamos la tabla para que muestre los nuevos datos
                tableView.refresh();

            } catch (BusinessLogicException e) {
                Alert dialogoAlerta = new Alert(AlertType.ERROR);
                dialogoAlerta.setTitle("ERROR");
                dialogoAlerta.setContentText("Error a la hora asignar un txoko al evento");
                dialogoAlerta.setHeaderText("Asignar un evento");
                dialogoAlerta.showAndWait();
                LOGGER.log(Level.SEVERE,
                        " Error updating event: {0}",
                        e.getMessage());
            }
        } else {
            Alert dialogoAlerta = new Alert(AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("Tienes que seleccionar una fila!!");
            dialogoAlerta.setHeaderText("Asignar un evento a mi txoko");
            dialogoAlerta.showAndWait();
        }
    }

    /**
     * Method to delete an event
     *
     * @param ev event
     */
    //eliminar evento
    public void deleteEvent(ActionEvent ev) {
        LOGGER.info("clickOn delete event");
        //mirar si la fila esta seleccionada
        boolean isSelected = isSelected();
        if (isSelected) {
            try {  //Vamos a coger todos los datos de la fila que ha seleccionado
                EventBean selectedEvent = ((EventBean) tableView.getSelectionModel().getSelectedItem());
                //Cuadro de confirmacion de borrado de la fila y el dato
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
                    //eliminar de la base de datos y eliminar la fila
                    this.ilogic.deleteEvent(selectedEvent);
                    tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
                    tableView.refresh();
                }
            } catch (BusinessLogicException e) {
                Alert dialogoAlerta = new Alert(AlertType.ERROR);
                dialogoAlerta.setTitle("ERROR");
                dialogoAlerta.setContentText("Error a la hora de eliminar un evento");
                dialogoAlerta.setHeaderText("Eliminar un evento");
                dialogoAlerta.showAndWait();
                LOGGER.log(Level.SEVERE,
                        " Error deleting event: {0}",
                        e.getMessage());
            }
        } else {

            Alert dialogoAlerta = new Alert(AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("Tienes que seleccionar una fila!!");
            dialogoAlerta.setHeaderText("Eliminar un evento");
            dialogoAlerta.showAndWait();
        }
    }

    /**
     *
     * Method to know if the row is selected or not
     *
     * @return it returns a boolean indicating if there is a row selected
     */
    //para mirar si ha seleccionado la fila de la tabla
    private boolean isSelected() {
        LOGGER.info("Mirando si la fila esta seleccionada o no");
        boolean isSelected = false;
        //si es diferente a vacio
        if (!tableView.getSelectionModel().getSelectedItems().isEmpty()) {
            //hay algo seleccionado
            isSelected = true;
        }
        return isSelected;
    }

    /**
     *
     * Depending on the option that you choose in the combo box does one thing
     * or another
     *
     * @param ev event
     */
    //dependiendo la opcion del combo box que haya elegido, el textfield estara activado o no
    public void comboBoxOption(ActionEvent ev) {
        LOGGER.info("clickOn combo box");
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los eventos")) {
            //si anteriormente hay algun texto, quitarlo
            tfSearch.setText("");
            //deshabilitar el textfield
            tfSearch.setDisable(true);
            btnSearch.requestFocus();
            //que se quite lo rojo si anteriormente habia SELECCIONADO algo y daba error
            //para que en el momento de seleccion en el combobox no haya nada en rojo
            labelError.setVisible(false);
            tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los eventos de mi txoko")) {
            //si anteriormente hay algun texto, quitarlo
            tfSearch.setText("");
            //deshabilitar el textfield
            tfSearch.setDisable(true);
            btnSearch.requestFocus();
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            //para que en el momento de seleccion en el combobox no haya nada en rojo
            labelError.setVisible(false);
            tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del evento")) {
            //si anteriormente hay algun texto, quitarlo
            tfSearch.setText("");
            //habilitar el textfield
            tfSearch.setDisable(false);
            //que se ponga el foco en el text field
            tfSearch.requestFocus();
            tooltipID.setText("Escribe el ID del evento");
            tfSearch.setTooltip(tooltipID);
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            //para que en el momento de seleccion en el combobox no haya nada en rojo
            labelError.setVisible(false);
            tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Nombre del evento")) {
            //si anteriormente hay algun texto, quitarlo
            tfSearch.setText("");
            //habilitar el textfield
            tfSearch.setDisable(false);
            //que se ponga el foco en el text field
            tfSearch.requestFocus();
            tooltipName.setText("Escribe el nombre del evento");
            tfSearch.setTooltip(tooltipName);
            //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
            //para que en el momento de seleccion en el combobox no haya nada en rojo
            labelError.setVisible(false);
            tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        }
    }

    /**
     * When you click on the search button,the behavior of the program
     *
     * @param ev event
     */
    //buscar evento
    public void searchButton(ActionEvent ev) {
        LOGGER.info("clickOn search button");
        //que se quite lo rojo si anteriormente habia seleccionado algo y daba error
        labelError.setVisible(false);
        tfSearch.setStyle("-fx-border-color: -fx-box-border;");
        //si ha seleccionado "todos"
        if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los eventos")) {
            btnAddEvent.setDisable(false);
            btnAsignar.setDisable(false);
            btnDeleteEvent.setDisable(true);
            btnImgEvent.setDisable(true);
            btnInforme.setDisable(true);
            eventsData = null;
            try {
                eventsData = FXCollections.observableArrayList(ilogic.getAllEvents());
                tableView.setItems(eventsData);
                if (eventsData.size() == 0) {
                    Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAlerta.setTitle("INFORMACION");
                    dialogoAlerta.setHeaderText("No hay ningun evento");
                    dialogoAlerta.showAndWait();
                    btnDeleteEvent.setDisable(true);
                    btnImgEvent.setDisable(true);
                    btnInforme.setDisable(true);
                } else {
                    //si ha devuelto algun evento se habilitarán los botones
                    btnInforme.setDisable(false);
                    btnDeleteEvent.setDisable(false);
                    btnImgEvent.setDisable(false);
                }
            } catch (BusinessLogicException ex) {
                eventsData = null;
                tableView.setItems(eventsData);
                Alert dialogoAlerta = new Alert(AlertType.ERROR);
                dialogoAlerta.setTitle("ERROR");
                dialogoAlerta.setContentText("HTTP 400 Bad Request");
                dialogoAlerta.setHeaderText("Ver los datos");
                dialogoAlerta.showAndWait();
                LOGGER.log(Level.SEVERE,
                        " Error reading all events {0}",
                        ex.getMessage());
            }
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Todos los eventos de mi txoko")) {
            eventsData = null;
            btnAddEvent.setDisable(true);
            btnAsignar.setDisable(true);
            btnDeleteEvent.setDisable(true);
            btnImgEvent.setDisable(true);
            btnInforme.setDisable(true);
            String idTxoko = "1";
            try {
                eventsData = FXCollections.observableArrayList(ilogic.findAllEvents(idTxoko));
                tableView.setItems(eventsData);
                //si ese txoko no tiene ningun evento hay que avisarle al usuario
                if (eventsData.size() == 0) {
                    Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                    dialogoAlerta.setTitle("INFORMACION");
                    dialogoAlerta.setHeaderText("No hay ningun evento en el txoko");
                    dialogoAlerta.showAndWait();
                    btnDeleteEvent.setDisable(true);
                    btnImgEvent.setDisable(true);
                } else {
                    //si ha devuelto algun evento se habilitarán los botones
                    btnDeleteEvent.setDisable(false);
                    btnImgEvent.setDisable(false);
                }
            } catch (BusinessLogicException ex) {
                eventsData = null;
                tableView.setItems(eventsData);
                Alert dialogoAlerta = new Alert(AlertType.ERROR);
                dialogoAlerta.setTitle("ERROR");
                dialogoAlerta.setContentText("HTTP 400 Bad Request");
                dialogoAlerta.setHeaderText("Ver los datos");
                dialogoAlerta.showAndWait();
                LOGGER.log(Level.SEVERE,
                        " Error reading events of my txoko: {0}",
                        ex.getMessage());
            }
        } else if (cbSearch.getSelectionModel().getSelectedItem().equals("Id del evento")) {
            //miramos si el textfield esta vacio o no
            boolean tfIDEmpty = textEmptyOrNot();
            btnAddEvent.setDisable(true);
            btnAsignar.setDisable(true);
            btnDeleteEvent.setDisable(true);
            btnImgEvent.setDisable(true);
            btnInforme.setDisable(true);
            //si no esta vacio
            if (tfIDEmpty) {
                String idTxoko = "1";
                try {
                    eventsData = FXCollections.observableArrayList(ilogic.findEventByIdByTxoko(tfSearch.getText().trim(), idTxoko));
                    tableView.setItems(eventsData);
                    btnDeleteEvent.setDisable(false);
                    btnImgEvent.setDisable(false);
                } catch (BusinessLogicException ex) {
                    eventsData = null;
                    tableView.setItems(eventsData);
                    Alert dialogoAlerta = new Alert(AlertType.ERROR);
                    dialogoAlerta.setTitle("ERROR");
                    dialogoAlerta.setContentText(" HTTP 400 Bad Request");
                    dialogoAlerta.setHeaderText("Ver los datos");
                    dialogoAlerta.showAndWait();
                    btnDeleteEvent.setDisable(true);
                    btnImgEvent.setDisable(true);
                } catch (IdNotOkException e) {
                    eventsData = null;
                    tableView.setItems(eventsData);
                    LOGGER.log(Level.SEVERE,
                            " Error reading event BY ID: {0}",
                            e.getMessage());
                    btnDeleteEvent.setDisable(true);
                    btnImgEvent.setDisable(true);
                    tfSearch.setStyle("-fx-border-color: red;");
                    labelError.setText("ID del evento incorrecto");
                    labelError.setVisible(true);
                }

            } //si esta vecio
            else {
                eventsData = null;
                tableView.setItems(eventsData);
                btnDeleteEvent.setDisable(true);
                btnImgEvent.setDisable(true);
                tfSearch.setStyle("-fx-border-color: red;");
                labelError.setText("Campo requerido");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }
        }//si busca por nombre
        else if (cbSearch.getSelectionModel().getSelectedItem().equals("Nombre del evento")) {
            //miramos si el text field esta vacio
            boolean tfNameEmpty = textEmptyOrNot();
            btnAddEvent.setDisable(true);
            btnAsignar.setDisable(true);
            btnDeleteEvent.setDisable(true);
            btnImgEvent.setDisable(true);
            btnInforme.setDisable(true);
            //si no esta vacio
            if (tfNameEmpty) {
                String idTxoko = "1";
                try {
                    eventsData = FXCollections.observableArrayList(ilogic.findEventByName(tfSearch.getText().trim(), idTxoko));
                    tableView.setItems(eventsData);
                    //si no hay ningun problema los botones de eliminar y arhivos se habilitan
                    btnDeleteEvent.setDisable(false);
                    btnImgEvent.setDisable(false);
                } catch (BusinessLogicException ex) {
                    eventsData = null;
                    tableView.setItems(eventsData);
                    Alert dialogoAlerta = new Alert(AlertType.ERROR);
                    dialogoAlerta.setTitle("ERROR");
                    dialogoAlerta.setContentText(" HTTP 400 Bad Request");
                    dialogoAlerta.setHeaderText("Ver los datos");
                    dialogoAlerta.showAndWait();
                    LOGGER.log(Level.SEVERE,
                            " Error reading event BY NAME: {0}",
                            ex.getMessage());
                } catch (NameNotOkException e) {
                    //la tabla aparece sin ningun contenido
                    eventsData = null;
                    tableView.setItems(eventsData);
                    btnDeleteEvent.setDisable(true);
                    btnImgEvent.setDisable(true);
                    LOGGER.log(Level.SEVERE,
                            " Error reading event BY NAME: {0}",
                            e.getMessage());
                    tfSearch.setStyle("-fx-border-color: red;");
                    labelError.setText("Nombre del evento incorrecto");
                    labelError.setVisible(true);
                    labelError.setStyle("-fx-text-inner-color: red;");
                }
            }//si esta vacio
            else {
                eventsData = null;
                tableView.setItems(eventsData);
                btnDeleteEvent.setDisable(true);
                btnImgEvent.setDisable(true);
                tfSearch.setStyle("-fx-border-color: red;");
                labelError.setText("Campo requerido");
                labelError.setVisible(true);
                labelError.setStyle("-fx-text-inner-color: red;");
            }

        }

    }

    /**
     * Method to know if the text field is empty or not
     *
     * @return it returns a boolean indicating if the text field is empty
     */
    //miramos si el textfield esta vacio o no cuando ha seleccionado ID o NOMBRE
    private boolean textEmptyOrNot() {
        LOGGER.info("Mirando si el text field esta vacio");
        boolean empty = false;
        //si no esta vacio
        if (!this.tfSearch.getText().trim().equals("")) {
            empty = true;
        }
        return empty;
    }
    /**
     * 
     *Method to open the report window
     * @param ev event
     */
    public void informeGenerate(ActionEvent ev) {
        try {
            JasperReport report
                    = JasperCompileManager.compileReport(getClass()
                            .getResourceAsStream("/jampclientside/ui/reports/newReportEvent.jrxml"));
            //Data for the report: a collection of UserBean passed as a JRDataSource 
            //implementation 
            JRBeanCollectionDataSource dataItems
                    = new JRBeanCollectionDataSource((Collection<EventBean>) this.tableView.getItems());
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
            Alert dialogoAlerta = new Alert(AlertType.ERROR);
            dialogoAlerta.setTitle("ERROR");
            dialogoAlerta.setContentText("Error a la hora de hacer el informe");
            dialogoAlerta.setHeaderText("Asignar un evento");
            dialogoAlerta.showAndWait();
            LOGGER.log(Level.SEVERE,
                    " Error updating event: {0}",
                    ex.getMessage());

        }
    }
}
