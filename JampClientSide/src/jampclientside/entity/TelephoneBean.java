  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Julen
 */
public class TelephoneBean implements Serializable{
    private SimpleIntegerProperty idTelephone;
    private SimpleStringProperty name;
    private SimpleIntegerProperty telephone;
    private SimpleStringProperty description;
    private SimpleBooleanProperty venta;

    public TelephoneBean(){
        this.idTelephone = new SimpleIntegerProperty();
        this.description = new SimpleStringProperty();
        this.telephone = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.venta = new SimpleBooleanProperty();
     }
     
    public TelephoneBean(String name,
                    String description,
                    Integer telephone,
                    Boolean venta){
        this.name=new SimpleStringProperty(name);
        this.description=new SimpleStringProperty(description);
        this.telephone=new SimpleIntegerProperty(telephone);
        this.venta = new SimpleBooleanProperty(venta);
    }
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
    public String getName() {
        return this.name.get();
    }

    /**
     * @param nombre the nombre to set
     */
    public void setName(String nombre) {
        this.name.set(nombre);
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

    /**
     * @return the venta
     */
    public Boolean getVenta() {
        return this.venta.get();
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(Boolean venta) {
        this.venta.set(venta);
    }

}
