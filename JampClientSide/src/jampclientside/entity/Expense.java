/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Entity JPA class for Expenses. The properties of this class are idUser ,
 * date, type, description and price.
 *
 * @author ander
 */
public class Expense implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *
     */
    private Integer idExpense;
   
    /**
     * Id of the user.
     */
    private UserBean user;
    
    /**
     * Date of the expense.
     */
    private Timestamp dateExpense;
    
    /**
     * Type of expense.
     */
    private String type;
    
    /**
     * Long description of the expense.
     */
    private String description;
    
    /**
     * Price of the expense.
     */
    private Float price;

    /**
     * @return the idExpense
     */
    public Integer getIdExpense() {
        return idExpense;
    }

    /**
     * @param idExpense the idExpense to set
     */
    public void setIdExpense(Integer idExpense) {
        this.idExpense = idExpense;
    }

    /**
     * @return the idUser
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setUser(UserBean user) {
        this.user = user;
    }

    /**
     * @return the dateExpense
     */
    public Timestamp getDateExpense() {
        return dateExpense;
    }

    /**
     * @param dateExpense the dateExpense to set
     */
    public void setDateExpense(Timestamp dateExpense) {
        this.dateExpense = dateExpense;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

}
