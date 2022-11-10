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
public class PasswordErrorException extends Exception {

    /**
     * Creates a new instance of <code>UnmatchedPasswordsException</code>
     * without detail message.
     */
    public PasswordErrorException() {
        super("Password and Confirm Password do not match or are blank spaces");
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
