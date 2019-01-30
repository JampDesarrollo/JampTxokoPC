/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.EventBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.logic.EventLogic;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.LocalDateStringConverter;

/**
 * Controller to add an event
 *
 * @author paula
 */
public class AddEventController {

    /**
     * Logger attribute
     */
    private static final Logger LOGGER = Logger.getLogger("package.class");
    private ObservableList<EventBean> eventsData;
    private static final int MAX_CARACT = 255;
    private String datePickerString;
    @FXML
    private DatePicker datePicker;

    /**
     * Stage object
     */
    private Stage stage;
    /**
     * Event logic object
     */
    private EventLogic ilogic;
    /**
     * text field for the name of the event
     */
    @FXML
    private TextField tfName;
    /**
     * go out button
     */
    @FXML
    private Button btnSalir;
    /**
     * text field for the description
     */
    @FXML
    private TextField tfDescription;
    /**
     * text field for the date
     */
    @FXML
    private TextField tfDate;
    /**
     * text field for the name of the img of the event
     */
    @FXML
    private TextField tfImg;
    /**
     * text field for the price
     */
    @FXML
    private TextField tfPrice;
    /**
     * button to add an event
     */
    @FXML
    private Button btnAdd;
    /**
     * label error
     */
    @FXML
    private Label lblError;
    /**
     * test field for the hour
     */
    @FXML
    private TextField hora;
    /**
     * label error for the date
     */
    @FXML
    private Label lblErrorDate;

    /**
     * Set logic for this view controller
     *
     * @param ilogic ilogic
     */
    public void setILogic(EventLogic ilogic) {
        this.ilogic = ilogic;
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
        stage.setResizable(true);
        //Set window properties
        stage.setTitle("Añadir eventos");
        //Set window's events handlers
        stage.setOnShowing(this::handleWindowShowing);
        btnAdd.setOnAction(this::addEvent);
        btnSalir.setOnAction(this::goOut);
        stage.show();
    }

    /**
     * Initializes the window when shown.
     *
     * @param event event
     */
    private void handleWindowShowing(WindowEvent event) {
        btnAdd.requestFocus();
        btnAdd.setDisable(false);
        btnSalir.setDisable(false);
        lblError.setVisible(false);
        lblErrorDate.setVisible(false);
        hora.setDisable(false);
        tfName.setDisable(false);
        tfDescription.setDisable(false);
        tfPrice.setDisable(false);
        tfImg.setDisable(false);
        datePicker.setDisable(false);
       
    }

    /**
     * method to go out of the window
     *
     * @param ev event
     */
    public void goOut(ActionEvent ev) {
        stage.hide();
    }

    /**
     * Method to add an event
     *
     * @param ev event
     */
    public void addEvent(ActionEvent ev) {
        LOGGER.info("clickOn addevent");
        lblError.setVisible(false);
        boolean isNotEmpty = isNotEmpty();
        //si ninguno de los campos estan vacios
        if (isNotEmpty) {
            //que se quite el campo en rojo por si alguno tenia de antes
            lblError.setVisible(false);
            tfName.setStyle("-fx-border-color: -fx-box-border;");
            tfDescription.setStyle("-fx-border-color: -fx-box-border;");
            tfImg.setStyle("-fx-border-color: -fx-box-border;");
            tfPrice.setStyle("-fx-border-color: -fx-box-border;");
            hora.setStyle("-fx-border-color: -fx-box-border;");
            lblErrorDate.setVisible(false);
            //RECOJO EN UN STRING LA FECHA DEL DATE PICKER
            datePickerString = datePicker.getValue().toString();
            //AHORA VOY A MIRAR QUE LOS CARACTERES NO SEAN MAYORES A 255
            boolean maxCharacters = maxCharacters();
            //si los caracteres no son mayores que 255 y el formato de la hora es el correcto
            if (maxCharacters) {
                //ai habia algun error antes
                lblError.setVisible(false);
                tfName.setStyle("-fx-border-color: -fx-box-border;");
                tfDescription.setStyle("-fx-border-color: -fx-box-border;");
                tfImg.setStyle("-fx-border-color: -fx-box-border;");
                tfPrice.setStyle("-fx-border-color: -fx-box-border;");
                //miro el formato de la hora
                boolean horaFormat = horaFormat(hora.getText());
                //si el formato de la hora es el correcto
                if (horaFormat) {
                    hora.setStyle("-fx-border-color: -fx-box-border;");
                    lblError.setVisible(false);
                    //concateno la fecha y la hora    
                    String fechaHora = datePickerString.concat("T").concat(hora.getText());
                    try {

                        EventBean event;
                        event = new EventBean(tfName.getText(), tfDescription.getText(), fechaHora, tfImg.getText(), tfPrice.getText());
                        this.ilogic.createEvent(event);
                        Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
                        dialogoAlerta.setTitle("INFORMACION");
                        dialogoAlerta.setContentText("Evento creado");
                        dialogoAlerta.setHeaderText("Crear un evento");
                        dialogoAlerta.showAndWait();
                        LOGGER.log(Level.SEVERE,
                                "  creating an event: {0}");

                    } catch (BusinessLogicException e) {
                        Alert dialogoAlerta = new Alert(Alert.AlertType.ERROR);
                        dialogoAlerta.setTitle("ERROR");
                        dialogoAlerta.setContentText("Error a la hora de crear un evento");
                        dialogoAlerta.setHeaderText("Crear un evento");
                        dialogoAlerta.showAndWait();
                        LOGGER.log(Level.SEVERE,
                                " Error creating an event: {0}",
                                e.getMessage());
                    }
                } else {
                    lblError.setText("Formato de hora incorrecto: HH:mm:ss 00:00:00");
                    lblError.setVisible(true);
                    hora.setStyle("-fx-border-color: red;");
                }

            }//si hay algun campo que lo supera
            else {
                lblError.setText("Demasiados caracteres");
                lblError.setVisible(true);
                if (tfName.getText().trim().length() > MAX_CARACT || tfDescription.getText().trim().length() > MAX_CARACT || tfImg.getText().trim().length() > MAX_CARACT || tfPrice.getText().length() > MAX_CARACT) {
                    //si el campo supera los caracteres
                    if (tfName.getText().trim().length() > MAX_CARACT) {
                        tfName.setStyle("-fx-border-color: red;");
                    }//si el campo ya no esta vacio no se pone en rojo 
                    else if (tfName.getText().trim().length() < MAX_CARACT) {
                        tfName.setStyle("-fx-border-color: -fx-box-border;");
                    }
                    if (tfDescription.getText().trim().length() > MAX_CARACT) {
                        tfDescription.setStyle("-fx-border-color: red;");
                    } else if (tfDescription.getText().trim().length() < MAX_CARACT) {
                        tfDescription.setStyle("-fx-border-color: -fx-box-border;");
                    }
                    if (tfImg.getText().trim().length() > MAX_CARACT) {
                        tfImg.setStyle("-fx-border-color: red;");
                    } else if (tfImg.getText().trim().length() < MAX_CARACT) {
                        tfImg.setStyle("-fx-border-color: -fx-box-border;");
                    }
                    if (tfPrice.getText().length() > MAX_CARACT) {
                        tfPrice.setStyle("-fx-border-color: red;");
                    } else if (tfPrice.getText().length() < MAX_CARACT) {
                        tfPrice.setStyle("-fx-border-color: -fx-box-border;");
                    }
                }

            }
        } else { //hay campos vacios
            lblError.setText("Todos los campos deben de estar llenos ");
            lblError.setVisible(true);
            //si cualquiera de los campos esta vacio primero
            if (tfName.getText().trim().equals("") || tfDescription.getText().trim().equals("")  || hora.getText().equals("") || tfImg.getText().trim().equals("") || tfPrice.getText().trim().equals("")) {
                //si el campo esta vacio se pone en rojo
                if (tfName.getText().trim().equals("")) {
                    tfName.setStyle("-fx-border-color: red;");
                }//si el campo ya no esta vacio no se pone en rojo 
                else if (!tfName.getText().trim().isEmpty()) {
                    tfName.setStyle("-fx-border-color: -fx-box-border;");
                }
                if (tfDescription.getText().trim().equals("")) {
                    tfDescription.setStyle("-fx-border-color: red;");
                } else if (!tfDescription.getText().trim().isEmpty()) {
                    tfDescription.setStyle("-fx-border-color: -fx-box-border;");
                }
                if (hora.getText().equals("")) {
                    hora.setStyle("-fx-border-color: red;");
                } else if (!hora.getText().equals("")) {
                    hora.setStyle("-fx-border-color: -fx-box-border;");
                }
                if (tfImg.getText().trim().equals("")) {
                    tfImg.setStyle("-fx-border-color: red;");
                } else if (!tfImg.getText().trim().isEmpty()) {
                    tfImg.setStyle("-fx-border-color: -fx-box-border;");
                }
                if (tfPrice.getText().trim().equals("")) {
                    tfPrice.setStyle("-fx-border-color: red;");
                } else if (!tfPrice.getText().trim().isEmpty()) {
                    tfPrice.setStyle("-fx-border-color: -fx-box-border;");
                }
            }
        }
    }

    /**
     * Method to know if all the fields are empty or not
     *
     * @return it returns a boolean
     */
    //Si todos los campos estan llenos, devuelve un true
    //Si hay alguno vacio devuelve un false
    public boolean isNotEmpty() {
        boolean isNotEmpty = false;
        //si estan todo llenos
        if (!tfName.getText().trim().isEmpty() && !tfDescription.getText().trim().isEmpty()   &&   !hora.getText().isEmpty() && !tfPrice.getText().trim().isEmpty()) {
            isNotEmpty = true;
        }
        return isNotEmpty;
    }

    /**
     * Method to see if the format of the time that the user is entering is
     * correct
     *
     * @param HORA it receives the time that the user has enter in the text
     * field
     * @return it return a boolean indicating if the time format is correct or
     * not
     */
    private boolean horaFormat(String hora) {
        SimpleDateFormat formatoHora;
        try {
            formatoHora = new SimpleDateFormat("HH:mm:ss");
            formatoHora.setLenient(false);
            formatoHora.parse(hora);
        } catch (ParseException e) {
            return false;

        }
        return true;

    }

    private boolean maxCharacters() {
        boolean maxCharacters = false;
        if (tfName.getText().trim().length() < MAX_CARACT && tfDescription.getText().trim().length() < MAX_CARACT && tfImg.getText().trim().length() < MAX_CARACT && tfPrice.getText().trim().length() < MAX_CARACT) {
            maxCharacters = true;
        }
        return maxCharacters;
    }

}
