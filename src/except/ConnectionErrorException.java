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
public class ConnectionErrorException extends Exception {

    /**
     * Creates a new instance of <code>ConnectionErrorException</code> without
     * detail message.
     */
    public ConnectionErrorException() {
        super("Not able to connect with the server\nTry again later");
    }

    /**
     * Constructs an instance of <code>ConnectionErrorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ConnectionErrorException(String msg) {
        super(msg);
    }
}
