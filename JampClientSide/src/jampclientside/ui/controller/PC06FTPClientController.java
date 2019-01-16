/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.logic.FTPClientLogic;
import java.io.File;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
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
     * 
     */
    private FTPClientLogic iLogic;
    /**
     * 
     * @param iLogic 
     */
    public void setILogic(FTPClientLogic iLogic) {
        this.iLogic = iLogic;
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
//            stage.setOnShowing(this::handleWindowShowing);
 //           btnOut.setOnAction(this::back);
            btnUpload.setOnAction(this::uplaodFile);
            btnDownload.setOnAction(this::downloadFile);
            btnDeleteFile.setOnAction(this::deleteFile);
            btnCreateDir.setOnAction(this::createDir);
            btnDeleteDir.setOnAction(this::deleteDir);
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
 /*   private void handleWindowShowing(WindowEvent event) {
        LOGGER.info("Beggining PCFTPCLient::handleWindowShowing");
        LOGGER.info("Ventana de cliente FTP");
        lblInfo.setVisible(false);
        btnCreateDir.setDisable(false);
        btnUpload.setDisable(false);
        btnDownload.setDisable(true);
        btnDeleteDir.setDisable(true);
        btnDeleteFile.setDisable(true);
        tView.setDisable(false);
        Boolean connected = iLogic.connectFTP(IP, PORT, USERNAME, PASSWORD);
        if (connected) {
            lblInfo.setText("Conectado a Servidor FTP");
            lblInfo.setStyle("-fx-text-inner-color: green;");
            lblInfo.setVisible(true);
            //Lista de files
            FTPFile[] files = iLogic.listFiles("/");
            TreeItem<String> rootNode = new TreeItem<String>("/");
            rootNode.setExpanded(true);
            for (FTPFile file : files) {
                if (file.isDirectory()) {
                    TreeItem<String> item = new TreeItem<String>(file.getName());
                    TreeItem<String> node0 = new TreeItem<String>("");
                    rootNode.getChildren().add(item);
                    item.getChildren().add(node0);
                    item.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler<TreeItem.TreeModificationEvent<String>>() {
                        @Override
                        public void handle(TreeItem.TreeModificationEvent<String> event) {
                            item.getChildren().remove(0, item.getChildren().size());
                            String path = getPath(item);
                            FTPFile[] filesInDirectory = iLogic.listFiles(path);
                            for (FTPFile files : filesInDirectory) {
                                TreeItem<String> f = new TreeItem<String>(files.getName());
                                item.getChildren().add(f);
                            }
                        }
                    });
                    
                } else {
                    TreeItem<String> item = new TreeItem<String>(file.getName());
                    rootNode.getChildren().add(item);
                }
            }
            tView = new TreeView<String>(rootNode);
            tView.setEditable(false);
            tView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                    if(selectedItem.getChildren()==null){
                        //enable botones para file
                    }else if(selectedItem.getChildren()!=null){
                        // enable botones para carpeta
                    }
                }
            });
        } else {
            lblInfo.setText("No se ha podido conectar a ServidorFTP");
            lblInfo.setStyle("-fx-text-inner-color: red;");
            lblInfo.setVisible(true);
        }
        stage.show();

    }

    
    private String getPath(TreeItem item){
        String fullPath = "";
        String parentItemName ="";
        TreeItem<String> parentItem = null ;
        
        fullPath = "/" + (String) item.getValue();
        parentItemName = (String) item.getParent().getValue();
        parentItem = item;
        
        while(!parentItemName.equals("/")){
            if(!parentItemName.equals("/")){
                parentItem = parentItem.getParent();
                fullPath = "/" + (String) parentItem.getValue() + fullPath;
                
                parentItemName = (String) parentItem.getParent().getValue();
            }
        }
        
        return fullPath;
    }
    
    
    
    /**
     * Close current view, disconnect ftp client and open eventos view method.
     *
     * @param ev ActionEvent
     */
/*    private void back(ActionEvent ev) {
        LOGGER.info("Ventana de cliente FTP back");
        iLogic.disconnectFTP();
        stage.hide();
    }

    /**
     * Upload a file
     *
     * @param ev ActionEvent
     */
    private void uplaodFile(ActionEvent ev) {
        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Elige un archivo");
        fChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fChooser.showOpenDialog(stage);
        iLogic.uploadFile(file);
    }
  
    /**
     * 
     * @param ev 
     */
    private void downloadFile(ActionEvent ev){
        iLogic.downloadFile();
    }
    
    private void deleteFile(ActionEvent ev){
        
    }
    
    private void createDir(ActionEvent ev){
        
    }
    
    private void deleteDir(ActionEvent ev){
        
    }
}
