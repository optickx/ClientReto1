/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package view.signIn;

import except.LoginCredentialException;
import except.ServerException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.UserManagerFactory;
import logic.objects.User;
import logic.objects.message.Response;
import logic.objects.message.types.ResponseType;
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
    // private ControllerSocket control = null;
    private static final Logger LOGGER = Logger.getLogger("package view.signIn");

    /**
     * Initializing method
     *
     * @param root root object with the DOM charged
     */
    public void initSignIn(Parent root) {
        LOGGER.info("Initializing SignIn window");
        //Creates an scene
        Scene scene = new Scene(root);
        //Establishes an scene
        stageSignIn.setScene(scene);
        //Window title
        stageSignIn.setTitle("SignIn");
        //Not resizable window
        stageSignIn.setResizable(false);
        //Set the Event handlers
        stageSignIn.setOnShowing(this::handlerWindowShowing);
        //Set the textfields with a listener
        tfLogin.textProperty().addListener(this::textChanged);
        cpPassword.textProperty().addListener(this::textChanged);
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
     * Text changed event handler. Validate that all the fields are not empty
     * and that they not surpass 25 characters. The Accept button is disabled if
     * either of those are not fulfilled
     *
     * @param observable The value being observed.
     * @param oldValue The old value of the observable.
     * @param newValue The new value of the observable.
     */
    private void textChanged(ObservableValue observable,
            String oldValue,
            String newValue) {
        //Checks if the lenght is above 25 characters, showing an alert if happens and erasing the las character

        if (tfLogin.getText().trim().length() > 25) {
            tfLogin.setText(tfLogin.getText().substring(0, 25));
            new Alert(Alert.AlertType.ERROR, "The maximum lenght for the login is 25 characters.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }
        if (cpPassword.getText().trim().length() > 25) {
            cpPassword.setText(cpPassword.getText().substring(0, 25));
            new Alert(Alert.AlertType.ERROR, "The maximum lenght for the password is 25 characters.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }
//Validates that both fields are not empty
        if (tfLogin.getText().trim().isEmpty()
                || cpPassword.getText().trim().isEmpty()) {
            btnAccept.setDisable(true);
        }//All the data is filled correctly and the button is enabled
        else {
            btnAccept.setDisable(false);
        }
    }

    /**
     * Handle Action event on Accept button, if all goes well, the logged window
     * shows, if not, an alert is shown with the error
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {
        try {
            Response response = null;
            LOGGER.info("Intentando abrir la ventana Logged");
            //The data is charged into an User
            User usSignIn = new User();
            usSignIn.setLogin(tfLogin.getText());
            usSignIn.setPassword(cpPassword.getText());
            //The factory is used to obtain the implementation, and the method signIn is called, sending the User from above.           y se llama al método signIn() pasándole un nuevo objeto
            response = UserManagerFactory.getAccess().signIn(usSignIn);
            if (response.getResponseType() != ResponseType.OK) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(response.getResponseType().name());
                alert.setContentText("Try again");
                alert.showAndWait();
            } else {
                //Closing SignIn window
                this.stageSignIn.close();
                Stage stageLogged = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/logged/Logged.fxml"));
                Parent root = (Parent) loader.load();
                //Opens the Logged window
                LoggedFXMLDocumentController controller = (LoggedFXMLDocumentController) loader.getController();
                controller.setStage(stageLogged);
                controller.initLogged(root, response.getUser());
            }

        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
        } catch (LoginCredentialException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
        } catch (ServerException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    /**
     * The event which opens the Sign Up window
     *
     * @param event
     */
    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {
        try {
            LOGGER.info("Oppening SignUp window");
            //We need another stage to open it in a Modal way
            Stage stageSignUp = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signUp/SignUp.fxml"));
            Parent root = (Parent) loader.load();
            //Obtain the controller of the Sign Up window
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
