package logic.model;

import java.sql.Timestamp;

import logic.objects.User;
import logic.objects.UserPrivilege;
import logic.objects.UserStatus;

public class IUMImplementation implements IUserManager {

    @Override
    public User signIn(User pUser){
      User  userDao = new User();//Metodo del dao que te devolvera algo
      
      userDao.getID();
      userDao.getLogin();
      userDao.getEmail();
      userDao.getFullName();
      userDao.getPassword();
      userDao.getStatus();
      userDao.getPrivilege();
      userDao.getLastPasswordChange();
      //return userDao
        return 
            new User(0, "FBe9", "nereaoceja2003@gmail.com", "Nerea Oceja",
            "abcd*1234", null, UserStatus.ENABLED, UserPrivilege.ADMIN, null);

    }

    @Override
    public User signUp(User pUser) {
        // TODO Auto-generated method stub
        return null;
    }
}