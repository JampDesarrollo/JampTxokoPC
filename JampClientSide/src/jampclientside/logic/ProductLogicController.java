/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.logic;

import jampclientside.entity.ProductBean;
import jampclientside.exceptions.BusinessLogicException;
import jampclientside.exceptions.ProductExist;
import jampclientside.rest.ProductRESTClient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 * Class that implements the productLogic iterface
 *
 * @author Julen
 */

public class ProductLogicController implements ProductLogic {

    //REST users web client
    private final ProductRESTClient ProductWebClient;
    
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
     * @throws jampclientside.exceptions.BusinessLogicException
     */
    @Override
    public void deleteProduct(ProductBean product) throws BusinessLogicException {
        try{
            LOGGER.log(Level.INFO,"ProductImplementation: Deleting product {0}.",product.getName());
            ProductWebClient.deleteProduct(product.getIdProduct());
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception deleting product, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error deleting product:\n"+ex.getMessage());
        }
    }
    /**
     * This method updates data for an existing Product. 
     * This is done by sending a PUT request to a RESTful web service.
     * @param product The PrductBean object to be updated.
     * @throws jampclientside.exceptions.BusinessLogicException
     */
    @Override
    public void updateProduct(ProductBean product) throws BusinessLogicException {
        try{
            LOGGER.log(Level.INFO,"ProductImplementation: Updating product {0}.",product.getIdProduct());
            ProductWebClient.updateProduct(product);
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception updating product, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error updating product:\n"+ex.getMessage());
        }
    }

    /**
     * This method adds a new created Product. This is done by sending a POST 
     * request to a RESTful web service.
     * @param product The UserBean object to be added.
     * @throws jampclientside.exceptions.BusinessLogicException
     */
    @Override
    public void createProduct(ProductBean product) throws BusinessLogicException {
         try{
            LOGGER.log(Level.INFO,"ProductImplementation: Creating product {0}.",product.getIdProduct());
            
            ProductWebClient.createProduct(product);
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception creating product, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error creating product:" + ex.getMessage());
        }
    }
       
    /**
     * This method returns a all products.
     * @return A collection of Product.
     * @throws jampclientside.exceptions.BusinessLogicException
     */
    @Override
    public List<ProductBean> findAllProducts() throws BusinessLogicException{
        List<ProductBean> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findAllProducts(new GenericType<List<ProductBean>>() {});
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error finding products:\n"+ex.getMessage());
        }
        
        return productos;
    }

    /**
     * This method returns a all products by id.
     * @param idProduct
     * @return A product
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    @Override
    public ProductBean findProductById(String idProduct) throws BusinessLogicException{
            ProductBean producto = null;
        try{
            LOGGER.info("ProductImplementation: Finding products by id from REST service (XML).");
            producto = ProductWebClient.findProductById(ProductBean.class, idProduct);
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding products by id, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error updating user:\n"+ex.getMessage());
        }
        return producto;
    }

    /**
     * This method returns a product by id and by txoko.
     * @param idProduct
     * @param idTxoko
     * @return A product
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    @Override
    public ProductBean findProductByIdByTxoko(String idProduct, String idTxoko) throws BusinessLogicException {
           ProductBean producto = null;
        try{
            LOGGER.info("ProductImplementation: Finding products by id and txoko from REST service (XML).");
            //Ask webClient for all departments' data.
            producto = ProductWebClient.findProductByIdByTxoko(ProductBean.class, idProduct, idTxoko);
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding products by id and txoko, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error updating user:\n"+ex.getMessage());
        }
        return producto;
    }

    /**
     * This method returns products by name.
     * @param name
     * @param idTxoko
     * @return A product
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    @Override
    public List<ProductBean> findProductByName(String name, String idTxoko) throws BusinessLogicException{
            List<ProductBean> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findProductByName(new GenericType<List<ProductBean>>() {}, name, idTxoko);
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
            throw new BusinessLogicException("ProductImplementation: Error updating user:\n"+ex.getMessage());
        }
        return productos;
    }

    /**
     * This method returns all products by the txoko.
     * @param idTxoko
     * @return A product
     * @throws jampclientside.exceptions.BusinessLogicException 
     */
    @Override
    public List<ProductBean> findAllProductsByTxoko(String idTxoko) throws BusinessLogicException {
        List<ProductBean> productos = null;
        try{
            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
            productos = ProductWebClient.findAllProductsByTxoko(new GenericType<List<ProductBean>>() {}, idTxoko);
        }catch(ClientErrorException ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
                throw new BusinessLogicException("ProductImplementation: Error updating user:\n"+ex.getMessage());
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
