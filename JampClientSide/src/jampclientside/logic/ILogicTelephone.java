/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.Telephone;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.DeleteException;
import jampclientside.exceptions.UpdateException;


/**
 * Es la interfaz de lógica. Si hay que hacer una llamada entre dos objetos de
 * diferentes clases, se necesita una interfaz.
 *
 * @author Paula
 */
public interface ILogicTelephone {

    public void deleteTelephone(Telephone phone) throws DeleteException;
    public void updateTelephone(Telephone phone) throws UpdateException;
    public void createTelephone(Telephone phone) throws CreateException;
}
