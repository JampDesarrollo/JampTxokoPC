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
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author Usuario
 */
public class EventBean {
     private SimpleIntegerProperty idEvent;
     private SimpleStringProperty description;
     private SimpleFloatProperty price;
     private SimpleStringProperty name;
     private SimpleStringProperty img;
     private SimpleDateFormat date;
     
     public EventBean(Integer idEvent, String description, Float price, String name, String img, Date date){
     this.idEvent = new SimpleIntegerProperty(idEvent);
     this.description = new SimpleStringProperty(description);
     this.price = new SimpleFloatProperty (price);
     this.name = new SimpleStringProperty (name);
     this.img = new SimpleStringProperty(img);
    // this.date = new SimpleDateFormat(date);
     }

    /**
     * @return the idEvent
     */
    public Integer getIdEvent() {
        return this.idEvent.get();
    }

    /**
     * @param idEvent the idEvent to set
     */
    public void setIdEvent(Integer idEvent) {
        this.idEvent.set(idEvent);
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
     * @return the img
     */
    public String getImg() {
        return this.img.get();
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img.set(img);
    }

    /**
     * @return the date
     */
    /*
    public Date getDate() {
        return this.date.get();
    }

    /**
     * @param date the date to set
     */
    /*
    public void setDate(Date date) {
        this.date.set(date);
    }
     */
  
}