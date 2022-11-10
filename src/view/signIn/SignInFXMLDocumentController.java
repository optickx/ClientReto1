/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package view.signIn;

import except.LoginPasswordNotFoundException;
import java.io.IOException;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.UserManagerFactory;
import logic.objects.User;
import view.logged.LoggedFXMLDocumentController;
import view.signUp.SignUpFXMLDocumentController;

/**
 *
 * @author Nerea and Dani
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

    private Stage stageSignIn;
    private static final Logger LOGGER = Logger.getLogger("package view.signIn");

    /**
     * Metodo de inicialización de la ventana no modal SignIn
     *
     * @param root Un objeto parent con el DOM cargado
     */
    public void initSignIn(Parent root) {
        LOGGER.info("Inicializando la ventana SignIn");
        //Se crea una escena a partir del parent
        Scene scene = new Scene(root);
        //Establece la escena en el escenario
        stageSignIn.setScene(scene);
        //Nombre de la ventana
        stageSignIn.setTitle("SignIn");
        //Ventana no redimensionable
        stageSignIn.setResizable(false);
        //Poner los manejadores de eventos
        stageSignIn.setOnShowing(this::handlerWindowShowing);
        //Los campos de login y password estan apuntando al metodo textChanged
        tfLogin.textProperty().addListener(this::textChanged);
        cpPassword.textProperty().addListener(this::textChanged);
        //Muestra la ventana
        stageSignIn.show();
    }
    /**
     * Metodo que controla que al inicializar la ventana 
     * los campos esten vacios, el boton deshabilitado y 
     * el focus en el primer campo
     * @param event evento de mostrarse la ventana
     */
    private void handlerWindowShowing(WindowEvent event) {
        LOGGER.info("Iniciando SignInFXMLDocumentController::handlerWindowShowing");
        //Login text view sin texto
        tfLogin.setText("");
        //Login text view sin texto
        cpPassword.setText("");
        //Se desabilita el botton Accept
        btnAccept.setDisable(true);
        //Se enfoca el campo login
        tfLogin.requestFocus();
    }

    /**
     * Metodo de cambio de texto que determina que el maximo de caracteres
     * posibles sera 25 y habilitara el boton btnAccept si no hay texto alguno
     * en los campos
     *
     * @param observable El valor que esta siendo ovservado
     * @param oldValue La version vieja del observable
     * @param newValue La version nueva del observable
     */
    private void textChanged(ObservableValue observable,
            String oldValue,
            String newValue) {
        /*Mira si el numero de caracteres es superior a 25
        en los campos de tflogin o de cppassword y si es asi
        desactiva el boton btnAccept y sale una alerta con un mensaje
         */
        if (tfLogin.getText().trim().length() > 25) {
            new Alert(Alert.AlertType.ERROR, "La longitud máxima del campo es de 25 caracteres.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }/*Validar que los campos Login y
        Password están informados.
        En el caso de que no lo estén
        deshabilitar el botón Accept.*/ else if (cpPassword.getText().trim().length() > 25) {
            new Alert(Alert.AlertType.ERROR, "La longitud máxima del campo es de 25 caracteres.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        } else if (tfLogin.getText().trim().isEmpty()
                || cpPassword.getText().trim().isEmpty()) {
            btnAccept.setDisable(true);
        }/*En el caso de que estén
        informados, habilitar el botón
        Accept*/ else {
            btnAccept.setDisable(false);
        }
    }

    /**
     * El evento es que cuando se toca el boton btnAccept abre la ventana Logged
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {
        try {
            LOGGER.info("Intentando abrir la ventana Logged");
            //Objeto User con los valores de login y password
            User usSignIn = new User();
            usSignIn.setLogin(tfLogin.getText());
            usSignIn.setPassword(cpPassword.getText());
            /*Se usa la factoría para obtener una interfaz IUserManager, 
            y se llama al método signIn() pasándole un nuevo objeto
            User que contenga el valor login y el valor password.*/
            User us = UserManagerFactory.getAccess().signIn(usSignIn);
            /*Validar que existe un usuario con el
            mismo nombre de usuario introducido y
            que su contraseña coincide con el valor
            de la contraseña introducida.*/
            if (!us.getLogin().equalsIgnoreCase(usSignIn.getLogin()) || !us.getPassword().equalsIgnoreCase(usSignIn.getPassword())) {
                /*Si se produce un error,se abre una ventana emergente
                mostrando el mensaje del error.*/
                throw new LoginPasswordNotFoundException();
            } else {
                //Cerrar la ventana login
                this.stageSignIn.close();
                Stage stageLogged = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/logged/Logged.fxml"));
                Parent root = (Parent) loader.load();
                //COnseguir el controlador de la ventana Logged
                LoggedFXMLDocumentController controller = (LoggedFXMLDocumentController) loader.getController();
                controller.setStage(stageLogged);
                controller.initLogged(root, us);
            }
        } catch (IOException ex) {
            Logger.getLogger(SignInFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LoginPasswordNotFoundException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    /**
     * El evento es que cuando se toca el boton btnSignUp abre la ventana SignUp
     * de manera modal
     *
     * @param event
     */
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        try {
            LOGGER.info("Intentando abrir la ventana SignUp");
            /*Para poder abrir modalmente la ventana
            he declaro otro stage diferenete*/
            Stage stageSignUp = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signUp/SignUp.fxml"));
            Parent root = (Parent) loader.load();
            //COnseguir el controlador de la ventana SignIn
            SignUpFXMLDocumentController controller = (SignUpFXMLDocumentController) loader.getController();
            controller.setStage(stageSignUp);
            controller.initSignUp(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Declarar que el stage de la clase controlladora 
     * es el mismo al que le han pasado
     * @param stage donde se muestra la ventana
     */
    public void setStage(Stage stage) {
        this.stageSignIn = stage;
    }
}
