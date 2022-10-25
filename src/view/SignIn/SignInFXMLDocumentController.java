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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.UserManagerFactory;
import logic.objects.User;
import view.*;
import view.logged.LoggedFXMLDocumentController;

/**
 *
 * @author 2dam
 */
public class SignInFXMLDocumentController implements Initializable {
    
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField cpPassword;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnSignUp;
    
    private Stage stage;

    /**
     * Handle Action event on Accept button
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {
        User us1 = new User();
        User us = UserManagerFactory.getAccess().signIn(us1);
        
        if (us.getLogin().equalsIgnoreCase(tfLogin.getText()) && us.getPassword().equalsIgnoreCase(cpPassword.getText())) {
            //Abrir ventana de login
            try { //Cargar fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/logged/Logged.fxml"));
                Parent root = (Parent) loader.load();
                //Crea una escena a partir del Parent 
                Scene scene = new Scene(root);
                stage = new Stage();
                //COnseguir el controlador de la ventana Logged
                LoggedFXMLDocumentController controller = (LoggedFXMLDocumentController) loader.getController();
                
                controller.setStage(stage);
                controller.initLogged(us);
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
        } else {
            new Alert(Alert.AlertType.ERROR, "Login or password not found", ButtonType.OK).showAndWait();
        }
        
    }
    
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) {
        
        try {//Validar que todos los campos llenos

            //Carga el ('DOM'--> document object model) documento xml y btiene un objeto parent
            Parent root = FXMLLoader.load(getClass().getResource("../signUp/SignUp.fxml"));
            //Crea una escena a partir del Parent
            Scene scene = new Scene(root);
            
            stage = new Stage();
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
        tfLogin.textProperty().addListener(this::textChanged);
        cpPassword.textProperty().addListener(this::textChanged);
    }

//Cambio de texto para volver a habiitar el botton accept
    private void textChanged(ObservableValue observable,
            String oldValue,
            String newValue) {
        //If text fields values are too long, show error message and disable 
        //accept button
        if (tfLogin.getText().trim().length() > 25
                || cpPassword.getText().trim().length() > 25) {
            //showErrorAlert("La longitud máxima del campo es de 25 caracteres.");
            btnAccept.setDisable(true);
        } //If text fields are empty disable accept buttton
        else if (tfLogin.getText().trim().isEmpty()
                || cpPassword.getText().trim().isEmpty()) {
            btnAccept.setDisable(true);
        } //Else, enable accept button
        else {
            btnAccept.setDisable(false);
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initSignIn(location, resources);
    }
}
