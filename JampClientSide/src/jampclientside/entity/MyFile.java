/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Bean used for the creation of objects when creating every item of the shared
 * folder by ftp.
 *
 * @author Ander
 */
public class MyFile {

    private SimpleStringProperty name;
    private SimpleStringProperty path;
    private SimpleBooleanProperty isDirectory;

    public MyFile() {
        this.name = new SimpleStringProperty();
        this.path = new SimpleStringProperty();
        this.isDirectory = new SimpleBooleanProperty();
    }

    public MyFile(String name, String path, boolean isDirectory) {
        this.name = new SimpleStringProperty(name);
        this.path = new SimpleStringProperty(path);
        this.isDirectory = new SimpleBooleanProperty(isDirectory);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path.get();
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path.set(path);
    }

    /**
     * @return the isDirectory
     */
    public boolean isIsDirectory() {
        return isDirectory.get();
    }

    /**
     * @param isDirectory the isDirectory to set
     */
    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory.set(isDirectory);
    }

    /**
     * To String method
     *
     * @return the name of the object.
     */
    public String toString() {
        return name.get();
    }

    /**
     * Gets the absolute path of the object MyFile.
     *
     * @return String of absolute path of the object MyFile.
     */
    public String getAbsolutePath() {
        String absolutePath = null;
        if (this.path.get().equals("/")) {
            absolutePath = "/" + this.name.get();
        } else {
            absolutePath = this.path.get() + "/" + this.name.get();
        }
        return absolutePath;
    }
}
