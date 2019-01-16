/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.UserBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.rest.UserRESTClient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Ander
 */
public class UserLogicController implements UserLogic {
    private UserRESTClient webClient;
    /**
     * Logger for the class.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jampclientside.logic");
    
    public UserLogicController(){
        webClient = new UserRESTClient();
    }

    @Override
    public List<UserBean> findAllUsers(Integer idTxoko) throws BusinessLogicException {
        List<UserBean> users = null;
        try {
            LOGGER.info("UserLogicController: Reading all users.");
           // users = webClient.findAllTxokoUsers(new GenericType<List<UserBean>>(), idTxoko);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController: Exception reading all users:",
                    e.getMessage());
            throw new BusinessLogicException("Error findin all users: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void updateUser(UserBean user) throws BusinessLogicException {
        try {
            LOGGER.log(Level.INFO,"UserLogicController: Updating user {0}.",user.getLogin());
            webClient.updateUser(user);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController: Exception updating user.{0}",
                    e.getMessage());
            throw new BusinessLogicException("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(UserBean user) throws BusinessLogicException {
       try {
            LOGGER.log(Level.INFO,"UserLogicController: Deleting user {0}.",user.getLogin());
            webClient.deleteUser(user.getIdUser());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController: Exception deleting user.{0}",
                    e.getMessage());
            throw new BusinessLogicException("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public UserBean findUserByLoginPassw(String login, String passw) throws BusinessLogicException {
       UserBean user = null;
        try {
            LOGGER.info("UserLogicController: finding a user by login and passw.");
          //  user = webClient.findUserByLoginPasswPC(new GenericType<UserBean>, login, passw);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController: finding a user by login and passw:",
                    e.getMessage());
            throw new BusinessLogicException("Error finding user: " + e.getMessage());
        }
        return user;
    }

    @Override
    public Boolean findUserForgotPassw(String login) throws BusinessLogicException {
     Boolean allOk = false;
        try {
            LOGGER.info("UserLogicController: Reading all users.");
           // allOk = webClient.findUserForgotPassword(new GenericType<>, login);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserLogicController: finding a user by login:",
                    e.getMessage());
            throw new BusinessLogicException("Error finding user: " + e.getMessage());
        }
        return allOk;
    }
    
}
