package logic.model;

import except.ConnectionErrorException;
import except.LoginCredentialException;
import except.ServerException;
import logic.objects.User;
import logic.objects.message.Response;

public interface IUserManager {

    /**
     * whatever.
     */
    public Response signIn(User pUser) throws LoginCredentialException;

    public Response signUp(User pUser) throws ConnectionErrorException,
            ServerException,
            Exception;
}
