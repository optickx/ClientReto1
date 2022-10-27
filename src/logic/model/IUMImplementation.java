package logic.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.objects.User;
import logic.objects.message.Request;
import static logic.objects.message.types.RequestType.SIGNIN;
import static logic.objects.message.types.RequestType.SIGNUP;

public class IUMImplementation implements IUserManager {
    
    public User signIn(User user) {
        try {
            Socket socket = new Socket("localhost", 7777);
            Request request = new Request(user, SIGNIN);
            ObjectOutputStream mandarMensaje;
            ObjectInputStream leerMensaje;
            
            mandarMensaje = new ObjectOutputStream(socket.getOutputStream());
            mandarMensaje.writeObject(request);
        } catch (IOException ex) {
            Logger.getLogger(IUMImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public User signUp(User user) {
        try {
            Socket socket = new Socket("localhost", 7777);
            Request request = new Request();
            request.setUser(user);
            request.setRequestType(SIGNUP);
            ObjectOutputStream mandarMensaje;
            ObjectInputStream leerMensaje;
            
            mandarMensaje = new ObjectOutputStream(socket.getOutputStream());
            mandarMensaje.writeObject(request);
        } catch (IOException ex) {
            Logger.getLogger(IUMImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
