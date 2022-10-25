package except;

/**thrown when the email input by the user doesn't match
 * the required pattern.
 */
public class EmailErrorException extends Exception {
    /**stores the email so that it 
     * can be shown back to the user.
     */
    private String email;
    /**enum that stores the type
     * 
     */
    private TYPE reason;

    /**to build an exception of this type
     * requires an indication of the reason behind it
     * and the email that failed.
     */
    public EmailErrorException(TYPE pReason, String pEmail) {
        super();
        reason = pReason;
        email = pEmail;        
    }
    /**useful to return the email
     * @return email that gave the error
    */
    public String getEmail() {
        return email;
    }
    /**@return the reason behind
     * the exception.
     */
    public TYPE getReason() {
        return reason;
    }
    /**two possible reasons for an
     * invalid email: pattern or availability.
     */
    public enum TYPE {
        INVALID_PATTERN,
        ALREADY_IN_USE
    }
}