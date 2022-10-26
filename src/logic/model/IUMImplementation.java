package logic.model;

import java.sql.Timestamp;

import logic.objects.User;

public class IUMImplementation implements IUserManager {

    /*
     * (non-Javadoc)
     */
    @Override
    public User signIn(User pUser) {
        Timestamp t = Timestamp.valueOf("");
        return null;
    }

    @Override
    public User signUp(User pUser) {
        // TODO Auto-generated method stub
        return null;
    }
}