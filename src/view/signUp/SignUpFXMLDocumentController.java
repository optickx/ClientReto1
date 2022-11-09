package view.signUp;

import except.EmailErrorException;
import except.LoginFormatException;
import except.ServerException;
import except.UnmatchedPasswordsException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import logic.ControllerSocket;
import logic.UserManagerFactory;
import logic.objects.User;
import logic.objects.message.Response;
import logic.objects.message.types.ResponseType;

/**
 *
 * @author 2dam
 */
public class SignUpFXMLDocumentController {

    /**
     * @author Eneko and Roke
     */
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField cpPassword;
    @FXML
    private TextField cpConfirm;
    @FXML
    private TextField tfLogin;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnReset;
    @FXML
    private Label lblLogin;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblPassword;
    @FXML
    private Label lblConfirmPassword;
    @FXML
    private ImageView imageView;

    private Stage stage;
//    private ControllerSocket control = null;
    private static final Logger LOGGER = Logger.getLogger("package view.signUp;");

    /**
     * Handle Action event on Accept button
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {
        try {
            Response response = null;
            //Oculta todos los labels con los mensajes de las
            //  excepciones.
            lblEmail.setText("");
            lblConfirmPassword.setText("");

            //Validar que el formato del email es correcto
            // Patrón para validar el email
            String pattern = "([a-z0-9]*)@([a-z]*).(com|org|cn|net|gov|eus)";
            if (!Pattern.matches(pattern, tfEmail.getText())) {
                throw new EmailErrorException();
            }

            //Validar que la confirm password coincida
            if (!(cpPassword.getText().equals(cpConfirm.getText()))) {
                throw new UnmatchedPasswordsException();
            }

            //Carga los datos en un objeto User
            User user = new User();
            user.setLogin(tfLogin.getText());
            user.setEmail(tfEmail.getText());
            user.setPassword(cpPassword.getText());
            user.setFullName(tfFullName.getText());
            //a continuación manda el objeto al método (sign up) de la implementación.
            response = UserManagerFactory.getAccess().signUp(user/*, control*/);

            if (response.getResponseType() != ResponseType.OK) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(response.getResponseType().name());
                alert.setContentText("Try again");
                alert.showAndWait();
            } else {
                //Avisa al usuario de que se ha registrado exitosamente
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("SignUp");
                alert.setHeaderText(null);
                alert.setContentText("Signed Up succesfully, try logging in");
                alert.showAndWait();
                //la ventana Sign Up se cierra, devolviendo el control a la
                //ventana Sign In.
                Stage stageN = (Stage) btnAccept.getScene().getWindow();
                stageN.close();
            }

            //A través de una ventana emergente se mostrará el mensaje
            //de la excepción si la hubiera.
        } catch (EmailErrorException e) {
            lblEmail.setText(e.getMessage());
        } catch (UnmatchedPasswordsException e) {
            lblConfirmPassword.setText(e.getMessage());
        } catch (LoginFormatException e) {
            lblLogin.setText(e.getMessage());
        } catch (ServerException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK).showAndWait();
        } catch (Exception ex) {
            Logger.getLogger(SignUpFXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handle Action event on Reset button
     *
     * @param event The action event object
     */
    @FXML
    private void handleResetButtonAction(ActionEvent event
    ) {
        //Vacía todos los campos 
        tfEmail.setText("");
        tfFullName.setText("");
        cpPassword.setText("");
        cpConfirm.setText("");
        tfLogin.setText("");
        lblConfirmPassword.setText("");
        lblEmail.setText("");
        // enfoca el campo username.
        tfLogin.requestFocus();
    }

    public void initSignUp(Parent root/*, ControllerSocket control*/) {
        //this.control = control;
        LOGGER.info("Inicializando la ventana SignUp");
        //Se crea una escena a partir del parent
        Scene scene = new Scene(root);
        //Establece la escena en el escenario stage y la muestra
        stage.setScene(scene);
        //El nombre de la ventana es SignUp
        stage.setTitle("Sign Up");
        //Ventana modal
        stage.initModality(Modality.APPLICATION_MODAL);
        //Ventana no redimensionable
        stage.setResizable(false);
        //Poner los manejadores de eventos
        stage.setOnShowing(this::handlerWindowShowing);
        //Poner a los textFields en escucha
        tfEmail.textProperty().addListener(this::textPropertyChange);
        tfLogin.textProperty().addListener(this::textPropertyChange);
        tfFullName.textProperty().addListener(this::textPropertyChange);
        cpPassword.textProperty().addListener(this::textPropertyChange);
        cpConfirm.textProperty().addListener(this::textPropertyChange);
        stage.showAndWait();
    }

    private void handlerWindowShowing(WindowEvent event) {
        LOGGER.info("Iniciando SignUpFXMLDocumentController::handlerWindowShowing");
        //Se desabilita el botton Accept
        btnAccept.setDisable(true);
        //Se enfoca el campo login
        tfLogin.requestFocus();
        /*Oculta los labels 
        que muestran los mensajes de las excepciones*/
        tfEmail.setText("");
        tfFullName.setText("");
        cpPassword.setText("");
        cpConfirm.setText("");
        tfLogin.setText("");
        lblConfirmPassword.setText("");
        lblEmail.setText("");
    }

    /**
     * Text changed event handler. Validar que los campos login, email, full
     * name, password y confirm password están informados.
     *
     * @param observable The value being observed.
     * @param oldValue The old value of the observable.
     * @param newValue The new value of the observable.
     */
    private void textPropertyChange(ObservableValue observable,
            String oldValue,
            String newValue) {
        //If text fields values are longer than 25 (max value in Database), show error message and disable 
        //accept button
        if (tfLogin.getText().trim().length() > 25
                || tfEmail.getText().trim().length() > 25
                || tfFullName.getText().trim().length() > 25
                || cpPassword.getText().trim().length() > 25
                || cpConfirm.getText().trim().length() > 25) {
            btnAccept.setDisable(true);
        } //If text fields are empty disable accept buttton
        else if (tfLogin.getText().trim().isEmpty()
                || tfEmail.getText().trim().isEmpty()
                || tfFullName.getText().trim().isEmpty()
                || cpPassword.getText().trim().isEmpty()
                || cpConfirm.getText().trim().isEmpty()) {
            btnAccept.setDisable(true);
        } //Else, enable accept button
        else {
            btnAccept.setDisable(false);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
