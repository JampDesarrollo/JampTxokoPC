/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.Product;
import jampclientside.entity.Telephone;
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
 * @author Julen
 */
public class ILogicImplementationProduct implements ILogicProduct {


    private UserBean returnUser;

    /**
     * Atributo para poder sacar textos de información. Attribute to appear the
     * information text.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jamp.pc.logic.IlogicImplementationProduct");

    @Override
    public void deleteProduct(Product product) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduct(Product product) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createProduct(Product product) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Telephone> findProductById(Integer idProduct) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Telephone> findProductByName(String name, Integer idTxoko) throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Telephone> findAllProducts() throws ReadException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
