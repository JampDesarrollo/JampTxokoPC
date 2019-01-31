/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.logic.UserLogic;



/**
 * Clase que devuelve objetos que implementan la interfaz. Si yo necesito un
 * objeto de la logica, hay que pedirselo a la factoria. Class that returns the
 * objects that implements the interface
 *
 * @author Julen
 */
public class ILogicFactory {

    /**
     * Metodo que va a devolver un nuevo objeto que implementa la interfaz.
     * Method that returns the new object of the implementation
     *
     * @return getIlogic
     */
    
    public static UserLogic getUserLogic() {
        return new UserLogicController();
    }
    public static EventLogic getEventLogic() {
       // return new EventLogicControllerTEST();
        return new EventLogicController();
    }
    
     public static ProductLogic getProductLogic() {
        return new ProductLogicController();
    } 
     public static ExpenseLogic getExpenseLogic() {
     //   return new ExpenseLogicControllerTEST();
        return new ExpenseLogicController();
    }
    
     public static TelephoneLogic getTelephoneLogic() {
        return new TelephoneLogicController();
    } 
     
    public static FTPClientLogic getFTPClientLogic() {
        return new FTPClientLogicController();
    }
}
