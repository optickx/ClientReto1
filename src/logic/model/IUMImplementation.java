package logic.model;

import java.sql.Timestamp;

import logic.objects.User;

public class IUMImplementation implements IUserManager {

    @Override
    public User signIn(User pUser) {
        User us = null;
        pUser.setStatus(UserStatus.ENABLED);
        pUser.setPrivilege(UserPrivilege.ADMIN);
        us = new User(0, "FBe9", "nereaoceja2003@gmail.com", "Nerea Oceja", "abcd*1234", null, pUser.getStatus(), pUser.getPrivilege());
        return us;
    }

    @Override
    public User signUp(User pUser) {
        // TODO Auto-generated method stub
        return null;
    }
}