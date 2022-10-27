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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.UserManagerFactory;
import logic.objects.User;
import view.logged.LoggedFXMLDocumentController;

/**
 *
 * @author Nerea
 */
public class SignInFXMLDocumentController {

    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField cpPassword;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnSignUp;
    @FXML
    private ImageView imageViewFondo;
    @FXML
    private ImageView imageViewUser;
    @FXML
    private ImageView imageViewPassword;

    private Stage stage;

    /**
     * Metodo de inicialización de la ventana no modal SignIn
     *
     * @param root
     */
    public void initSignIn(Parent root) {
        Scene scene = new Scene(root);
        //Se desabilita el botton Accept
        btnAccept.setDisable(true);
        //Se enfoc el campo login
        tfLogin.requestFocus();
        //Ventana no redimensionable
        stage.setResizable(false);
        //Nombre de la ventana
        stage.setTitle("Logged");
        //Establece la escena en el escenario stage y la muestra
        stage.setScene(scene);
        stage.show();

        //Los campos de login y password estan apuntando al metodo textChanged
        tfLogin.textProperty().addListener(this::textChanged);
        cpPassword.textProperty().addListener(this::textChanged);
    }

    /**
     * Metodo de cambio de texto que determina que el maximo de caracteres
     * posibles sera 25 y habilitara el boton btnAccept si no hay texto alguno
     * en los campos
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void textChanged(ObservableValue observable,
            String oldValue,
            String newValue) {
        /*Mira si el numero de caracteres es superior a 25
        en los campos de tflogin o de cppassword y si es asi
        desactiva el boton btnAccept y sale una alerta con un mensaje
         */
        if (tfLogin.getText().trim().length() > 25
                || cpPassword.getText().trim().length() > 25) {
            new Alert(Alert.AlertType.ERROR, "La longitud máxima del campo es de 25 caracteres.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }/*Validar que los campos Login y
        Password están informados.
        En el caso de que no lo estén
        deshabilitar el botón Accept.*/ else if (tfLogin.getText().trim().isEmpty()
                || cpPassword.getText().trim().isEmpty()) {
            btnAccept.setDisable(true);
        }/*En el caso de que estén
        informados, habilitar el botón
        Accept*/ else {
            btnAccept.setDisable(false);
        }
    }

    /*COMENTARIO JAVI 
        En primer lugar, hay que validar  la  longitud de
        la información contenida en los campos 
        y su formato, es decir, ¿se admite cualquier
        combinación de caracteres (incluidos espacios) para los campos Login y password?*/
    
    /*  Validar que existe un usuario con el
        mismo nombre de usuario introducido y
        que su contraseña coincide con el valor
        de la contraseña introducida. Se usa la
        factoría para obtener una interfaz
        IUserManager, y se llama al método
        signIn() pasándole un nuevo objeto
        User que contenga el valor login y el
        valor password.
        ■ Si coinciden, se cierra
        esta ventana y se abre la
        ventana Logged
        pasándole los datos del
        objeto User devuelto.
        ■ Si se produce un error,
        se abre una ventana
        emergente mostrando el
        mensaje del error.
        */
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
    
    /*Se abre la ventana Sign Up de manera
    modal*/
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
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(SignInFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setStage(Stage stage) {
        stage = this.stage;
    }
}
