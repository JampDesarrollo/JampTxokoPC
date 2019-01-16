/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.exceptions;

/**
 *
 * @author Ander
 */
public class BusinessLogicException extends Exception {
    public BusinessLogicException() {
    }
    
    public BusinessLogicException(String msg) {
        super(msg);
    }
}
