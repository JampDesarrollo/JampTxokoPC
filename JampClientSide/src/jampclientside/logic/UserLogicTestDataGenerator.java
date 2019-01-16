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
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Ander
 */
public class UserLogicTestDataGenerator implements UserLogic {
    private static final Logger LOGGER=Logger.getLogger("jampclientside.logic");
    private ArrayList<UserBean> users;
    private ArrayList<TxokoBean> txokos;
    
    public UserLogicTestDataGenerator(){
        users = new ArrayList<>();
        
        for(int i=0;i<15;i++)
            users.add(new UserBean(i, "login"+i, "email"+i, "fullname"+i,
                    UserStatus.ENABLED, UserPrivilege.USER));
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
    public void deleteUser(UserBean user) throws BusinessLogicException {
     users.remove(user);
    }

    @Override
    public UserBean findUserByLoginPassw(String login, String passw) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean findUserForgotPassw(String login) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
