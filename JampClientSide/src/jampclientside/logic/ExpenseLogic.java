/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.UserBean;
import java.util.Collection;


/**
 *
 * @author WIN10
 */
public interface ExpenseLogic {

    public Collection getAllExpense();

    public Collection<UserBean> getAllUsers();
    public Collection getThisMonthExpense();

}
