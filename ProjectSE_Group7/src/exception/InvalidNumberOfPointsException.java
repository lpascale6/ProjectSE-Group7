package exception;

/**
 * InvalidNumberOfPointsException class
 *
 * @author group7
 */
public class InvalidNumberOfPointsException extends Exception {

    /**
     * Creates a new instance of <code>InvalidNumberOfPointsException</code>
     * without detail message.
     */
    public InvalidNumberOfPointsException() {
    }

    /**
     * Constructs an instance of <code>InvalidNumberOfPointsException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidNumberOfPointsException(String msg) {
        super(msg);
    }
}
