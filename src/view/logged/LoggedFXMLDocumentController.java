/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package view.logged;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.objects.*;

/**
 *
 * @author 2dam
 */
public class LoggedFXMLDocumentController {

    @FXML
    private Label lblWelcome;
    @FXML
    private Button btnLogOut;
    @FXML
    private ImageView imageViewLogged;

    private User user;

    //Label con nombre del usuario y welcome
    public void initLogged(User user) {
        this.user = user;
        /* User us1=new User();
        User us = UserManagerFactory.getAccess().signUp(us1);*/
        lblWelcome.setText("Welcome " + user.getLogin() + " to our aplication");
    }

    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {

        try {//Validar que todos los campos llenos

            //Carga el ('DOM'--> document object model) documento xml y btiene un objeto parent
            Parent root = FXMLLoader.load(getClass().getResource("../signIn/SignIn.fxml"));
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

    public void setStage(Stage stage) {

    }

}
