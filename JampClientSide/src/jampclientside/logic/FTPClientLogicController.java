/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.ui.controller.PC06FTPClientController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Ander
 */
public class FTPClientLogicController implements FTPClientLogic {

    /**
     * FTPClient object.
     */
    private FTPClient ftp;

    /**
     * Logger object used to log messages of this controller.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jampclientside.ui.controller");

    @Override
    public Boolean connectFTP(String ip, String port, String username, String passw) {
        Boolean connected = false;
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(
                new PrintCommandListener(new PrintWriter(System.out)));
        try {
            ftp.connect(ip, Integer.parseInt(port));
            ftp.enterRemotePassiveMode();
            ftp.login(username, passw);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            connected = true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se ha podido conectar "
                    + "con servidor FTP. {0} \n ", e.getCause());
        }
        return connected;
    }

    @Override
    public FTPFile[] listFiles(String path) {
        FTPFile[] files = null;
        try {
            files = ftp.listFiles(path);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "No se ha podido listar "
                    + "de servidor FTP. {0} \n ", ex.getCause());
        }
        return files;
    }

    @Override
    public void disconnectFTP() {
        try {
            ftp.logout();
            ftp.disconnect();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "No se ha podido desconectar "
                    + "con servidor FTP. {0} \n ", ex.getCause());
        }
    }

    @Override
    public void uploadFile(File file) {
        FileInputStream fis = null;

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

    @Override
    public void downloadFile() {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("/");
            ftp.retrieveFile("/", out);
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
               
            }
        }
    }

    @Override
    public void deleteFile() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createDirectory() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDirectory() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
