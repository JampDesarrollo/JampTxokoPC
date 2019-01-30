/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.MyFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * This class implements logic interface to access ftp client management
 * methods.
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
            = Logger.getLogger("jampclientside.logic");

    /**
     * This methor creates connection and logs in to a filezilla server.
     *
     * @param ip Ip address of filezilla server.
     * @param port Port filezilla server listens to for connections.
     * @param username Username used for log in.
     * @param passw password used for log in.
     * @return Boolean if connection ok.
     */
    @Override
    public Boolean connectFTP(String ip, String port, String username, String passw) {
        Boolean connected = false;
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(
                new PrintCommandListener(new PrintWriter(System.out)));
        try {
            ftp.connect(ip, Integer.parseInt(port));
            ftp.enterRemotePassiveMode();
            System.out.println(username + " " + passw);
            ftp.login(username, passw);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            connected = true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se ha podido conectar "
                    + "con servidor FTP. {0} \n ", e.getCause());
        }
        return connected;
    }

    /**
     * List files of the folder shared.
     *
     * @param path Path of the directory to be listed.
     * @return ArrayList of the files in that path.
     */
    @Override
    public ArrayList<MyFile> listFiles(MyFile path) {
        FTPFile[] ftpFfiles = null;
        ArrayList<MyFile> array = new ArrayList<MyFile>();
        try {
            ftp.changeWorkingDirectory(path.getAbsolutePath());
            ftpFfiles = ftp.listFiles(path.getAbsolutePath());
            for (FTPFile file : ftpFfiles) {
                MyFile elFile = new MyFile();
                elFile.setName(file.getName());
                if (file.isDirectory()) {
                    elFile.setIsDirectory(true);
                } else {
                    elFile.setIsDirectory(false);
                }
                elFile.setPath(path.getAbsolutePath());
                array.add(elFile);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "No se ha podido listar "
                    + "de servidor FTP. {0} \n ", ex.getCause());
        }
        return array;
    }

    /**
     * This method logs out and disconnects fomr filezilla server.
     */
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

    /**
     * Uploads a file to a specific path.
     *
     * @param file File to upload.
     * @param path Where to upload th file.
     * @return Boolean if ok.
     */
    @Override
    public boolean uploadFile(File file, String path) {
        FileInputStream fis = null;
        boolean ok = false;
        try {
            fis = new FileInputStream(file.getAbsolutePath());
            ok = ftp.storeFile(path + "/" + file.getName(), fis);
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
        return ok;
    }

    /**
     * Downloads a file.
     *
     * @param file Where the file is going to be downloaded.
     * @param myFile The file to be downloaded.
     * @return Boolean if all ok.
     */
    @Override
    public boolean downloadFile(File file, MyFile myFile) {
        //FileOutputStream out = null;
        OutputStream outputStream = null;
        boolean ok = false;
        try {
            outputStream = new FileOutputStream(file.getAbsolutePath() + "/" + myFile.getName());
            ok = ftp.retrieveFile(myFile.getName(), outputStream);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {

            }
        }
        return ok;
    }

    /**
     * Deletes a file.
     *
     * @param path Where the file to be deleted is.
     * @return Boolean if all ok.
     * @throws IOException Exception.
     */
    @Override
    public boolean deleteFile(String path) throws IOException {
        boolean ok = ftp.deleteFile(path);
        return ok;
    }

    /**
     * Creates a directory.
     *
     * @param path Where the directory is going to be created.
     * @return Boolean if all ok.
     * @throws IOException Exception.
     */
    @Override
    public boolean makeDirectory(String path) throws IOException {
        boolean ok = ftp.makeDirectory(path);
        return ok;
    }

    /**
     * Remove a directory.
     *
     * @param path Wheres the directory to be deleted.
     * @return Boolean if all ok.
     * @throws IOException Exception.
     */
    @Override
    public boolean removeDirectory(String path) throws IOException {
        boolean ok = ftp.removeDirectory(path);
        return ok;
    }

}
