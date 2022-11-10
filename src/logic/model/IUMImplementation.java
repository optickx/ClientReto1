package logic.model;

import except.ServerException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
//import logic.ControllerSocket;

import logic.objects.User;
import logic.objects.message.Request;
import logic.objects.message.Response;
import static logic.objects.message.types.RequestType.SIGNIN;
import static logic.objects.message.types.RequestType.SIGNUP;
import logic.objects.message.types.ResponseType;

public class IUMImplementation implements IUserManager {

    @Override
    public Response signIn(User user) throws ServerException {
        Request request = null;
        Response response = null;
        ObjectInputStream read = null;
        ObjectOutputStream write = null;
        try {
            Socket socket = new Socket("localhost", 9107);
            write = new ObjectOutputStream(socket.getOutputStream());
            read = new ObjectInputStream(socket.getInputStream());

            // The data is introduced into the request and sent to the server side
            request = new Request();
            request.setUser(user);
            request.setRequestType(SIGNIN);
            write.writeObject(request);

            // Receives the response from the server side
            response = (Response) read.readObject();

            //Closes streams
            socket.close();
            read.close();
            write.close();
        } catch (ConnectException e) {
            throw new ServerException();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response signUp(User user) throws ServerException {
        Request request = null;
        Response response = null;
        ObjectInputStream read = null;
        ObjectOutputStream write = null;
        try {
            Socket socket = new Socket("localhost", 9107);
            write = new ObjectOutputStream(socket.getOutputStream());
            read = new ObjectInputStream(socket.getInputStream());

            // The data is introduced into the request and sent to the server side
            request = new Request();
            request.setUser(user);
            request.setRequestType(SIGNUP);
            write.writeObject(request);

            // Reads the response from the server
            response = (Response) read.readObject();

            //Close streams
            socket.close();
            read.close();
            write.close();
        } catch (ConnectException e) {
            throw new ServerException();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(IUMImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
