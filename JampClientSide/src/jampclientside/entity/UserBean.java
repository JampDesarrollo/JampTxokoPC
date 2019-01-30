/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ander
 */
@XmlRootElement(name="users")
public class UserBean implements Serializable {

    /**
     *
     */
    private SimpleIntegerProperty idUser;
    /**
     *
     */

    private SimpleObjectProperty<TxokoBean> txoko;
    /**
     *
     */

    private SimpleStringProperty login;
    /**
     *
     */
    private SimpleStringProperty email;
    /**
     *
     */
    private SimpleStringProperty fullname;
    /**
     *
     */

    private SimpleObjectProperty<UserStatus> status;
    /**
     *
     */

    private SimpleObjectProperty<UserPrivilege> privilege;
    /**
     *
     */

    private SimpleStringProperty lastAccess;
    
    private List<EventBean>events;
    private SimpleObjectProperty<ExpenseBean>expenses;

    public UserBean() {
        this.idUser = new SimpleIntegerProperty();
        this.txoko = new SimpleObjectProperty<>();
        this.login = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.fullname = new SimpleStringProperty();
        this.status = new SimpleObjectProperty<>();
        this.privilege = new SimpleObjectProperty<>();
        this.lastAccess = new SimpleStringProperty();
        this.events = events;
        this.expenses=new SimpleObjectProperty<>();
       

    }

    public UserBean(Integer idUser, String login, String email,
            String fullname, UserStatus status, UserPrivilege privilege) {
        this.idUser = new SimpleIntegerProperty(idUser);
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.fullname = new SimpleStringProperty(fullname);
        this.status = new SimpleObjectProperty<UserStatus>(status);
        this.privilege = new SimpleObjectProperty<UserPrivilege>(privilege);
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
     * @return the idTxoko
     */
    public TxokoBean getTxoko() {
        return this.txoko.get();
    }

    /**
     *
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
     * @return the fullname
     */
    public String getFullname() {
        return this.fullname.get();
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname.set(fullname);
    }

    /**
     * @return the status
     */
    public UserStatus getStatus() {
        return this.status.get();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status.set(status);
    }

    /**
     * @return the privilege
     */
    public UserPrivilege getPrivilege() {
        return this.privilege.get();
    }

    /**
     * @param privilege the privilege to set
     */
    public void setPrivilege(UserPrivilege privilege) {
        this.privilege.set(privilege);
    }

    /**
     * @return the lastAccess
     */
    public String getLastAccess() {
        return this.lastAccess.get();
    }

    /**
     * @param lastAccess the lastAccess to set
     */
    public void setLastAccess(String lastAccess) {
        this.lastAccess.set(lastAccess);
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
     * @return the expenses
     */
    public ExpenseBean getExpenses() {
        return this.expenses.get();
    }

    /**
     * @param expenses the expenses to set
     */
    public void setExpenses(ExpenseBean expenses) {
        this.expenses.set(expenses);
    }
    
    
}
