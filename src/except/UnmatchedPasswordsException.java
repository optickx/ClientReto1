package except;
/**
 * Thrown when password and confirm password fields don´t match
 */

public class UnmatchedPasswordsException extends Exception {
    private final String reason = "Password and Confirm Password fields don´t match";
    
    public String getReason(){
        return reason;
    }
}
