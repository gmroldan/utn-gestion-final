package edu.utn.gestion.exception;

/**
 * Created by martin on 09/12/15.
 */
public class FileGenerationException extends Exception {
    /**
     * Creates a new instance of <code>FileGenerationException</code> without detail
     * message.
     */
    public FileGenerationException() {
    }

    /**
     * Constructs an instance of <code>FileGenerationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FileGenerationException(String msg) {
        super(msg);
    }

    public FileGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileGenerationException(Throwable cause) {
        super(cause);
    }
}
