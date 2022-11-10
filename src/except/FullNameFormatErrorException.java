/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
