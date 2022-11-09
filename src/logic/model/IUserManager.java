package logic.model;

import except.ConnectionErrorException;
import except.LoginCredentialException;
import except.ServerException;
//import logic.ControllerSocket;
import logic.objects.User;
import logic.objects.message.Response;

public interface IUserManager {

    /**
     * whatever.
     */
    public Response signIn(User pUser/*, ControllerSocket control*/) throws LoginCredentialException, ServerException;

    public Response signUp(User pUser/*, ControllerSocket control*/) throws ConnectionErrorException,
            ServerException,
            Exception;
}
