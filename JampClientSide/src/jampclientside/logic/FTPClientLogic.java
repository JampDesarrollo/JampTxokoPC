/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import java.io.File;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Ander
 */
public interface FTPClientLogic {

    public Boolean connectFTP(String ip, String port, String username, String passw);

    public void disconnectFTP();

    public FTPFile[] listFiles(String path);

    public void uploadFile(File file);

    public void downloadFile();

    public void deleteFile() throws IOException;

    public void createDirectory() throws IOException;

    public void deleteDirectory() throws IOException;
}
