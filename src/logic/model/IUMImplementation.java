package logic.model;

import except.ServerException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.ControllerSocket;

import logic.objects.User;
import logic.objects.message.Request;
import logic.objects.message.Response;
import static logic.objects.message.types.RequestType.SIGNIN;
import static logic.objects.message.types.RequestType.SIGNUP;
import logic.objects.message.types.ResponseType;

public class IUMImplementation implements IUserManager {

    @Override
    public Response signIn(User user, ControllerSocket control) throws ServerException {
        Request request = null;
        Response response = null;
        ObjectInputStream read = null;
        ObjectOutputStream write = null;
        try {
            write = control.wObject();
            read = control.rObject();

            // Cargar los datos en el objeto Request 
            // y mandar la informacion al lado Servidor
            request = new Request();
            request.setUser(user);
            request.setRequestType(SIGNIN);
            write.writeObject(request);

            // Lee la Respuesta del lado Servidor
            response = (Response) read.readObject();

            //Cerrar flujos
            control.closeSocket();
            read.close();
            write.close();
        } catch (ConnectException e) {
            throw new ServerException();
        } catch (IOException | ClassNotFoundException ex) {
            //response = new Response(null, ResponseType.SERVER_ERROR);
            /*response.setUser(null);
            response.setResponseType(ResponseType.SERVER_ERROR);
            return response;*/
        }
        return response;
    }

    @Override
    public Response signUp(User user, ControllerSocket control) {
        Request request = null;
        Response response = null;
        ObjectInputStream read = null;
        ObjectOutputStream write = null;
        try {
            write = control.wObject();
            read = control.rObject();

            // Cargar los datos en el objeto Request 
            // y mandar la informacion al lado Servidor
            request = new Request();
            request.setUser(user);
            request.setRequestType(SIGNUP);
            write.writeObject(request);

            // Lee la Respuesta del lado Servidor
            response = (Response) read.readObject();

            //Cerrar flujos
            control.closeSocket();
            read.close();
            write.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(IUMImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
