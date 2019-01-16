/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.exceptions;

/**
 * Exception thrown when a Product already exists.
 * @author Julen
 */
public class ProductExist extends Exception {

    public ProductExist(String msg) {
        super(msg);
    }
    
}
