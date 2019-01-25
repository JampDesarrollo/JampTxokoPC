/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.TelephoneBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.DeleteException;
import jampclientside.exceptions.ReadException;
import jampclientside.exceptions.UpdateException;
import java.util.List;


/**
 * Es la interfaz de lógica de Telefonos. Si hay que hacer una llamada entre dos objetos de
 * diferentes clases, se necesita una interfaz.
 *
 * @author Julen
 */
public interface TelephoneLogic {

    /**
     * 
     * @param phone
     * @throws DeleteException 
     */
    public void deleteTelephone(TelephoneBean phone) throws BusinessLogicException;
    
    /**
     * 
     * @param phone
     * @throws UpdateException 
     */
    public void updateTelephone(TelephoneBean phone) throws BusinessLogicException;
    
    /**
     * 
     * @param phone
     * @throws CreateException 
     */
    public void createTelephone(TelephoneBean phone) throws BusinessLogicException;
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    public List<TelephoneBean> findAllTelephone()throws BusinessLogicException;
    
    /**
     * 
     * @return 
     */
    public List<TelephoneBean> findAllTelephoneByTxoko()throws BusinessLogicException;
    /**
     * 
     * @param <error>
     * @return 
     */
    public List<TelephoneBean> findTelephoneById(Integer idTelephone)throws BusinessLogicException;
    /**
     * 
     * @return 
     */
    public List<TelephoneBean> findTelephoneByName(String name)throws BusinessLogicException;
}
