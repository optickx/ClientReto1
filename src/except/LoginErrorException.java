package except;

/**thrown when the username is not valid.
 */
public class LoginErrorException extends Exception {
   /**stores the username so that it
    * can be shown to the user.
     */ 
    // not found jiji
    private String username;
    /**enum that stores the type.
     * 
     */
    private TYPE reason;

    /**to build an exception of this type
     * requires an indication of the reason behind it 
     * and the email that failed.
     */
    public LoginErrorException(TYPE pReason, String pUsername) {
        super();
        reason = pReason;
        username = pUsername;
    } 
    /**@return username that gave the error 
     * 
    */
    public String getUsername() {
        return username;
    }
    /**@return the reason behind the exception
     * 
     */
    public TYPE getReason() {
        return reason;
    }
    /**two possible reasons for this exception:
     * 
     */
    public enum TYPE {
        USER_NOT_FOUND,
        USERNAME_NOT_AVAILABLE
    }
}