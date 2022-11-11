package except;

/**
 * @author Roke
 */
public class EmailErrorException extends Exception {

    /**
     * Creates a new instance of EmailErrorException without detail
     * message.
     */
    public EmailErrorException() {
        super("Error in the email format,\nmust be a valid email address without blank spaces");
    }

    /**
     * Constructs an instance of <code>EmailErrorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmailErrorException(String msg) {
        super(msg);
    }
}
