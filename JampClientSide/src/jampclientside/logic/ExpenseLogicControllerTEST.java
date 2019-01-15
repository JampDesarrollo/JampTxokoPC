/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.ExpenseBean;
import jampclientside.entity.UserBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 *
 * @author WIN10
 */
public class ExpenseLogicControllerTEST implements ExpenseLogic {

    private static final Logger LOGGER = Logger.getLogger("javafxapplicationud3example");
    // List for storing users data.
    private ArrayList<UserBean> users;
    private ArrayList<ExpenseBean> expense;

    public ExpenseLogicControllerTEST() {
        //primero obtenemos los usuarios
        users = new ArrayList();
        expense = new ArrayList();
        //Create DepartmentBean fake data for users data.
        UserBean user = new UserBean();
        user.setLogin("paula");
        users.add(user);

        for (float i = 0; i < 25; i++) {
            expense.add(new ExpenseBean(user, "Evento" + i, "Cumple" + i, i));
        }
    }

    @Override
    public Collection getAllExpense() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return expense;
    }

    @Override
    public Collection<UserBean> getAllUsers() {
        return users;

    }
    
    @Override
     public Collection getThisMonthExpense(){
     return null;
     }


}
