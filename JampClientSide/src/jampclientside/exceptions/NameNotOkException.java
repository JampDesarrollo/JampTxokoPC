/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.exceptions;

/**
 *Class for the exception
 * @author paula
 */
public class NameNotOkException extends Exception {
       public NameNotOkException() {
    }

    /**
     *Exception with a message
     * @param msg the detail message.
     */
    public NameNotOkException(String msg) {
        super(msg);
    }
    
}
