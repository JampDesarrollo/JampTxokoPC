/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@XmlRootElement(name = "txokos")
public class TxokoBean {

    private String idTxoko;
    private String direction;
    private Float monthFee;
    private String name;
    private String town;
    private List<ProductBean> products;
    private List<UserBean> users;
    private List<EventBean> events;
    //HAY QUE PONER LA RELACION QUE TIENE CON LOS EVENTOS, LOS USUARIOS Y PRODUCTOS

    public TxokoBean() {
        this.idTxoko = idTxoko;
        this.direction = direction;
        this.monthFee =  monthFee;
        this.name = name;
        this.town = town;
        this.products = products;
        this.users = users;
        this.events = events;
    }

    public TxokoBean(String idTxoko, String direction, Float monthFee, String name, String town) {
        this.idTxoko = idTxoko;
        this.direction = direction;
        this.monthFee = monthFee;
        this.name = name;
        this.town = town;
    }
    
    public TxokoBean(String idTxoko){
    this.idTxoko = idTxoko;
    
    }
    /**
     * @return the products
     */
    public List<ProductBean> getProducts() {
        return products;
    }

    /**
     * @param products the products to set
     */
    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

    /**
     * @return the events
     */
    public List<EventBean> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<EventBean> events) {
        this.events = events;
    }

    /**
     * @return the idTxoko
     */
    public String getIdTxoko() {
        return idTxoko;
    }

    /**
     * @param idTxoko the idTxoko to set
     */
    public void setIdTxoko(String idTxoko) {
        this.idTxoko = idTxoko;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the monthFee
     */
    public Float getMonthFee() {
        return monthFee;
    }

    /**
     * @param monthFee the monthFee to set
     */
    public void setMonthFee(Float monthFee) {
        this.monthFee = monthFee;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the users
     */
    public List<UserBean> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<UserBean> users) {
        this.users = users;
    }

}
