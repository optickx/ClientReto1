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
        return new User(0, "FBe9", "nereaoceja2003@gmail.com",
         "Nerea Oceja", "abcd*1234", 
        t, 1, 1);
    }

    @Override
    public User signUp(User pUser) {
        // TODO Auto-generated method stub
        return null;
    }
}