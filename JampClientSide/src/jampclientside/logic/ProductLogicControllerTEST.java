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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author 2dam
 */
/*public class ProductLogicControllerTEST implements ProductLogic{
    private ArrayList<ProductBean> product;
    private static final Logger LOGGER = Logger.getLogger("package.class");  
      public ProductLogicControllerTEST(){
        LOGGER.info("Building fake products data for testing UI.");
        product=new ArrayList();
        //Create 25 UserBean fake data objects.
        for(int i=0;i<25;i++)
            product.add(new ProductBean("Coca", "zero", i, (float)1.0, false));
    }

    @Override
    public void deleteProduct(ProductBean product) throws DeleteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduct(ProductBean product) throws UpdateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createProduct(ProductBean product) throws CreateException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<ProductBean> findAllProducts(){
/*            LOGGER.info("ProductImplementation: Finding all product from REST service (XML).");
            //Ask webClient for all departments' data.
          //  productos = ProductWebClient.findAllProducts(new GenericType<List<Product>>() {});
        }catch(Exception ex){
            LOGGER.log(Level.SEVERE,
                    "ProductImplementation: Exception finding all products, {0}",
                    ex.getMessage());
            //throw new ReadException("ProductImplementation: Error finding all products:\n" + ex.getMessage());
        }
        
        return product;
    }
    

   /* @Override
    public void isProductExist(Integer id) throws ProductExist {

    }

    @Override
    public ProductBean findProductById(String idProduct) {

    }

    @Override
    public ProductBean findProductByIdByTxoko(String idProduct, String idTxoko) {

    }

    @Override
    public List<ProductBean> findProductByName(String name, String idTxoko) {

    }

    @Override
    public List<ProductBean> findAllProductsByTxoko(String idTxoko) {

    }


}*/
