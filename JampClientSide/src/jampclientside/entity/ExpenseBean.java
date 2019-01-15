/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WIN10
 */
public class ExpenseBean implements Serializable {

    private SimpleIntegerProperty idExpense;
    private Date date;
    private SimpleObjectProperty<UserBean> user;
    private SimpleStringProperty type;
    private SimpleStringProperty description;
    private SimpleFloatProperty price;

    public ExpenseBean() {
        this.idExpense = new SimpleIntegerProperty();
        this.date = new Date();
        this.user = new SimpleObjectProperty();
        this.type = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.price = new SimpleFloatProperty();
    }

    public ExpenseBean(UserBean user, String type,
            String description,
            Float price) {
        this.user = new SimpleObjectProperty(user);
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleFloatProperty(price);
    }


    /**
     * @return the idExpense
     */
    public Integer getIdExpense() {
        return this.idExpense.get();
    }

    /**
     * @param idExpense the idExpense to set
     */
    public void setIdExpense(Integer idExpense) {
        this.idExpense.set(idExpense);
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the user
     */
    public UserBean getUser() {
        return this.user.get();
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user) {
        this.user.set(user);
    }

    /**
     * @return the type
     */
    public String getType() {
        return this.type.get();
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type.set(type);
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
     * @return the price
     */
    public Float getPrice() {
        return this.price.get();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price.set(price);
    }

}
