package except;

/**
 *
 * @author Roke
 */
public class LoginFormatException extends Exception {

    /**
     * Creates a new instance of <code>LoginFormatException</code> without
     * detail message.
     */
    public LoginFormatException() {
        super("Error with the format of the login,\n can't start with a number or contain a blank space");
    }

    /**
     * Constructs an instance of <code>LoginFormatException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LoginFormatException(String msg) {
        super(msg);
    }
}
