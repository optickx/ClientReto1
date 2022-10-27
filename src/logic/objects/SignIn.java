package logic.objects;

import java.sql.Timestamp;

public class SignIn {
    private Timestamp lastSignIn;

    // Getters.
    public Timestamp getLastSignIn() {
        return lastSignIn;
    }

    // Setters.
    public void setLastSignIn(Timestamp LastSignIn) {
        lastSignIn = LastSignIn;
    }
}
