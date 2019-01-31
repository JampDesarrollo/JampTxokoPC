/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.UserBean;
import jampclientside.entity.UserPrivilege;
import jampclientside.entity.UserStatus;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.exceptions.ReadException;
import jampclientside.logic.EventLogic;
import jampclientside.logic.ExpenseLogic;
import jampclientside.logic.FTPClientLogic;
import jampclientside.logic.ILogicFactory;
import jampclientside.logic.ProductLogic;
import jampclientside.logic.TelephoneLogic;
import jampclientside.logic.UserLogic;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class for User management view.
 *
 * @author Ander
 */
public class PC03UserController {

    /**
     * Menu.
     */
    @FXML
    private Menu menu;
    /**
     * Menu item to log out f the app.
     */
    @FXML
    private MenuItem btnLogOut;
    /**
     * Menu item to go Events view.
     */
    @FXML
    private MenuItem btnEvents;
    /**
     * Menu item to go Expenses view.
     */
    @FXML
    private MenuItem btnExpenses;
    /**
     * Menu item to go Products view.
     */
    @FXML
    private MenuItem btnProducts;
    /**
     * Menu item to go Users view.
     */
    @FXML
    private MenuItem btnUsers;
    /**
     * Menu item to go Telephone numbers view.
     */
    @FXML
    private MenuItem btnPhones;
    /**
     * Menu item to go FTP client view.
     */
    @FXML
    private MenuItem btnArchivos;
    /**
     * Label last log in date.
     */
    @FXML
    private Label lblDate;
    /**
     *
     */
    @FXML
    private Label lblLogin;
    /**
     * Label full name.
     */
    @FXML
    private Label lblFullName;
    /**
     * label email.
     */
    @FXML
    private Label lblEmail;
    /**
     * Label txoko.
     */
    @FXML
    private Label lblTxoko;
    /**
     * Label error.
     */
    @FXML
    private Label lblErrorUser;
    /**
     * Button deleteUser.
     */
    @FXML
    private Button btnDeleteUser;
    /**
     * Button edit user.
     */
    @FXML
    private Button btnEditUser;
    /**
     * Button log out.
     */
    @FXML
    private Button btnLogOut2;
    /**
     * Button log out.
     */
    @FXML
    private Button btnPrint;
    /**
     * TableView of UserBean.
     */
    @FXML
    private TableView tabUsers;
    /**
     * Column for idUser.
     */
    @FXML
    private TableColumn colIdUser;
    /**
     * Column for Login.
     */
    @FXML
    private TableColumn colLogin;
    /**
     * Column for email.
     */
    @FXML
    private TableColumn colEmail;
    /**
     * Column for full name.
     */
    @FXML
    private TableColumn<UserBean, String> colNameSur;
    /**
     * Column for status.
     */
    @FXML
    private TableColumn<UserBean, UserStatus> colStatus;
    /**
     * Column for privilege.
     */
    @FXML
    private TableColumn<UserBean, UserPrivilege> colPriv;
    /**
     * ObservableList of UserBeans.
     */
    private ObservableList<UserBean> usersData;
    /**
     * To close app or session.
     */
    private int cerrar;

    /**
     * The business logic object containing all business methods for users.
     */
    private UserLogic userLogic;

    /**
     * UserBean object.
     */
    private UserBean user;

    /**
     * Logger object used to log messages for application.
     */
    private static final Logger LOGGER = Logger.getLogger("jampclientside.ui.controller");
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
     * @param iLogic
     */
    public void setILogic(UserLogic iLogic) {
        this.userLogic = iLogic;

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
        LOGGER.info("Initializing Principal stage.");
        //Create a scene associated to the node graph root.
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //Associate scene to primaryStage(Window)
        stage.setScene(scene);
        stage.setResizable(false);
        //Set window properties
        stage.setTitle("Usuarios");
        //Set window's events handlers
        stage.setOnShowing(this::windowShow);

        btnLogOut.setOnAction(this::logOutAction);
        btnLogOut2.setOnAction(this::logOutAction);
        btnEvents.setOnAction(this::goEvents);
        btnExpenses.setOnAction(this::goExpenses);
        btnProducts.setOnAction(this::goProducts);
        btnUsers.setOnAction(this::goUsers);
        btnPhones.setOnAction(this::goPhones);
        btnArchivos.setOnAction(this::goFTP);
        btnDeleteUser.setOnAction(this::deleteUser);
        btnEditUser.setOnAction(this::updateUser);
        btnPrint.setOnAction(this::printAction);
        tabUsers.getSelectionModel().selectedItemProperty()
                .addListener(this::handleUsersTableSelection);
        lblErrorUser.setVisible(false);
        //Show primary window
        stage.setOnCloseRequest((WindowEvent e) -> {
            cerrar = 1;
            e.consume();
            cerrarSesionAlert(cerrar);

        });

        tabUsers.setEditable(true);
        colNameSur.setEditable(true);
        colIdUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNameSur.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        colNameSur.setCellFactory(TextFieldTableCell.<UserBean>forTableColumn());
        colNameSur.setOnEditCommit(
                (CellEditEvent<UserBean, String> t) -> {
                    ((UserBean) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setFullname(t.getNewValue());
                });
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setCellFactory(ChoiceBoxTableCell.forTableColumn(UserStatus.ENABLED, UserStatus.DISABLED));
        colStatus.setOnEditCommit(
                (CellEditEvent<UserBean, UserStatus> t) -> {
                    ((UserBean) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setStatus(t.getNewValue());
                });
        colPriv.setCellValueFactory(new PropertyValueFactory<>("privilege"));
        colPriv.setCellFactory(ChoiceBoxTableCell.forTableColumn(UserPrivilege.ADMIN, UserPrivilege.USER));
        colPriv.setOnEditCommit(
                (CellEditEvent<UserBean, UserPrivilege> t) -> {
                    ((UserBean) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPrivilege(t.getNewValue());
                });
        try {
            usersData = FXCollections.observableArrayList(userLogic.findAllUsers(user.getTxoko().getIdTxoko()));
        } catch (BusinessLogicException ex) {
            LOGGER.log(Level.SEVERE, "UserController: Exception reading all users:",
                    ex.getMessage());
        }
        tabUsers.setItems(usersData);

        stage.show();
    }

    /**
     * Initializes the window when shown.
     *
     * @param event WindowEvent event
     */
    private void windowShow(WindowEvent event) {
        LOGGER.info("Beginning Principal::windowShow");

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
        btnDeleteUser.setDisable(true);
        btnEditUser.setDisable(true);
    }

    /**
     * Close current view and open Login view method.
     *
     * @param event Action Event
     */
    private void logOutAction(ActionEvent event) {
        LOGGER.info("Beginning Principal::logout action");
        cerrar = 2;
        cerrarSesionAlert(cerrar);
    }

    /**
     * Method that show a confirm dialog to close session
     *
     * @param cerrar Difference for close app or close session
     */
    private void cerrarSesionAlert(int cerrar) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dialogo de confirmacion");
        alert.setContentText("¿Estas seguro que deseas cerrar la sesión?");
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
     * Handle when a tableview's itemis selected.
     *
     * @param observable observable
     * @param oldValue oldvalue
     * @param newValue newbvalue
     */
    private void handleUsersTableSelection(ObservableValue observable,
            Object oldValue,
            Object newValue) {
        if (newValue != null) {
            btnDeleteUser.setDisable(false);
            btnEditUser.setDisable(false);
        } else {
            btnDeleteUser.setDisable(true);
            btnEditUser.setDisable(true);
        }

    }

    /**
     * Method that deletes the selected user from the database and the table.
     *
     * @param event event.
     */
    private void deleteUser(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dialogo de confirmacion");
        alert.setContentText("¿Estas seguro que deseas borrar el usuario?");
        alert.setHeaderText("Borrar usuario");

        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("okButton");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("cancelButton");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                //ALERT
                UserBean selectedUser = (UserBean) tabUsers.getSelectionModel().getSelectedItem();
                userLogic.deleteUser(selectedUser.getIdUser());
                tabUsers.getItems().remove(tabUsers.getSelectionModel().getSelectedItem());
                tabUsers.refresh();
                lblErrorUser.setText("Usuario eliminado");
                lblErrorUser.setTextFill(Color.GREEN);
                lblErrorUser.setVisible(true);
            } catch (BusinessLogicException ex) {
                LOGGER.log(Level.SEVERE, "UserController: Error borrando usuario {0}", ex.getCause());
                lblErrorUser.setText("Error borrando Usuario");
                lblErrorUser.setTextFill(Color.RED);
                lblErrorUser.setVisible(true);
            }

        }

    }

    /**
     * Method that updates the selected user on the database and the table.
     *
     * @param event event.
     */
    private void updateUser(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dialogo de confirmacion");
        alert.setContentText("¿Estas seguro que deseas actualizar el usuario?");
        alert.setHeaderText("Modificar usuario");

        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("okButton");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("cancelButton");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            UserBean selectedUser = (UserBean) tabUsers.getSelectionModel().getSelectedItem();
            if (selectedUser.getFullname().trim().length() >= 256) {
                LOGGER.log(Level.SEVERE, "UserController: FullName Muy Largo");
                lblErrorUser.setText("El nombre del Usuario es demasiado largo");
                lblErrorUser.setTextFill(Color.RED);
                lblErrorUser.setVisible(true);
            } else {
                try {
                    userLogic.updateUser(selectedUser);
                    lblErrorUser.setText("Usuario modificado");
                    lblErrorUser.setTextFill(Color.GREEN);
                    lblErrorUser.setVisible(true);
                } catch (BusinessLogicException ex) {
                    LOGGER.log(Level.SEVERE, "UserController: Error Actualizando usuario {0}", ex.getCause());
                    lblErrorUser.setText("Error modificando Usuario");
                    lblErrorUser.setTextFill(Color.RED);
                    lblErrorUser.setVisible(true);
                }
            }
        }

    }

    /**
     * Method that handles event for Printing report.
     * @param event
     */
    private void printAction(ActionEvent event) {
        try {
            JasperReport report
                    = JasperCompileManager.compileReport(getClass()
                            .getResourceAsStream("/jampclientside/ui/report/UserReport.jrxml"));
            //Data for the report: a collection of UserBean passed as a JRDataSource 
            //implementation 
            JRBeanCollectionDataSource dataItems
                    = new JRBeanCollectionDataSource((Collection<UserBean>) this.tabUsers.getItems());
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
            LOGGER.log(Level.SEVERE,
                    "UI GestionUsuariosController: Error printing report: {0}",
                    ex.getMessage());
        }
    }

    /**
     * Go to the events view.
     *
     * @param event event.
     */
    private void goEvents(ActionEvent event) {
        LOGGER.info("clickOn Eventos Menu");
        try {
            EventLogic iLogic = ILogicFactory.getEventLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC05Events.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC05EventsController controller = (PC05EventsController) loader.getController();
            //le mando el objeto logica al controlador
            /**
             * *******************************************
             */
            controller.setILogic(iLogic);
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
     * Go to products view.
     *
     * @param event event.
     */
    private void goProducts(ActionEvent event) {
        LOGGER.info("clickOn Productos Menu");
        try {
            ProductLogic iLogic = ILogicFactory.getProductLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC07Products.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC07ProductsController controller = (PC07ProductsController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogicProduct(iLogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            controller.setUser(user);
            //inizializo el stage
            controller.initStage(root);
            this.stage.hide();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    /**
     * Go to Expenses view.
     *
     * @param event event.
     */
    private void goExpenses(ActionEvent event) {
        LOGGER.info("clickOn Gastos Menu");
        try {
            ExpenseLogic iLogic = ILogicFactory.getExpenseLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC04Expense.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC04ExpenseController controller = (PC04ExpenseController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogic);
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
     * Go to Users view.
     *
     * @param event event.
     */
    private void goUsers(ActionEvent event) {
        LOGGER.info("clickOn Usuarios Menu");
        try {
            UserLogic iLogic = ILogicFactory.getUserLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC03User.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC03UserController controller = (PC03UserController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogic);
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
     * Go to Phones view.
     *
     * @param event event.
     */
    private void goPhones(ActionEvent event) {
        LOGGER.info("clickOn Telefonos Menu");
        try {
            TelephoneLogic iLogic = ILogicFactory.getTelephoneLogic();
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC08PhoneNumber.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //obtener el controlador
            PC08PhoneNumbersController controller = (PC08PhoneNumbersController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(iLogic);
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
     * Go to FTP Client view.
     *
     * @param event event.
     */
    private void goFTP(ActionEvent event) {
        LOGGER.info("clickOn Telefonos Menu");
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
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

}
