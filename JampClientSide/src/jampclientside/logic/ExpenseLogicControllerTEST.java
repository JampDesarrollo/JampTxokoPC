/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.ExpenseBean;
import jampclientside.entity.UserBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.ReadException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final LocalDateTime fechaActual = LocalDateTime.now();

    public ExpenseLogicControllerTEST() {
        //primero obtenemos los usuarios
        users = new ArrayList();
        expense = new ArrayList();
        //Create DepartmentBean fake data for users data.
        UserBean user = new UserBean();
        user.setLogin("paula");
        users.add(user);
    /*    for (float i = 0; i < 25; i++) {
            expense.add(new ExpenseBean(fechaActual, user, "Evento" + i, "Cumple" + i, i));
        }
        LOGGER.info("El usuario es "+user.getLogin());*/
    }

    @Override
    public Collection<ExpenseBean> findAllExpensesUsers(String idTxoko)throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<ExpenseBean> findMonthExpensesUsers(String idTxoko)throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void CreateExpense(ExpenseBean expense)throws BusinessLogicException  {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Float findMonthExpensesSingleUser(Integer idUser) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
