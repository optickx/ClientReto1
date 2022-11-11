package except;

/**
 *
 * @author Roke
 */
public class FullNameFormatErrorException extends Exception {

    /**
     * Creates a new instance of <code>FullNameFormatErrorException</code>
     * without detail message.
     */
    public FullNameFormatErrorException() {
        super("Your name can't have digits on it.");
    }

    /**
     * Constructs an instance of <code>FullNameFormatErrorException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public FullNameFormatErrorException(String msg) {
        super(msg);
    }
}
