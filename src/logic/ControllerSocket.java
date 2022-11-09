/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package logic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;*/

/**
 *
 * @author 2dam
 */
/*public class ControllerSocket {

    private Socket socket;

    public ControllerSocket() {
        createConnection();
    }

    public void createConnection() {
        try {
            socket = new Socket("localhost", 9107);
        } catch (IOException ex) {
            Logger.getLogger(ControllerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObjectOutputStream wObject() {
        ObjectOutputStream write = null;
        try {
            write = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ControllerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return write;
    }

    public ObjectInputStream rObject() {
        ObjectInputStream read = null;
        try {
            read = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ControllerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return read;
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ControllerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/
