
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;


import jampclientside.entity.UserBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.exceptions.PasswordNotOkException;
import jampclientside.exceptions.UserNotExistException;
import jampclientside.logic.UserLogic;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * Controller class for Login view.
 *
 * @author paula
 */
public class PC01LoginController {

    /**
     *
     * Attribute for the stage.
     */
    private Stage stage;

    /**
     *
     * Attribute to appear the information text.
     */
    private static final Logger LOGGER = Logger.getLogger("package.class");

    /**
     *
     * Attribute ilogic to be able to pass the ilogic object to the others
     * class.
     */
    private UserLogic ilogic;

    /**
     *
     * Maximum characters that can be inserted
     */
    private static final int MAX_CARACT = 255;

    /**
     *
     * User text field
     */
    @FXML
    private TextField tfUsuario;

    /**
     *
     * password field for the password
     */
    @FXML
    private PasswordField pfContraseña;

    /**
     *
     * Button to see the password
     */
    @FXML
    private Button btnOjo;

    /**
     *
     * Label for the errors
     */
    @FXML
    private Label lblError;

    /**
     *
     * Button for login
     */
    @FXML
    private Button btnInicio;

    /**
     *
     * Link to go to signup window.
     */
    @FXML
    private Hyperlink hpLink;
    /**
     *
     * Link to get password reset.
     */
    @FXML
    private Hyperlink hpForgot;

    /**
     * textfield for the password
     */
    @FXML
    private TextField tfContraseña;

    /**
     * Gif for the wait.
     */
    @FXML
    private ImageView imLoading;

    /**
     * Tooltip for tfUsuario
     */
    private Tooltip tooltip = new Tooltip();

    /**
     * Tooltip for pfContraseña
     */
    private Tooltip tooltipContra = new Tooltip();

    /**
     * Sets the Stage object related to this controller.
     *
     * @param stage it receives the stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;

    }

    /**
     *
     * Method that receives the ilogic param of the class application.
     *
     * @param ILogic it receives the logic object that came from the application
     * class
     */
    public void setILogic(UserLogic iLogic) {
        this.ilogic = iLogic;
    }

    /**
     * Method that initializes the "Login" window. It receives the param root,
     * where is the fxml file.
     *
     * @param root receives the root parameter
     */
    public void initStage(Parent root) {  //recibo el root, AHÍ tengo el archivo XML
        LOGGER.info("Initializing Login ");
        //creo la escena y le paso la que esta cargada en root
        Scene scene = new Scene(root);
        //asociar la escena con el escenario
        stage.setScene(scene);
        //titulo de la ventana
        stage.setTitle("Login");
        stage.setResizable(false);
        //cuando haga el metodo setOnShowing, haga el metodo handlewindowshowing
        stage.setOnShowing(this::handleWindowShowing);
        //manejadores de eventos
        hpLink.setOnAction(this::register);
        hpForgot.setOnAction(this::passwForgotten);
        btnInicio.setOnAction(this::logIn);
        btnOjo.setOnAction(this::showPassword);
        //muestro la ventana
        stage.show();
    }

    /**
     * Method that initializes the state of the components of the window.
     *
     * @param event parameter to do an action
     */
    public void handleWindowShowing(WindowEvent event) {
        LOGGER.info("ventana de inicio sesion handleWindowShowing");
        //los botones van a estar activos desde el primero momento
        btnInicio.setDisable(false);
        btnOjo.setDisable(false);
        hpLink.setDisable(false);
        hpForgot.setDisable(false);
        tfUsuario.requestFocus();
        tfUsuario.selectAll();
        imLoading.setVisible(false);
        tfContraseña.setVisible(false);
        //el label de informacion estará invisible
        lblError.setVisible(false);
        tooltip.setText("Escriba el nombre del usuario");
        tfUsuario.setTooltip(tooltip);
        tooltipContra.setText("Escriba la contraseña, tiene que tener mínimo 8 carácteres");
        pfContraseña.setTooltip(tooltipContra);
    }

    /**
     * Method used for sending a recuperation login's e-mail
     *
     * @param ev
     * @author ander
     */
    public void passwForgotten(ActionEvent ev) {
        LOGGER.info("ventana de login  contraseña olvidada");
        boolean loginFilled = chkLoginFilled();
        if (loginFilled) {

            Boolean allOk = getUserEmail();
            if (allOk) {
                lblError.setText("Se ha restablecido su contraseña, "
                        + "compruebe su email");
            lblError.setStyle("-fx-text-inner-color: green;");
            lblError.setVisible(true);
            }
        }
    }

    /**
     *
     * @return user
     * @author ander
     */

    private Boolean getUserEmail() {
        Boolean allOk = false;

        try {
            allOk = ilogic.findUserForgotPassw(tfUsuario.getText());
        } catch (BusinessLogicException ex) {
            tfUsuario.setStyle("-fx-border-color:red;");
            tfUsuario.requestFocus();
            tfUsuario.selectAll();
            lblError.setText("No existe este login");
            lblError.setStyle("-fx-text-inner-color: red;");
            lblError.setVisible(true);
            LOGGER.log(Level.SEVERE, " El login de usuario no existe. {0}",
                    ex.getMessage());
        }


        return allOk;
    }

    /**
     * Check login textfield is filled.
     *
     * @return filled
     * @author ander
     */
    private boolean chkLoginFilled() {
        boolean filled = true;
        //Set lblError to red
        if (tfUsuario.getText().trim().isEmpty()) {
            filled = false;
            tfUsuario.setStyle("-fx-border-color:red;");
            lblError.setText("Introduzca un login");
            lblError.setStyle("-fx-text-inner-color: red;");
            lblError.setVisible(true);
        }
        //Set lblError style back to normal
        if (!tfUsuario.getText().trim().isEmpty()) {
            filled = false;
            tfUsuario.setStyle("-fx-border-color:-fx-box-border;");
            lblError.setText("");
            lblError.setVisible(false);
        }

        return filled;
    }

    /**
     * Method to go to the register window.
     *
     * @param ev parameter to do an action
     */
    public void register(ActionEvent ev) {
        LOGGER.info("clickOn Hyperlink");
        try {
            //instancio el xml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC02SignUp.fxml"));
            //lo cargo en el root que es de tipo parent
            Parent root = (Parent) loader.load();
            //tengo que crear un nuevo escenario
            // stage = new Stage();
            //obtener el controlador
            PC02SignUpController controller = (PC02SignUpController) loader.getController();
            //le mando el objeto logica al controlador 
            controller.setILogic(ilogic);
            //a ese controlador le paso el stage
            controller.setStage(stage);
            //inizializo el stage
            controller.initStage(root);
            //que aparezca como al principio
            tfUsuario.setText("Nombre de usuario");
            tfUsuario.requestFocus();
            tfUsuario.selectAll();
            pfContraseña.setText("");
            tfUsuario.setStyle("-fx-border-color: -fx-box-border;");
            pfContraseña.setStyle("-fx-border-color: -fx-box-border;");
            lblError.setVisible(false);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
        }
    }

    /**
     * It serves to be able to make all the necessary checks to be able to
     * initiate session.
     *
     * @param ev parameter to do an action
     */
    public void logIn(ActionEvent ev) {
        LOGGER.info("ventana de login  inicio sesion");

        //va a mirar si los campos estan llenos o no
        boolean filled = chkAllFieldsFilled();
        //si los campos estan llenos  
        if (filled) {
            //que se quiten los errores
            tfUsuario.setStyle("-fx-border-color: -fx-box-border;");
            pfContraseña.setStyle("-fx-border-color: -fx-box-border;");
            lblError.setVisible(false);
            //si estan todos los campos comentados, comprobamos los caracteres maximos 
            boolean maxCar = maxCharacters();
            //si los caracteres son menos que el maximo
            if (maxCar) {
                //si todo esta bien metido, que se quiten los errores
                tfUsuario.setStyle("-fx-border-color: -fx-box-border;");
                pfContraseña.setStyle("-fx-border-color: -fx-box-border;");
                lblError.setVisible(false);
                //como vamos a hacer comprobaciones con la base de datos, ponemos el gif
                imLoading.setVisible(true);
                //comprobamos que existen el usuario y la contraseña
                UserBean userReturn = chkUserPassword(); //le envio el texto de lo que ha puesto en los campos
                if (userReturn != null) { // si lo que devuelve es un usuario diferente a null 
                    try {
                        //si esta todo correcto que vaya a la ventana principal
                        //instancio el xml
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/jampclientside/ui/view/PC03User.fxml"));
                        //lo cargo en el root que es de tipo parent
                        Parent root = (Parent) loader.load();
                        //obtener el controlador
                        PC03UserController controller = (PC03UserController) loader.getController();
                        //le mando el objeto logica 
                        controller.setILogic(ilogic);
                        //ilogic.UserLogin(mensaje);
                        //a ese controlador le paso el stage
                        controller.setStage(stage);
                        //inizializo el stage
                        //le paso el usuario entero a la ventana 
                        controller.setUser(userReturn);
                        controller.initStage(root);
                        //que aparezca como al principio
                        tfUsuario.setText("Nombre de usuario");
                        tfUsuario.requestFocus();
                        tfUsuario.selectAll();
                        pfContraseña.setText("");
                        imLoading.setVisible(false);
                        //stage.hide();

                    } catch (Exception ex) {
                        //mensaje de "no se ha podido cargar la ventana"
                        LOGGER.log(Level.SEVERE, "Error accediendo a la ventana {0}", ex.getCause());
                        lblError.setText("Error al conectar con la base de datos");
                        lblError.setStyle("-fx-text-inner-color: red;");
                        lblError.setVisible(true);
                        imLoading.setVisible(false);
                    }
                }
            } else { //si los caracteres son mayores a los definidos
                if (tfUsuario.getText().trim().length() > MAX_CARACT && pfContraseña.getText().trim().length() > MAX_CARACT) {
                    btnInicio.requestFocus();
                    tfUsuario.setStyle("-fx-border-color: red;");
                    pfContraseña.setStyle("-fx-border-color: red;");
                    lblError.setText("Demasiados caracteres");
                    lblError.setStyle("-fx-text-inner-color: red;");
                    lblError.setVisible(true);
                    //enfoco los dos campos
                    tfUsuario.requestFocus();
                    tfUsuario.selectAll();
                    pfContraseña.requestFocus();
                    pfContraseña.selectAll();

                } else if (tfUsuario.getText().trim().length() > MAX_CARACT) {
                    btnInicio.requestFocus();
                    tfUsuario.setStyle("-fx-border-color: red;");
                    pfContraseña.setStyle("-fx-border-color: -fx-box-border;");
                    lblError.setText("Demasiados caracteres");
                    lblError.setStyle("-fx-text-inner-color: red;");
                    lblError.setVisible(true);
                    //enfoco el campo del usuario
                    tfUsuario.requestFocus();
                    tfUsuario.selectAll();
                } else if (pfContraseña.getText().trim().length() > 255) {  //si la contraseña esta mal por que se pasa de los 255 caracteres
                    btnInicio.requestFocus();
                    tfUsuario.setStyle("-fx-border-color: -fx-box-border;");
                    pfContraseña.setStyle("-fx-border-color: red;");
                    lblError.setText("Demasiados caracteres");
                    lblError.setStyle("-fx-text-inner-color: red;");
                    lblError.setVisible(true);
                    //enfoco el campo de la contraseña
                    pfContraseña.requestFocus();
                    pfContraseña.selectAll();
                } else if (pfContraseña.getText().trim().length() < 8) {//si la contraseña esta mal por que no llega a los 8 caracteres
                    btnInicio.requestFocus();
                    tfUsuario.setStyle("-fx-border-color: -fx-box-border;");
                    pfContraseña.setStyle("-fx-border-color: red;");
                    lblError.setText("La contraseña tiene que tener mínimo 8 carácteres");
                    lblError.setStyle("-fx-text-inner-color: red;");
                    lblError.setVisible(true);
                    //enfoco el campo de la contraseña
                    pfContraseña.requestFocus();
                    pfContraseña.selectAll();
                }
            }
        } else { //si algun campo o los dos estan vacios
            //se va a enfocar los campos y se le va a cambiar el texto del label
            // enfocar los campos
            if (tfUsuario.getText().trim().equals("") && pfContraseña.getText().trim().equals("")) {
                btnInicio.requestFocus();
                //que este en rojo
                tfUsuario.setStyle("-fx-border-color: red;");
                pfContraseña.setStyle("-fx-border-color: red;");
                //hacer visible el texto
                lblError.setText("Campo requerido");
                lblError.setStyle("-fx-text-inner-color: red;");
                lblError.setVisible(true);

            } else if (tfUsuario.getText().trim().equals("")) {
                btnInicio.requestFocus();
                //que este en rojo el usuario pero la contraseña no
                tfUsuario.setStyle("-fx-border-color: red;");
                pfContraseña.setStyle("-fx-border-color: -fx-box-border;");
                lblError.setText("Campo requerido");
                lblError.setStyle("-fx-text-inner-color: red;");
                lblError.setVisible(true);

            } else {
                btnInicio.requestFocus();
                //que este en rojo la contraseña y el usuario normal
                pfContraseña.setStyle("-fx-border-color: red;");
                tfUsuario.setStyle("-fx-border-color: -fx-box-border;");
                //que el texto sea visible 
                lblError.setText("Campo requerido");
                lblError.setStyle("-fx-text-inner-color: red;");
                lblError.setVisible(true);

            }
        }

    }

    /**
     *
     * Method to know that all fields are filled. It returns a boolean.
     *
     * @return boolean it returns a boolean indicating if all fields are filled
     * or not
     */
    private boolean chkAllFieldsFilled() {
        boolean isFilled = false;
        if (!this.tfUsuario.getText().trim().equals("") && !this.pfContraseña.getText().trim().equals("")) {
            //si son diferentes a vacio, devuelve true, eso quiere decir que hay algo escrito
            isFilled = true;
        }
        return isFilled;
    }

    /**
     *
     * Method to know if the user and password are ok. If the user and password
     * are ok, it returns the all the user to be visible in the main window.
     *
     * @return in case that everything is ok, returns the user.
     */
    private UserBean chkUserPassword() {
        UserBean returnUser = null;
        //try {
            byte[] encryptedPassw = EncryptPassword.encrypt(pfContraseña.getText().getBytes());
            String ePassw = new String(encryptedPassw);

        try {
            //creo un nuevo usuario con contraseña y password solamente

            returnUser = ilogic.findUserByLoginPassw(tfUsuario.getText(), ePassw); // el userlogin me va a devolver el usuario entero 
        } catch (BusinessLogicException ex) {
            Logger.getLogger(PC01LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      /*  } catch (UserNotExistException e) {
            LOGGER.log(Level.SEVERE, "User not exist exception {0}", e.getCause());
            //se pone el foco en el usuario
            btnInicio.requestFocus();
            //que este en rojo
            tfUsuario.setStyle("-fx-border-color: red;");
            //que el texto sea visible 
            lblError.setText("Usuario incorrecto");
            lblError.setVisible(true);
            lblError.setStyle("-fx-text-inner-color: red;");
            imLoading.setVisible(false);
            //se va a enfocar el campo del usuario
            tfUsuario.requestFocus();
            tfUsuario.selectAll();
        } catch (PasswordNotOkException e) {
            LOGGER.log(Level.SEVERE, "Password not ok exception {0}", e.getCause());
            //se pone el foco en el campos de la contraseña y usuario 
            btnInicio.requestFocus();
            pfContraseña.setStyle("-fx-border-color: red;");
            //que el texto sea visible 
            lblError.setText("Contraseña incorrecta");
            lblError.setVisible(true);
            lblError.setStyle("-fx-text-inner-color: red;");
            imLoading.setVisible(false);
            //se va a enfocar el campo de la contraseña
            pfContraseña.requestFocus();
            pfContraseña.selectAll();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error {0}", e.getCause());
            lblError.setText("Error al conectar con la base de datos");
            lblError.setStyle("-fx-text-inner-color: red;");
            lblError.setVisible(true);
            imLoading.setVisible(false);
        }*/
        return returnUser;
    }

    /**
     * Method to be able to see the password or not.
     *
     * @param ev parameter to do an action
     */
    private void showPassword(ActionEvent ev) {
        LOGGER.info("ClickOn button Eye");
        if (pfContraseña.isVisible()) {
            //Si el texto esta oculto, se cambia a visible
            tfContraseña.setText(pfContraseña.getText());
            pfContraseña.setVisible(false);
            tfContraseña.setVisible(true);
        } else {
            //Si el texto esta visible, se cambia a oculto
            pfContraseña.setText(tfContraseña.getText());
            tfContraseña.setVisible(false);
            pfContraseña.setVisible(true);
        }

    }

    /**
     * Method to control the characters you enter in the textfield and
     * passwordfield.
     *
     * @return It returns a boolean indicating if the characters are more or
     * less than maximum/minimum characters defined
     */
    private boolean maxCharacters() {
        boolean maxcaracteres = false;
        //La contraseña tiene que estar entre 255 caracteres y 8 
        if (tfUsuario.getText().trim().length() < MAX_CARACT && pfContraseña.getText().trim().length() < MAX_CARACT && pfContraseña.getText().trim().length() >= 8) {
            maxcaracteres = true;
        }
        return maxcaracteres;
    }

}
