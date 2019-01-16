/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.UserBean;
import jampclientside.exceptions.BusinessLogicException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Ander
 */
public interface UserLogic {

    public Collection findAllUsers(Integer idTxoko) throws BusinessLogicException;

    public void updateUser(UserBean user) throws BusinessLogicException;

    public void deleteUser(UserBean user) throws BusinessLogicException;

    public UserBean findUserByLoginPassw(String login, String passw) throws BusinessLogicException;

    public Boolean findUserForgotPassw(String login) throws BusinessLogicException;
}
