 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.entity;


import java.io.Serializable;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Julen
 */
@XmlRootElement (name = "product")
public class ProductBean implements Serializable{

    /**
     * 
     */
    private SimpleIntegerProperty idProduct;   
   
    /**
     * 
     */
    private SimpleStringProperty stock;
    
    /**
     * 
     */
    private SimpleStringProperty name;    
   
    /**
    * 
    */
    private SimpleStringProperty price; 
    
    /**
     * 
     */
    private SimpleStringProperty description;
    
    /**
     * 
     */
    private SimpleStringProperty venta;
    
    /**
     * 
     */
    private List<TxokoBean> txokos;
    
    public ProductBean(){
     this.idProduct = new SimpleIntegerProperty();
     this.name = new SimpleStringProperty ();
     this.description = new SimpleStringProperty();
     this.price = new SimpleStringProperty();
     this.stock = new SimpleStringProperty();
     this.venta = new SimpleStringProperty();
     this.txokos = txokos;

     }
    
        public ProductBean(String name,
                    String description,
                    String stock,
                    String price,
                    String venta){
        this.name=new SimpleStringProperty(name);
        this.description=new SimpleStringProperty(description);
        this.stock=new SimpleStringProperty(stock);
        this.price = new SimpleStringProperty(price);
        this.venta = new SimpleStringProperty(venta);
    }

    /**
     * @return the idProduct
     */
    public Integer getIdProduct() {
        return this.idProduct.get();
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
    public String getStock() {
        return this.stock.get();
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(String stock) {
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
    public String getPrice() {
        return this.price.get();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
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
    public void setDescription(String description) {
        this.description.set(description);
    }    

    /**
     * @return the venta
     */
    public String getVenta() {
        return this.venta.get();
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(String venta) {
        this.venta.set(venta);
    }

    /**
     * @return the txokos
     */
    public List<TxokoBean> getTxokos() {
        return txokos;
    }

    /**
     * @param txokos the txokos to set
     */
    public void setTxokos(List<TxokoBean> txokos) {
        this.txokos = txokos;
    }

}
