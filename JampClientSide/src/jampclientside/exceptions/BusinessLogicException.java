/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.exceptions;

/**
 * BusinessLogic exception
 * @author Ander
 */
public class BusinessLogicException extends Exception {
    /**
     * empty Exception contructor
     */
    public BusinessLogicException() {
    }
    /**
     * Exception constructor with msg
     * @param msg message
     */
    public BusinessLogicException(String msg) {
        super(msg);
    }
}
