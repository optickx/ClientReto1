package logic.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.objects.User;
import logic.objects.message.Request;
import logic.objects.message.Response;
import static logic.objects.message.types.RequestType.SIGNIN;
import static logic.objects.message.types.RequestType.SIGNUP;

public class IUMImplementation implements IUserManager {

    @Override
    public Response signIn(User user) {
        Request request = null;
        Response response = null;
        ObjectInputStream read = null;
        ObjectOutputStream write = null;
        try {
            Socket socket = new Socket("localhost", 9107);
            write = new ObjectOutputStream(socket.getOutputStream());
            read = new ObjectInputStream(socket.getInputStream());

            // Cargar los datos en el objeto Request 
            // y mandar la informacion al lado Servidor
            request = new Request();
            request.setUser(user);
            request.setRequestType(SIGNIN);
            write.writeObject(request);

            // Lee la Respuesta del lado Servidor
            response = (Response) read.readObject();

            //Cerrar flujos
            socket.close();
            read.close();
            write.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(IUMImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public Response signUp(User user) {
        Request request = null;
        Response response = null;
        ObjectInputStream read = null;
        ObjectOutputStream write = null;
        try {
            Socket socket = new Socket("localhost", 9107);
            write = new ObjectOutputStream(socket.getOutputStream());
            read = new ObjectInputStream(socket.getInputStream());

            // Cargar los datos en el objeto Request 
            // y mandar la informacion al lado Servidor
            request = new Request();
            request.setUser(user);
            request.setRequestType(SIGNUP);
            write.writeObject(request);

            // Lee la Respuesta del lado Servidor
            response = (Response) read.readObject();

            //Cerrar flujos
            socket.close();
            read.close();
            write.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(IUMImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
