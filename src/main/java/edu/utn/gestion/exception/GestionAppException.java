package edu.utn.gestion.exception;

/**
 *
 * @author martin
 */
public class GestionAppException extends Exception {

    /**
     * Creates a new instance of <code>GestionAppException</code> without detail
     * message.
     */
    public GestionAppException() {
    }

    /**
     * Constructs an instance of <code>GestionAppException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public GestionAppException(String msg) {
        super(msg);
    }
    
    public GestionAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public GestionAppException(Throwable cause) {
        super(cause);
    }
}
