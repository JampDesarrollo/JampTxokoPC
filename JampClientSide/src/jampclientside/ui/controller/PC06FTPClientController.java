/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.ui.controller;

import jampclientside.entity.MyFile;
import jampclientside.logic.FTPClientLogic;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.net.ftp.FTPClient;

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
    private final String PASS = ResourceBundle.
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
     * Button Upload file.
     */
    @FXML
    private Button btnUpload;
    /**
     * Buttont download file.
     */
    @FXML
    private Button btnDownload;
    /**
     * Button delete file.
     */
    @FXML
    private Button btnDeleteFile;
    /**
     * Button create directory.
     */
    @FXML
    private Button btnCreateDir;
    /**
     * Button delete directory.
     */
    @FXML
    private Button btnDeleteDir;
    /**
     * Treeview of type MyFile.
     */
    @FXML
    private TreeView<MyFile> tView;
    /**
     * Label info.
     */
    @FXML
    private Label lblInfo;
    /**
     * Button to log out.
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
     * Object that implements methods for ftp management.
     */
    private FTPClientLogic iLogic;
    /**
     * Node containing the root folder img.
     */
    private final Node rootFolder = new ImageView(
            new Image("resources/folder.png"));
    /**
     * Image of a folder for directories in TreeView.
     */
    private final Image folder = new Image("resources/folder.png");

    /**
     * Setter of the logic object.
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
            stage.setOnShowing(this::handleWindowShowing);
            btnOut.setOnAction(this::back);
            btnUpload.setOnAction(this::uplaodFile);
            btnDownload.setOnAction(this::downloadFile);
            btnDeleteFile.setOnAction(this::deleteFile);
            btnCreateDir.setOnAction(this::createDir);
            btnDeleteDir.setOnAction(this::deleteDir);
            stage.show();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "FTPClient: No se ha podido abrir la ventana ",
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
        btnOut.setDisable(false);
        btnCreateDir.setDisable(true);
        btnUpload.setDisable(true);
        btnDownload.setDisable(true);
        btnDeleteDir.setDisable(true);
        btnDeleteFile.setDisable(true);
        tView.setDisable(false);
        System.out.println(USERNAME + " " + PASS);
        Boolean connected = iLogic.connectFTP(IP, PORT, USERNAME, PASS);
        if (connected) {
            lblInfo.setText("Conectado a Servidor FTP");
            lblInfo.setTextFill(Color.GREEN);
            lblInfo.setVisible(true);
            //Lista de files
            listFiles();
        } else {
            lblInfo.setText("No se ha podido conectar a ServidorFTP");
            lblInfo.setTextFill(Color.RED);
            lblInfo.setVisible(true);
        }
        stage.show();
    }

    /**
     * Method that fills the TreeView with the items received from the folder
     * shared by FTP.
     */
    private void listFiles() {
        LOGGER.info("Ventana de cliente FTP: cargando treeView");
        MyFile root = new MyFile("/", "/", true);
        ArrayList<MyFile> files = iLogic.listFiles(root);
        TreeItem<MyFile> rootNode = new TreeItem<MyFile>(root, rootFolder);
        tView.setRoot(rootNode);
        rootNode.setExpanded(true);
        for (MyFile file : files) {
            if (file.isIsDirectory()) {
                TreeItem<MyFile> item = new TreeItem<MyFile>(file, new ImageView(folder));
                TreeItem<MyFile> node0 = new TreeItem<MyFile>();
                rootNode.getChildren().add(item);
                item.getChildren().add(node0);
                item.addEventHandler(TreeItem.branchExpandedEvent(), handleExpand);
            } else {
                TreeItem<MyFile> item = new TreeItem<MyFile>(file);
                rootNode.getChildren().add(item);
            }
        }
        tView.setEditable(false);
        tView.getSelectionModel().selectedItemProperty()
                .addListener(this::handleItemSelected);
    }
    /**
     * Expand event handler for directories.
     */
    private EventHandler<TreeItem.TreeModificationEvent<MyFile>> handleExpand
            = new EventHandler<TreeItem.TreeModificationEvent<MyFile>>() {
        @Override
        public void handle(TreeItem.TreeModificationEvent<MyFile> event) {
            TreeItem<MyFile> item = event.getSource();
            item.getChildren().remove(0, item.getChildren().size());
            ArrayList<MyFile> files = iLogic.listFiles(item.getValue());
            for (MyFile file : files) {
                if (file.isIsDirectory()) {
                    TreeItem<MyFile> dir = new TreeItem<MyFile>(file, new ImageView(folder));
                    TreeItem<MyFile> node0 = new TreeItem<MyFile>();
                    item.getChildren().add(dir);
                    dir.getChildren().add(node0);
                    item.addEventHandler(TreeItem.branchExpandedEvent(), handleExpand);
                } else {
                    TreeItem<MyFile> dir = new TreeItem<MyFile>(file);
                    item.getChildren().add(dir);
                }
            }
        }
    };

    /**
     * Handler for every item selected in the TreeView.
     *
     * @param observable observable
     * @param oldValue oldValue
     * @param newValue item selected
     */
    private void handleItemSelected(ObservableValue observable, Object oldValue,
            Object newValue) {
        lblInfo.setVisible(false);
        TreeItem<MyFile> selectedItem = (TreeItem<MyFile>) newValue;
        if (selectedItem == null) {
            btnDownload.setDisable(true);
            btnDeleteFile.setDisable(true);
            btnDeleteDir.setDisable(true);
            btnUpload.setDisable(true);
            btnCreateDir.setDisable(true);
        } else {
            if (!selectedItem.getValue().isIsDirectory()) {
                btnDownload.setDisable(false);
                btnDeleteFile.setDisable(false);
                btnDeleteDir.setDisable(true);
                btnUpload.setDisable(true);
                btnCreateDir.setDisable(true);
            } else {
                btnDownload.setDisable(true);
                btnDeleteFile.setDisable(true);
            }
            if (selectedItem.getValue().isIsDirectory()) {
                btnDeleteDir.setDisable(false);
                btnUpload.setDisable(false);
                btnCreateDir.setDisable(false);
            } else {
                btnDeleteDir.setDisable(true);
            }
        }
    }

    /**
     * Close current view, disconnect ftp client and open eventos view method.
     *
     * @param ev ActionEvent
     */
    private void back(ActionEvent ev) {
        LOGGER.info("Ventana de cliente FTP back");
        iLogic.disconnectFTP();
        stage.hide();
    }

    /**
     * Event listener for upload file button.
     *
     * @param ev ActionEvent
     */
    private void uplaodFile(ActionEvent ev) {
        LOGGER.info("Ventana de cliente FTP: subir archivo");
        FileChooser fChooser = new FileChooser();
        fChooser.setTitle("Elige un archivo");
        fChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg",
                        "*.gif"));
        File file = fChooser.showOpenDialog(stage);
        if (file != null) {
            boolean ok = iLogic.uploadFile(file, tView.getSelectionModel()
                    .getSelectedItem().getValue().getAbsolutePath());
            if (ok) {
                MyFile uploadedFile = new MyFile(file.getName(), tView.getSelectionModel()
                        .getSelectedItem().getValue().getAbsolutePath(), false);
                TreeItem<MyFile> item = new TreeItem<MyFile>(uploadedFile);
                tView.getSelectionModel().getSelectedItem().getChildren().add(item);
                tView.refresh();
                lblInfo.setText("El archivo ha sido subido correctamente");
                lblInfo.setTextFill(Color.GREEN);
                lblInfo.setVisible(true);
            } else {
                lblInfo.setText("Ha habido un problema al subir el archivo");
                lblInfo.setTextFill(Color.RED);
                lblInfo.setVisible(true);
            }
        }
    }

    /**
     * Event listener for Download file button.
     *
     * @param ev ActionEvent
     */
    private void downloadFile(ActionEvent ev) {
        LOGGER.info("Ventana de cliente FTP: descargar archivo");
        DirectoryChooser dchooser = new DirectoryChooser();
        dchooser.setTitle("Elige donde guardar");
        File dir = dchooser.showDialog(stage);
        if (dir != null) {
            boolean ok = iLogic.downloadFile(dir, tView.getSelectionModel()
                    .getSelectedItem().getValue());
            if (ok) {
                lblInfo.setText("El archivo ha sido descargado correctamente en "
                        + dir.getAbsolutePath());
                lblInfo.setTextFill(Color.GREEN);
                lblInfo.setVisible(true);
            } else {
                lblInfo.setText("Ha habido un problema al descargar el archivo");
                lblInfo.setTextFill(Color.RED);
                lblInfo.setVisible(true);
            }
        }

    }

    /**
     * Event listener for delete file button.
     *
     * @param ev AvtionEvent
     */
    private void deleteFile(ActionEvent ev) {
        LOGGER.info("Ventana de cliente FTP: borrar archivo");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de que deseas borrar el archivo?");
        alert.setHeaderText("Confirmación");

        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("okButton");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("cancelButton");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                boolean ok = iLogic.deleteFile(tView.getSelectionModel()
                        .getSelectedItem().getValue().getAbsolutePath());
                if (ok) {
                    TreeItem c = (TreeItem) tView.getSelectionModel().getSelectedItem();
                    c.getParent().getChildren().remove(c);
                    tView.refresh();
                    lblInfo.setText("El archivo ha sido eliminado correctamente");
                    lblInfo.setTextFill(Color.GREEN);
                    lblInfo.setVisible(true);
                } else {
                    lblInfo.setText("Ha habido un problema al borrar el archivo");
                    lblInfo.setTextFill(Color.RED);
                    lblInfo.setVisible(true);
                }
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "FTPClient: io exception borrando archivo ",
                        ex.getCause());
            }
        }
    }

    /**
     * Event listener for Create directory button.
     *
     * @param ev ActionEvent
     */
    private void createDir(ActionEvent ev) {
        LOGGER.info("Ventana de cliente FTP: crear directorio");
        try {
            TextInputDialog dialog = new TextInputDialog("Introduzca un nombre");
            dialog.setTitle("Introduzca un nombre");
            dialog.setHeaderText("Introduzca un nombre para la carpeta:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                MyFile newDir = new MyFile(result.get(), tView.getSelectionModel()
                        .getSelectedItem().getValue().getAbsolutePath(), true);
                boolean ok = iLogic.makeDirectory(newDir.getAbsolutePath());
                if (ok) {
                    TreeItem<MyFile> dir = new TreeItem<MyFile>(newDir, new ImageView(folder));
                    TreeItem<MyFile> node0 = new TreeItem<MyFile>();
                    tView.getSelectionModel().getSelectedItem().getChildren().add(dir);
                    dir.getChildren().add(node0);
                    dir.addEventHandler(TreeItem.branchExpandedEvent(), handleExpand);
                    tView.refresh();
                    lblInfo.setText("Se ha creado una carpeta nueva correctamente");
                    lblInfo.setTextFill(Color.GREEN);
                    lblInfo.setVisible(true);
                } else {
                    lblInfo.setText("Ha habido un problema al crear la carpeta");
                    lblInfo.setTextFill(Color.RED);
                    lblInfo.setVisible(true);
                }
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "FTPClient:Io exception creando directorio ",
                    ex.getCause());
        }
    }

    /**
     * Event listener for Delete directory button.
     *
     * @param ev ActionEvent
     */
    private void deleteDir(ActionEvent ev) {
        LOGGER.info("Ventana de cliente FTP: borrar directorio");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Estas seguro de que deseas borrar el directorio?");
        alert.setHeaderText("Confirmación");

        Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        okButton.setId("okButton");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setId("cancelButton");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                boolean ok = iLogic.removeDirectory(tView.getSelectionModel()
                        .getSelectedItem().getValue().getAbsolutePath());
                if (ok) {
                    TreeItem c = (TreeItem) tView.getSelectionModel().getSelectedItem();
                    c.getParent().getChildren().remove(c);
                    tView.refresh();
                    lblInfo.setText("La carpeta ha sido eliminada correctamente");
                    lblInfo.setTextFill(Color.GREEN);
                    lblInfo.setVisible(true);
                } else {
                    lblInfo.setText("La carpeta tiene que estar vacía");
                    lblInfo.setTextFill(Color.RED);
                    lblInfo.setVisible(true);
                }
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "FTPClient: io exception borrando directorio ",
                        ex.getCause());
                lblInfo.setText("Ha habido un problema borrando la carpeta");
                    lblInfo.setTextFill(Color.RED);
                    lblInfo.setVisible(true);
            }
        }
    }

}
