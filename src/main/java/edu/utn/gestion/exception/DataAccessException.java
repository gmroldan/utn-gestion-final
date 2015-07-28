/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.gestion.exception;

/**
 *
 * @author martin
 */
public class DataAccessException extends Exception {

    /**
     * Creates a new instance of <code>DataAccessException</code> without detail
     * message.
     */
    public DataAccessException() {
    }

    /**
     * Constructs an instance of <code>DataAccessException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DataAccessException(String msg) {
        super(msg);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
