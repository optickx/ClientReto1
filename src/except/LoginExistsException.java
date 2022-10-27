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
public class LoginExistsException extends Exception {

    /**
     * Creates a new instance of <code>LoginExistsException</code> without
     * detail message.
     */
    public LoginExistsException() {
        super("The Login introduced already exists\nTry another Login");
    }

    /**
     * Constructs an instance of <code>LoginExistsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LoginExistsException(String msg) {
        super(msg);
    }
}
