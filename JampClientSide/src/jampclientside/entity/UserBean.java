/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WIN10
 */
public class UserBean implements Serializable{
    private SimpleIntegerProperty idUser;
    private SimpleObjectProperty<ExpenseBean>expense;
    private SimpleObjectProperty<EventBean>events;
    private SimpleObjectProperty<TxokoBean>txoko;
    private SimpleStringProperty login;
    private SimpleStringProperty email;
    private SimpleStringProperty fullName;
    private SimpleObjectProperty<UserStatus> userStatus;
    private SimpleObjectProperty<UserPrivilege> userPrivilege;
    private SimpleStringProperty password;
    private SimpleObjectProperty<LocalDateTime> lastAccess;
    private SimpleObjectProperty<LocalDateTime> lastPasswordChange;
    
    public UserBean(){
     this.idUser = new SimpleIntegerProperty();
     this.expense = new SimpleObjectProperty<>();
     this.events = new SimpleObjectProperty<>();
     this.txoko = new SimpleObjectProperty<>();
     this.login = new SimpleStringProperty();
     this.email = new SimpleStringProperty ();
     this.fullName = new SimpleStringProperty();
     this.userStatus = new SimpleObjectProperty<>();
     this.userPrivilege = new SimpleObjectProperty<>();
     this.password = new SimpleStringProperty();
     this.lastAccess = new SimpleObjectProperty<>();
     this.lastPasswordChange = new SimpleObjectProperty<>();
   
     }

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return this.idUser.get();
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(Integer idUser) {
        this.idUser.set(idUser);
    }

    /**
     * @return the expense
     */
    public ExpenseBean getExpense() {
        return this.expense.get();
    }

    /**
     * @param expense the expense to set
     */
    public void setExpense(ExpenseBean expense) {
        this.expense.set(expense);
    }

    /**
     * @return the events
     */
    public EventBean getEvents() {
        return this.events.get();
    }

    /**
     * @param events the events to set
     */
    public void setEvents(EventBean events) {
        this.events.set(events);
    }

    /**
     * @return the txoko
     */
    public TxokoBean getTxoko() {
        return this.txoko.get();
    }

    /**
     * @param txoko the txoko to set
     */
    public void setTxoko(TxokoBean txoko) {
        this.txoko.set(txoko);
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return this.login.get();
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login.set(login);
    }
  
     @Override
    public String toString() {
        return this.login.get();
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return this.email.get();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return this.fullName.get();
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    /**
     * @return the userStatus
     */
    public UserStatus getUserStatus() {
        return this.userStatus.get();
    }

    /**
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus.set(userStatus);
    }

    /**
     * @return the userPrivilege
     */
    public UserPrivilege getUserPrivilege() {
        return this.userPrivilege.get();
    }

    /**
     * @param userPrivilege the userPrivilege to set
     */
    public void setUserPrivilege(UserPrivilege userPrivilege) {
        this.userPrivilege.set(userPrivilege);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return this.password.get();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     * @return the lastAccess
     */
    public LocalDateTime getLastAccess() {
        return this.lastAccess.get();
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess.set(lastAccess);
    }

    /**
     * @return the lastPasswordChange
     */
    public LocalDateTime getLastPasswordChange() {
        return this.lastPasswordChange.get();
    }

    /**
     * @param lastPasswordChange the lastPasswordChange to set
     */
    public void setLastPasswordChange(LocalDateTime lastPasswordChange) {
        this.lastPasswordChange.set(lastPasswordChange);
    }

    
    
    
}
