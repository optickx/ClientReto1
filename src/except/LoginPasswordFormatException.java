package except;

/**
 *
 * @author Roke
 */
public class LoginPasswordFormatException extends Exception {

    /**
     * Creates a new instance of <code>LoginPasswordFormatException</code>
     * without detail message.
     */
    public LoginPasswordFormatException() {
        super("Password can't contain blank spaces");
    }

    /**
     * Constructs an instance of <code>LoginPasswordFormatException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LoginPasswordFormatException(String msg) {
        super(msg);
    }
}
