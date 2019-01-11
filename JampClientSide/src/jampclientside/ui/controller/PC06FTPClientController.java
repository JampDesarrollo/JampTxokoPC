/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * FXML Controller class
 *
 * @author Ander
 */
public class PC06FTPClientController {

    /**
     * Username.
     */
    private final String USERNAME = ResourceBundle.
            getBundle("jampclientside.ui.controller.config").getString("USERNAME");
    /**
     * Password.
     */
    private final String PASSWORD = ResourceBundle.
            getBundle("jampclientside.ui.controller.config").getString("PASSWORD");
    /**
     * IP address the server socket has to connect to.
     */
    private final String IP = ResourceBundle.
            getBundle("jampclientside.ui.controller.config").getString("IP");
    /**
     * Port from which the connection to the server socket will be done.
     */
    private final String PORT = ResourceBundle.
            getBundle("jampclientside.ui.controller.config").getString("PORT");
    /**
     *
     */
    @FXML
    private Button btnUpload;
    /**
     *
     */
    @FXML
    private Button btnDownload;
    /**
     *
     */
    @FXML
    private Button btnDeleteFile;
    /**
     *
     */
    @FXML
    private Button btnCreateDir;
    /**
     *
     */
    @FXML
    private Button btnDeleteDir;
    /**
     *
     */
    @FXML
    private TreeView<String> tView;
    /**
     *
     */
    @FXML
    private Label lblInfo;
    /**
     *
     */
    @FXML
    private Button btnOut;
    /**
     * The Stage object associated to the Scene controlled by this controller.
     * This is an utility method reference that provides quick access inside the
     * controller to the Stage object in order to make its initialization.
     */
    private Stage stage;

    /**
     * FTPClient object.
     */
    private FTPClient ftp;

    /**
     * Sets the Stage object related to this controller.
     *
     * @param stage The Stage object to be initialized.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * Logger object used to log messages of this controller.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jampclientside.ui.controller");

    /**
     * Initializes the controller class.
     *
     * @param root
     */
    public void initStage(Parent root) {
        LOGGER.info("ventana de registro InitStage");
        try {
            Scene scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Cliente FTP");
            stage.setResizable(false);
            stage.setOnShowing(this::handleWindowShowing);
            btnOut.setOnAction(this::back);
            btnUpload.setOnAction(this::uplaodFile);
            btnDownload.setOnAction(this::downloadFile);
            stage.show();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "{0} No se ha podido abrir la ventana. \n ",
                    e.getCause());
        }
    }

    /**
     * Initializes the window when shown.
     *
     * @param event WindowEvent
     */
    private void handleWindowShowing(WindowEvent event) {
        LOGGER.info("Beggining PCFTPCLient::handleWindowShowing");
        LOGGER.info("Ventana de cliente FTP");
        lblInfo.setVisible(false);
        btnCreateDir.setDisable(false);
        btnUpload.setDisable(false);
        btnDownload.setDisable(true);
        btnDeleteDir.setDisable(true);
        btnDeleteFile.setDisable(true);
        tView.setDisable(false);
        boolean connected = connectFTP();
        if (connected) {
            try {
                lblInfo.setText("Conectado a Servidor FTP");
                lblInfo.setStyle("-fx-text-inner-color: green;");
                lblInfo.setVisible(true);
                //Lista de files 
                FTPFile[] files = ftp.listFiles("/");
                TreeItem<String> rootItem = new TreeItem<String>("/");
                rootItem.setExpanded(true);

                for (FTPFile file : files) {
                    if (file.isDirectory()) {
                        
                    } else {
                        TreeItem<String> item = new TreeItem<String>(file.getName());
                        rootItem.getChildren().add(item);
                    }
                }
                tView = new TreeView<String>(rootItem);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "No se ha podido listar "
                        + "directorio de FTP server. {0} \n ", e.getCause());
            }
        } else {
            lblInfo.setText("No se ha podido conectar a ServidorFTP");
            lblInfo.setStyle("-fx-text-inner-color: red;");
            lblInfo.setVisible(true);
        }
        stage.show();

    }

    /**
     * Connect FTP client to server.
     */
    private boolean connectFTP() {
        boolean connected = false;
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(
                new PrintCommandListener(new PrintWriter(System.out)));
        try {
            ftp.connect(IP, Integer.parseInt(PORT));
            ftp.enterRemotePassiveMode();
            ftp.login(USERNAME, PASSWORD);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            connected = true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se ha podido conectar "
                    + "con servidor FTP. {0} \n ", e.getCause());
        }
        return connected;
    }

    /**
     * Close current view, disconnect ftp client and open eventos view method.
     *
     * @param ev ActionEvent
     */
    private void back(ActionEvent ev) {
        LOGGER.info("Ventana de cliente FTP back");
        try {
            ftp.logout();
            ftp.disconnect();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se ha podido desconectar "
                    + "con servidor FTP. {0} \n ", e.getCause());
        }
        stage.hide();
    }

    /**
     * Upload a file
     *
     * @param ev ActionEvent
     */
    private void uplaodFile(ActionEvent ev) {
        FileInputStream fis = null;
        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Elige un archivo");
        fChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fChooser.showOpenDialog(stage);
        try {
            fis = new FileInputStream(file.getName());
            ftp.storeFile(file.getName(), fis);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se ha podido subir archivo "
                    + "a servidor FTP. {0} \n ", e.getCause());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error cerrando. {0} ", e.getCause());
                }
            }
        }

    }
    
    private void downloadFile(ActionEvent ev){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/");
            ftp.retrieveFile("/", out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PC06FTPClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PC06FTPClientController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(PC06FTPClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
