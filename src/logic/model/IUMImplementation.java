package logic.model;

import logic.objects.User;
import logic.objects.UserPrivilege;
import logic.objects.UserStatus;

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
        return signIn(pUser);
    }
}
