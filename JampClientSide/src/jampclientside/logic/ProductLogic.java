/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.ProductBean;
import jampclientside.exceptions.BusinessLogicException;
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
    public void deleteProduct(ProductBean product) throws BusinessLogicException;
    
    /**
     * 
     * @param product
     * @throws UpdateException 
     */
    public void updateProduct(ProductBean product) throws BusinessLogicException;
    
    /**
     * 
     * @param product
     * @throws CreateException 
     */
    public void createProduct(ProductBean product) throws BusinessLogicException;
    
    /**
     * 
     * @param idProduct
     * @return
     * @throws ReadException 
     */
    public ProductBean findProductById(String idProduct) throws BusinessLogicException;
    
    /**
     * 
     * @param idProduct
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public ProductBean findProductByIdByTxoko(String idProduct, String idTxoko) throws BusinessLogicException;
    
    /**
     * 
     * @param name
     * @param idTxoko
     * @return
     * @throws ReadException 
     */
    public List<ProductBean> findProductByName(String name, String idTxoko) throws BusinessLogicException;
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    public List<ProductBean> findAllProducts () throws BusinessLogicException;
    
    /**
     * 
     * @return 
     */
    public List<ProductBean> findAllProductsByTxoko(String idTxoko) throws BusinessLogicException;

    /**
     * 
     * @param id
     * @throws ProductExist 
     */
    public void isProductExist(Integer id) throws ProductExist;

   
    
}
