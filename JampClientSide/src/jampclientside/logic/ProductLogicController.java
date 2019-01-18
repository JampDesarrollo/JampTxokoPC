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
import jampclientside.rest.ProductRESTClient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;

/**
 *
 * Clase que implementa la interfaz de Logica. Class that implements the logic
 * interface.
 *
 * @author Julen
 */

public class ProductLogicController implements ProductLogic {

    //REST users web client
    private ProductRESTClient ProductWebClient;
    
    /**
     * Attribute to appear the information text.
     */
    private static final Logger LOGGER
            = Logger.getLogger("jamp.pc.logic.IlogicImplementationProduct");

    /**
     * Create a ILogicImplementationroduct.
     */
    public ProductLogicController(){
        ProductWebClient = new ProductRESTClient();
    }
    /**
     * This method deletes data for an existing product. 
     * This is done by sending a DELETE request to a RESTful web service.
     * @param product The ProductBean object to be deleted.
     * @throws DeleteException
     */
    @Override
    public void deleteProduct(ProductBean product) throws DeleteException {
        try{
            LOGGER.log(Level.INFO,"ProductImplementation: Deleting product {0}.",product.getName());
            ProductWebClient.deleteProduct(product.getIdProduct());
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception deleting product, {0}",
                    ex.getMessage());
            throw new DeleteException("ProductImplementation: Error deleting product:\n"+ex.getMessage());
        }
    }
    /**
     * This method updates data for an existing Product. 
     * This is done by sending a PUT request to a RESTful web service.
     * @param product The PrductBean object to be updated.
     * @throws UpdateException If there is any error while processing.
     */
    @Override
    public void updateProduct(ProductBean product) throws UpdateException {
        try{
            LOGGER.log(Level.INFO,"ProductImplementation: Updating user {0}.",product.getIdProduct());
            ProductWebClient.updateProduct(product);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception updating user, {0}",
                    ex.getMessage());
            throw new UpdateException("ProductImplementation: Error updating user:\n"+ex.getMessage());
        }
    }

    /**
     * This method adds a new created Product. This is done by sending a POST 
     * request to a RESTful web service.
     * @param product The UserBean object to be added.
     * @throws CreateException If there is any error while processing.
     */
    @Override
    public void createProduct(ProductBean product) throws CreateException {
         try{
            LOGGER.log(Level.INFO,"ProductImplementation: Creating user {0}.",product.getIdProduct());
            ProductWebClient.createProduct(product);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception creating user, {0}",
                    ex.getMessage());
            throw new CreateException("ProductImplementation: Error creating user:" + ex.getMessage());
        }
    }
       
    /**
     * This method returns a collection of products for users.
     * @return A collection of Product.
     * @throws ReadExcdeption If there is any error while processing.
     */
    @Override
    public List<ProductBean> findAllProducts(){
        List<ProductBean> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findAllProducts(new GenericType<List<ProductBean>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
        }
        
        return productos;
    }

    /**
     * 
     * @param idProduct
     * @return 
     */
    @Override
    public ProductBean findProductById(String idProduct) {
            ProductBean producto = null;
        try{
            LOGGER.info("ProductImplementation: Finding products by id from REST service (XML).");
            //Ask webClient for all departments' data.
            producto = ProductWebClient.findProductById(ProductBean.class, idProduct);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding products by id, {0}",
                    ex.getMessage());
        }
        return producto;
    }

    /**
     * 
     * @param idProduct
     * @param idTxoko
     * @return 
     */
    @Override
    public ProductBean findProductByIdByTxoko(String idProduct, String idTxoko) {
           ProductBean producto = null;
        try{
            LOGGER.info("ProductImplementation: Finding products by id and txoko from REST service (XML).");
            //Ask webClient for all departments' data.
            producto = ProductWebClient.findProductByIdByTxoko(ProductBean.class, idProduct, idTxoko);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding products by id and txoko, {0}",
                    ex.getMessage());
        }
        return producto;
    }

    /**
     * 
     * @param name
     * @param idTxoko
     * @return 
     */
    @Override
    public List<ProductBean> findProductByName(String name, String idTxoko) {
            List<ProductBean> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findProductByName(new GenericType<List<ProductBean>>() {}, name, idTxoko);
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
        }
        return productos;
    }

    /**
     * 
     * @param idTxoko
     * @return 
     */
    @Override
    public List<ProductBean> findAllProductsByTxoko(String idTxoko) {
        List<ProductBean> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findAllProducts(new GenericType<List<ProductBean>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
        }
        
        return productos;
    }


    /**
     * 
     * @param id
     * @throws ProductExist 
     */
    @Override
    public void isProductExist(Integer id) throws ProductExist{
        
    }
}
