/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package except;

/**
 *
 * @author 2dam
 */
public class EmailErrorException extends Exception {

    /**
     * Creates a new instance of <code>EmailErrorException</code> without detail
     * message.
     */
    public EmailErrorException() {
        super("Error in the email format, must be a valid email address without blank spaces");
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
