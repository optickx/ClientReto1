/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package view.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.*;
import view.greet.LoggedFXMLDocumentController;

/**
 *
 * @author 2dam
 */
public class LoginFXMLDocumentController implements Initializable {

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField tfPasswd;
    @FXML
    private Button bAccept;
    @FXML
    private Button bRegister;

    /**
     * Handle Action event on Accept button
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {

        try {   //Cargar fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../greet/Logged.fxml"));
                Parent root = (Parent) loader.load();
                //COnseguir el controlador de la ventana Logged
                
                //Crea una escena a partir del Parent
                Scene scene = new Scene(root);

                Stage stage = new Stage();
                
               /* LoggedFXMLDocumentController controller = (LoggedFXMLDocumentController)loader.getController());
                controller.setStage(stage);*/
                //Localizar la ventana login
                Stage stageN = (Stage) bAccept.getScene().getWindow();
                //Cerrar la ventana login
                stageN.close();
                stage.setResizable(false);
                stage.setTitle("Logged");
                //Establece la escena en el escenario stage y la muestra
                stage.setScene(scene);
                stage.show();

        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
//Validar contraseña y usuario y mandar alerta en caso de error
        //si todo ok ir a la ventana de welcome
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

        try {//Validar que todos los campos llenos

            //Carga el ('DOM'--> document object model) documento xml y btiene un objeto parent
            Parent root = FXMLLoader.load(getClass().getResource("../register/SignUp.fxml"));
            //Crea una escena a partir del Parent
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Sign Up");
            stage.setResizable(false);
            //Establece la escena en el escenario stage y la muestra
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // private static final Logger logger = Logger.getLogger("package basicloginjavafxapplication1");
}
