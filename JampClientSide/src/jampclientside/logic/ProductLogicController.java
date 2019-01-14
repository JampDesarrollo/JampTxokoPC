/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.Product;
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
        ProductWebClient=new ProductRESTClient();
    }
    /**
     * This method deletes data for an existing product. 
     * This is done by sending a DELETE request to a RESTful web service.
     * @param product The ProductBean object to be deleted.
     * @throws DeleteException
     */
    @Override
    public void deleteProduct(Product product) throws DeleteException {
        try{
            LOGGER.log(Level.INFO,"ProductImplementation: Deleting product {0}.",product.getName());
            ProductWebClient.deleteProduct(product.getIdProduct().toString());
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
    public void updateProduct(Product product) throws UpdateException {
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
    public void createProduct(Product product) throws CreateException {
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
    public List<Product> findAllProducts() throws ReadException {
        List<Product> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findAllProducts(new GenericType<List<Product>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
            throw new ReadException("ProductImplementation: Error finding all products:\n" + ex.getMessage());
        }
        
        return productos;
    }
    
    /**
     * This method returns a collection of products for users.
     * @return A collection of Product.
     * @throws ReadExcdeption If there is any error while processing.
     */
    @Override
    public List<Product> findProductByName(String name, Integer idTxoko) throws ReadException{
            List<Product> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findProductByName(new GenericType<List<Product>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
            throw new ReadException("ProductImplementation: Error finding products by Name:\n" + ex.getMessage());
        }
        return productos;
    }

    /**
     * This method returns a collection of products for users.
     * @return A collection of Product.
     * @throws ReadExcdeption If there is any error while processing.
     */
    @Override
    public Product findProductsByIdByTxoko() throws ReadException {
            Product productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding products by id and txoko from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findProductByName(new GenericType<List<Product>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding products by id and txoko, {0}",
                    ex.getMessage());
            throw new ReadException("ProductImplementation: Error finding products by id and txoko:\n" + ex.getMessage());
        }
        return productos;
    }

    @Override
    public Product findProductsById() throws ReadException {
            Product productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding products by id from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findProductByName(new GenericType<List<Product>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding products by id, {0}",
                    ex.getMessage());
            throw new ReadException("ProductImplementation: Error finding products by id:" + ex.getMessage());
        }
        return productos;
    }
    
    @Override
        public void isProductExist(Integer id) throws ProductExist{
        
        }

}
