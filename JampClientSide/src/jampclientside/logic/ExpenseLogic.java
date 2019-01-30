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
import java.util.Collection;


/**
 * the expenses interface
 * @author paula
 */
public interface ExpenseLogic {
    /**
     * Method to find all expenses of my txoko
     * @param idTxoko the id of the txoko
     * @return it returns a collection of the expenses of the txoko
     * @throws BusinessLogicException  throws this exceptions if something is wrong. 
     */
    public Collection<ExpenseBean> findAllExpensesUsers(String idTxoko)throws BusinessLogicException ;
    /**
     * Create an expense
     * @param expense the expense we want to create
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */
    public void CreateExpense(ExpenseBean expense)throws BusinessLogicException ;
    /**
     * Method to konow the expenses of 1 month
     * @param idTxoko the id of the txoko
     * @return returns the expenses of 1 month
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */
    public Collection<ExpenseBean> findMonthExpensesUsers(String idTxoko)throws BusinessLogicException ;
    /**
     * Method to know the expenses of 1 month of one user
     * @param idUser the id of the user
     * @return returns the expense
     * @throws BusinessLogicException  throws this exceptions if something is wrong.
     */
    public Float findMonthExpensesSingleUser(Integer idUser)throws BusinessLogicException ;

}
