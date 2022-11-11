package except;

/**
 *
 * @author Roke
 */
public class PasswordErrorException extends Exception {

    /**
     * Creates a new instance of <code>UnmatchedPasswordsException</code>
     * without detail message.
     */
    public PasswordErrorException() {
        super("Password and Confirm Password do not match\n or are blank spaces");
    }

    /**
     * Constructs an instance of <code>UnmatchedPasswordsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public PasswordErrorException(String msg) {
        super(msg);
    }
}
