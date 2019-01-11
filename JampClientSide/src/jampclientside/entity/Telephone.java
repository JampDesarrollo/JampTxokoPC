  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Julen
 */
public class Telephone {
    private SimpleIntegerProperty idTelephone;
    private SimpleStringProperty nombre;
    private SimpleIntegerProperty telephone;
    private SimpleStringProperty description;

    /**
     * @return the id
     */
    public Integer getId() {
        return this.idTelephone.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.idTelephone.set(id);
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return this.nombre.get();
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    /**
     * @return the telephon
     */
    public Integer getTelephon() {
        return this.telephone.get();
    }

    /**
     * @param telephon the telephon to set
     */
    public void setTelephon(Integer telephone) {
        this.telephone.set(telephone);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description.get();
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description.set(description);
    }

}
