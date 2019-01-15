/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.TelephoneBean;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.DeleteException;
import jampclientside.exceptions.ReadException;
import jampclientside.exceptions.UpdateException;
import java.util.List;
import java.util.logging.Logger;
import messageuserbean.UserBean;

/**
 *
 * Clase que implementa la interfaz de Logica. Class that implements the logic
 * interface.
 *
 * @author Ander
 */
public class TelephoneLogicController implements TelephoneLogic {

    private UserBean returnUser;

    /**
     * Atributo para poder sacar textos de información. Attribute to appear the
     * information text.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jamp.pc.logic.IlogicImplementationTelephone");

    /**
     * 
     * @param phone
     * @throws DeleteException 
     */
    @Override
    public void deleteTelephone(TelephoneBean phone) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param phone
     * @throws UpdateException 
     */
    @Override
    public void updateTelephone(TelephoneBean phone) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param phone
     * @throws CreateException 
     */
    @Override
    public void createTelephone(TelephoneBean phone) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return
     * @throws ReadException 
     */
    @Override
    public List<TelephoneBean> findAllTelephone(){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}