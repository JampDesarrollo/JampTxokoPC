/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.TelephoneBean;
import jampclientside.exceptions.BusinessLogicException;
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
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    public void deleteTelephone(TelephoneBean phone) throws BusinessLogicException;
    
    /**
     * 
     * @param phone 
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    public void updateTelephone(TelephoneBean phone) throws BusinessLogicException;
    
    /**
     * 
     * @param phone 
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    public void createTelephone(TelephoneBean phone) throws BusinessLogicException;
    
    /**
     * 
     * @return 
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    public List<TelephoneBean> findAllTelephone()throws BusinessLogicException;
    
    /**
     * 
     * @param idTelephone
     * @return 
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    public TelephoneBean findTelephoneById(Integer idTelephone)throws BusinessLogicException;
    
    /**
     * 
     * @param name
     * @return 
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    public List<TelephoneBean> findTelephoneByName(String name)throws BusinessLogicException;
    

    
}
