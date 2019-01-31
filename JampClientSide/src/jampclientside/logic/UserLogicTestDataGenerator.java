/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.TxokoBean;
import jampclientside.entity.UserBean;
import jampclientside.entity.UserPrivilege;
import jampclientside.entity.UserStatus;
import jampclientside.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Test class for user tableview.
 * @author Ander
 */
public class UserLogicTestDataGenerator implements UserLogic {

    private static final Logger LOGGER = Logger.getLogger("jampclientside.logic");
    private ArrayList<UserBean> users;
    private ArrayList<TxokoBean> txokos;

    public UserLogicTestDataGenerator() {
        users = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            users.add(new UserBean(i, "login" + i, "email" + i, "fullname" + i,
                    UserStatus.ENABLED, UserPrivilege.USER));
        }
    }

    public Collection findAllUsers(Integer idTxoko) throws BusinessLogicException {
        LOGGER.info("Getting all fake users data for UI.");
        return users;
    }

    @Override
    public void updateUser(UserBean user) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean findUserForgotPassw(String login) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(Integer idUser) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createUser(UserBean user) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserBean findUserByLoginPasswPC(String login, String passw) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserBean findUserByLoginPasswMov(String login, String passw) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean findUserChangePasswMov(Integer idUser, String oldpass, String newPassw) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<UserBean> findAll() throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
