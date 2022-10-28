package logic.model;

import except.ConnectionErrorException;
import except.EmailErrorException;
import except.ServerException;
import except.UserExistsException;
import logic.objects.User;

public interface IUserManager {

    /**
     * whatever.
     */
    public User signIn(User pUser);

    public User signUp(User pUser) throws ConnectionErrorException,
            UserExistsException,
            ServerException,
            Exception;
}
