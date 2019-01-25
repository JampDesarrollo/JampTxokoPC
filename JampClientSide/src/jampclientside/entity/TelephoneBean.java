  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Julen
 */
public class TelephoneBean implements Serializable{
    private SimpleIntegerProperty idTelephone;
    private SimpleStringProperty name;
    private SimpleStringProperty telephone;
    private SimpleStringProperty description;
    private SimpleStringProperty town;

    public TelephoneBean(){
        this.idTelephone = new SimpleIntegerProperty();
        this.description = new SimpleStringProperty();
        this.telephone = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.town = new SimpleStringProperty();
     }
     
    public TelephoneBean(String name,
                    String description,
                    String telephone,
                    String town){
        this.name=new SimpleStringProperty(name);
        this.description=new SimpleStringProperty(description);
        this.telephone=new SimpleStringProperty(telephone);
        this.town = new SimpleStringProperty(town);
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
    public String getTelephon() {
        return this.telephone.get();
    }

    /**
     * @param telephon the telephon to set
     */
    public void setTelephon(String telephone) {
        this.telephone.set(telephone);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description.get();
    }
    
    /**
     * 
     * @return 
     */
    public String getTown() {
        return this.town.get();
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town.set(town);
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description.set(description);
    }
        public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelephoneBean)) {
            return false;
        }
        TelephoneBean other = (TelephoneBean) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.idTelephone.equals(other.idTelephone))) {
            return false;
        }
        if ((this.getName() == null && other.getName() != null) || (this.getName() != null && !this.name.equals(other.name))) {
            return false;
        }
        if ((this.getDescription()== null && other.getDescription()!= null) || (this.getDescription() != null && !this.description.equals(other.description))) {
            return false;
        }
        if ((this.getTelephon()== null && other.getTelephon()!= null) || (this.getTelephon() != null && !this.telephone.equals(other.telephone))) {
            return false;
        }
        if ((this.getTown()== null && other.getTown()!= null) || (this.getTown() != null && !this.town.equals(other.town))) {
            return false;
        }

        return true;
    }

    /**
     * @return the town
     */
}
