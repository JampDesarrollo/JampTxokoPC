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
import jampclientside.exceptions.ProductExist;
import jampclientside.exceptions.ReadException;
import jampclientside.exceptions.UpdateException;
import java.util.List;


/**
 * Es la interfaz de lógica de los productos. Si hay que hacer una llamada entre dos objetos de
 * diferentes clases, se necesita una interfaz.
 *
 * @author Julen
 */
public interface ILogicProduct {


    /**
     * 
     * @param phone
     * @throws DeleteException 
     */
    public void deleteProduct(Product product) throws DeleteException;
    
    /**
     * 
     * @param phone
     * @throws UpdateException 
     */
    public void updateProduct(Product product) throws UpdateException;
    
    /**
     * 
     * @param phone
     * @throws CreateException 
     */
    public void createProduct(Product product) throws CreateException;
    
    /**
     * 
     * @param idProduct
     * @return
     * @throws ReadException 
     */
    public List<Product> findProductById(Integer idProduct) throws ReadException;
    
    /**
     * 
     * @param name
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public List<Product> findProductByName(String name, Integer idTxoko) throws ReadException;
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    public List<Product> findAllProducts () throws ReadException;

    /**
     * 
     * @param id
     * @throws ProductExist 
     */
    public void isProductExist(Integer id) throws ProductExist;

   
    
}
