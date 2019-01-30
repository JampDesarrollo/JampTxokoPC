/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.exceptions;

/**
 * Exception thrown when the user login exist on database
 *
 * @author ander
 */
public class UserLoginExistException extends Exception {
    /**
     * Exception constructor with msg
     * @param msg 
     */
    public UserLoginExistException(String msg) {
        super(msg);
    }
    /**
     * Empty exception constructor
     */
    public UserLoginExistException() {

    }
}
