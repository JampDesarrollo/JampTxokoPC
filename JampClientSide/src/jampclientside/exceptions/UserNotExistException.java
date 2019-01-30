/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jampclientside.exceptions;

/**
 * Clase de excepcion para cuando el user no existe a la hora de hacer login
 * Exception class for when the user doesn't exist.
 *
 * @author paula
 */
public class UserNotExistException extends Exception {

    /**
     * Exception constructor with msg
     * @param msg
     */
    public UserNotExistException(String msg) {

        super(msg);

    }

    /**
     *  Empty exception contructor
     */
    public UserNotExistException() {
    }

}
