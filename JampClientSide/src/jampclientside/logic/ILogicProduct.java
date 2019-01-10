/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.Product;
import jampclientside.exceptions.CreateException;
import jampclientside.exceptions.DeleteException;
import jampclientside.exceptions.UpdateException;


/**
 * Es la interfaz de lógica. Si hay que hacer una llamada entre dos objetos de
 * diferentes clases, se necesita una interfaz.
 *
 * @author Paula
 */
public interface ILogicProduct {

    public void deleteProduct(Product product) throws DeleteException;
    public void updateProduct(Product product) throws UpdateException;
    public void createProduct(Product product) throws CreateException;
   
    
}
