/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data Transfer Object used in UI and client side for representing User entity.
 * It is also used as data model for a TableView in the UI.
 *
 * @author Ander
 */
@XmlRootElement(name = "user")
public class UserBean implements Serializable {

    private SimpleObjectProperty<ExpenseBean> expenses;
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
    private SimpleStringProperty password;
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

    /**
     *
     */
    private SimpleStringProperty lastPasswordChange;

    /**
     * Empty contructor
     */
    public UserBean() {
        this.expenses = new SimpleObjectProperty();
        this.txoko = new SimpleObjectProperty();
        this.idUser = new SimpleIntegerProperty();
        this.login = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.fullname = new SimpleStringProperty();
        this.status = new SimpleObjectProperty<>();
        this.privilege = new SimpleObjectProperty<>();
        this.lastAccess = new SimpleStringProperty();
        this.lastPasswordChange = new SimpleStringProperty();
    }

    /**
     * Full constructor
     *
     * @param idUser id user
     * @param login login
     * @param email email
     * @param fullname fullname
     * @param status status
     * @param privilege privilege
     */
    public UserBean(Integer idUser, String login, String email,
            String fullname, UserStatus status, UserPrivilege privilege) {
        this.idUser = new SimpleIntegerProperty(idUser);
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.fullname = new SimpleStringProperty(fullname);
        this.status = new SimpleObjectProperty<>(status);
        this.privilege = new SimpleObjectProperty<>(privilege);
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
     * @return the lastPasswordChange
     */
    public String getLastPasswordChange() {
        return this.lastPasswordChange.get();
    }

    /**
     * @param lastPasswordChange the lastPasswordChange to set
     */
    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange.set(lastPasswordChange);
    }
}
