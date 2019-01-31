/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@XmlRootElement(name = "txoko")
public class TxokoBean {
    
    private SimpleObjectProperty<UserBean> users;
    private SimpleIntegerProperty idTxoko;
    private SimpleStringProperty direction;
    private SimpleFloatProperty monthFee;
    private SimpleStringProperty name;
    private SimpleStringProperty town;

    public TxokoBean(Integer idTxoko, String direction, Float monthFee, String name, String town) {
        this.idTxoko = new SimpleIntegerProperty(idTxoko);
        this.direction = new SimpleStringProperty(direction);
        this.monthFee = new SimpleFloatProperty(monthFee);
        this.name = new SimpleStringProperty(name);
        this.town = new SimpleStringProperty(town);
    }

    public TxokoBean() {
        this.users = new SimpleObjectProperty<>();
        this.idTxoko = new SimpleIntegerProperty();
        this.direction = new SimpleStringProperty();
        this.monthFee = new SimpleFloatProperty();
        this.name = new SimpleStringProperty();
        this.town = new SimpleStringProperty();
    }

    /**
     * @return the idTxoko
     */
    public Integer getIdTxoko() {
        return this.idTxoko.get();
    }

    /**
     * @param idTxoko the idTxoko to set
     */
    public void setIdTxoko(Integer idTxoko) {
        this.idTxoko.set(idTxoko);
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return this.direction.get();
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    /**
     * @return the monthFee
     */
    public Float getMonthFee() {
        return this.monthFee.get();
    }

    /**
     * @param monthFee the monthFee to set
     */
    public void setMonthFee(Float monthFee) {
        this.monthFee.set(monthFee);
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the town
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

}
