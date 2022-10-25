/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package view.signIn;

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

/**
 *
 * @author 2dam
 */
public class SignInFXMLDocumentController implements Initializable {

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField tfPasswd;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnSignUp;

    /**
     * Handle Action event on Accept button
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {
        try {   //Cargar fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../logged/Logged.fxml"));
            Parent root = (Parent) loader.load();
            //COnseguir el controlador de la ventana Logged

            //Crea una escena a partir del Parent
            Scene scene = new Scene(root);

            Stage stage = new Stage();

            /* LoggedFXMLDocumentController controller = (LoggedFXMLDocumentController)loader.getController());
                controller.setStage(stage);*/
            //Localizar la ventana login
            Stage stageN = (Stage) btnAccept.getScene().getWindow();
            //Cerrar la ventana login
            stageN.close();
            //Ventana no redimensionable
            stage.setResizable(false);
            //Nombre de la ventana
            stage.setTitle("Logged");
            //Establece la escena en el escenario stage y la muestra
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(SignInFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);

            //Validar contraseña y usuario y mandar alerta en caso de error
            //si todo ok ir a la ventana de welcome
        }
    }

    @FXML
    private void handleTextFieldAction(ActionEvent event) {
        if (this.tfLogin.getText().isEmpty() || this.tfPasswd.getText().isEmpty()) {
            this.btnAccept.setDisable(true);
        }else{
            this.btnAccept.setDisable(false);
        }
    }

    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {

        try {//Validar que todos los campos llenos

            //Carga el ('DOM'--> document object model) documento xml y btiene un objeto parent
            Parent root = FXMLLoader.load(getClass().getResource("../signUp/SignUp.fxml"));
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
            Logger.getLogger(SignInFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initSignIn(URL url, ResourceBundle rb) {
        //Se desabilita el botton Accept
        btnAccept.setDisable(true);
        //Se enfoc el campo login
        tfLogin.requestFocus();
    }

    // private static final Logger logger = Logger.getLogger("package basicloginjavafxapplication1");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSignIn(location, resources);
    }
}
