 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;


import java.io.Serializable;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author Julen
 */
public class ProductBean implements Serializable{

    /**
     * 
     */
    private SimpleIntegerProperty idProduct;   
   
    /**
     * 
     */
    private SimpleIntegerProperty stock;
    
    /**
     * 
     */
    private SimpleStringProperty name;    
   
    /**
    * 
    */
    private SimpleFloatProperty price; 
    
    /**
     * 
     */
    private SimpleStringProperty description;

    /**
     * @return the idProduct
     */
    public SimpleIntegerProperty getIdProduct() {
        return idProduct;
    }
    
    public ProductBean(){
     this.idProduct = new SimpleIntegerProperty();
     this.name = new SimpleStringProperty ();
     this.description = new SimpleStringProperty();
     this.price = new SimpleFloatProperty ();
     this.stock = new SimpleIntegerProperty();

     }
    
        public ProductBean(String name,
                    String description,
                    Integer stock){
        this.name=new SimpleStringProperty(name);
        this.description=new SimpleStringProperty(description);
        this.stock=new SimpleIntegerProperty(stock);
    }

    /**
     * @param idProduct the idProduct to set
     */
    public void setIdProduct(Integer idProduct) {
        this.idProduct.set(idProduct);
    }

    /**
     * @return the stock
     */
    public Integer getStock() {
        return this.stock.get();
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Integer stock) {
        this.stock.set(stock);
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return this.price.get();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price.set(price);
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description.get();
    }

    /**
     * @param description the description to set
     */
    public void setDescription(SimpleStringProperty description) {
        this.description = description;
    }    

}
