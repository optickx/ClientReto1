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
public class UnmatchedPasswordsException extends Exception {

    /**
     * Creates a new instance of <code>UnmatchedPasswordsException</code>
     * without detail message.
     */
    public UnmatchedPasswordsException() {
        super("Password and Confirm Password do not match");
    }

    /**
     * Constructs an instance of <code>UnmatchedPasswordsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public UnmatchedPasswordsException(String msg) {
        super(msg);
    }
}
