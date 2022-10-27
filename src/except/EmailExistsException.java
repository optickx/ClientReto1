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
public class EmailExistsException extends Exception {

    /**
     * Creates a new instance of <code>EmailExistsException</code> without
     * detail message.
     */
    public EmailExistsException() {
        super("The Login introduced already exists\nTry another Login");
    }

    /**
     * Constructs an instance of <code>EmailExistsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmailExistsException(String msg) {
        super(msg);
    }
}
