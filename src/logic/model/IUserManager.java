package logic.model;

import except.ConnectionErrorException;
import except.EmailErrorException;
import except.EmailExistsException;
import except.LoginExistsException;
import except.LoginFormatException;
import except.ServerException;
import except.UnmatchedPasswordsException;
import logic.objects.User;

public interface IUserManager {

    /**
     * whatever.
     */
    public User signIn(User pUser);

    public User signUp(User pUser) throws ConnectionErrorException,
            EmailErrorException,
            EmailExistsException,
            LoginExistsException,
            LoginFormatException,
            ServerException,
            UnmatchedPasswordsException,
            Exception;
}
