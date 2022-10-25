/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package view.greet;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.objects.*;
import static logic.objects.UserStatus.ENABLED;
import view.*;

/**
 *
 * @author 2dam
 */
public class LoggedFXMLDocumentController implements Initializable {

    @FXML
    private Label lblWelcome;
    @FXML
    private Button btnLogOut;

    //Label con nombre del usuario y welcome
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User us = getUser();
        lblWelcome.setText("Welcome " + us.getLogin() + " to our aplication");
    }

    @FXML
    private User getUser() {
        User us = new User();
        us.setID(1);
        us.setLogin("Pepito45");
        us.setEmail("pepito@gmail.com");
        us.setFullName("Pepe Jolinnas");
        us.setPassword("abcd*1234");
        us.setLastPasswordChange(null);
        us.setStatus(UserStatus.ENABLED);
        us.setPrivilege(UserPrivilege.USER);
        return us;
    }

    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {

        try {//Validar que todos los campos llenos

            //Carga el ('DOM'--> document object model) documento xml y btiene un objeto parent
            Parent root = FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
            //Crea una escena a partir del Parent
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            //Localizar la ventana Logged
            Stage stageN = (Stage) btnLogOut.getScene().getWindow();
            //Cerrar la ventana Logged
            stageN.close();
            stage.setTitle("Sign In");
            stage.setResizable(false);
            //Establece la escena en el escenario stage y la muestra
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoggedFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
