/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.ProductBean;
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
public interface ProductLogic {


    /**
     * 
     * @param product
     * @throws DeleteException 
     */
    public void deleteProduct(ProductBean product) throws DeleteException;
    
    /**
     * 
     * @param product
     * @throws UpdateException 
     */
    public void updateProduct(ProductBean product) throws UpdateException;
    
    /**
     * 
     * @param product
     * @throws CreateException 
     */
    public void createProduct(ProductBean product) throws CreateException;
    
    /**
     * 
     * @param idProduct
     * @return
     * @throws ReadException 
     */
    public List<ProductBean> findProductById(Integer idProduct) throws ReadException;
    
    /**
     * 
     * @param name
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public List<ProductBean> findProductByName(String name, Integer idTxoko) throws ReadException;
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    public List<ProductBean> findAllProducts () throws ReadException;

    /**
     * 
     * @param id
     * @throws ProductExist 
     */
    public void isProductExist(Integer id) throws ProductExist;

   
    
}
