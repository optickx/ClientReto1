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
public class LoginPasswordNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>LoginPasswordNotFoundException</code>
     * without detail message.
     */
    public LoginPasswordNotFoundException() {
    }

    /**
     * Constructs an instance of <code>LoginPasswordNotFoundException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LoginPasswordNotFoundException(String msg) {
        super("Login or password not found");
    }
}
