package logic.model;

import except.ConnectionErrorException;
import except.EmailErrorException;
import except.ServerException;
import except.UserExistsException;
import logic.objects.User;
import logic.objects.message.Response;

public interface IUserManager {

    /**
     * whatever.
     */
    public User signIn(User pUser);

    public Response signUp(User pUser) throws ConnectionErrorException,
            ServerException,
            Exception;
}
