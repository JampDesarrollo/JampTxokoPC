/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.MyFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *  Logic interface encapsulating business methods for ftp client management.
 * @author Ander
 */
public interface FTPClientLogic {
    /**
     * This methor creates connection and logs in to a filezilla server.
     * @param ip Ip address of filezilla server.
     * @param port Port filezilla server listens to for connections.
     * @param username Username used for log in.
     * @param passw password used for log in.
     * @return Boolean if connection ok.
     */
    public Boolean connectFTP(String ip, String port, String username, String passw);
    /**
     * This method logs out and disconnects fomr filezilla server.
     */
    public void disconnectFTP();
    /**
     * List files of the folder shared.
     * @param path Path of the directory to be listed.
     * @return ArrayList of the files in that path.
     */
    public ArrayList<MyFile> listFiles(MyFile path);
    /**
     * Uploads a file to a specific path.
     * @param file File to upload.
     * @param path Where to upload th file.
     * @return Boolean if ok.
     */
    public boolean uploadFile(File file, String path);
    /**
     * Downloads a file.
     * @param file Where the file is going to be downloaded.
     * @param myFile The file to be downloaded.
     * @return Boolean if all ok.
     */
    public boolean downloadFile(File file, MyFile myFile);
    /**
     * Deletes a file.
     * @param path Where the file to be deleted is.
     * @return Boolean if all ok.
     * @throws IOException Exception.
     */
    public boolean deleteFile(String path) throws IOException;
    /**
     * Creates a directory.
     * @param path Where the directory is going to be created.
     * @return Boolean if all ok.
     * @throws IOException Exception.
     */
    public boolean makeDirectory(String path) throws IOException;
    /**
     * Remove a directory.
     * @param path Wheres the directory to be deleted.
     * @return Boolean if all ok.
     * @throws IOException Exception.
     */
    public boolean removeDirectory(String path) throws IOException;
}
