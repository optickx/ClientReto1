package view.signUp;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import except.EmailErrorException;
import except.EmailExistsException;
import except.LoginExistsException;
import except.LoginFormatException;
import except.ServerException;
import except.UnmatchedPasswordsException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.UserManagerFactory;
import logic.objects.User;

public class SignUpFXMLDocumentController implements Initializable {

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

    /**
     * Handle Action event on Accept button
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {
        try {
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
            UserManagerFactory.getAccess().signUp(user);
            //Si el registro ha sido correcto
            //la ventana Sign Up se cierra, devolviendo el control a la
            //ventana Sign In.

            //Localizar la ventana Logged
            Stage stageN = (Stage) btnAccept.getScene().getWindow();
            //Cerrar la ventana Logged
            stageN.close();

            //A través de una ventana emergente se mostrará el mensaje
            //de la excepción si la hubiera.
        } catch (EmailErrorException | EmailExistsException e) {
            lblEmail.setText(e.getMessage());
        } catch (UnmatchedPasswordsException e) {
            lblConfirmPassword.setText(e.getMessage());
        } catch (LoginExistsException | LoginFormatException e) {
            lblLogin.setText(e.getMessage());
        } catch (ServerException e) {
            LOGGER.info(e.getMessage());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
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
    private static final Logger logger = Logger.getLogger("package basicloginjavafxapplication1");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSignUp();
    }

    public void initSignUp() {
        //El foco se centra en el login
        tfLogin.requestFocus();
        //Deshabilita el botón accept
        btnAccept.setDisable(true);
        //Oculta los labels 
        //que muestran los mensajes de las excepciones
        tfEmail.setText("");
        tfFullName.setText("");
        cpPassword.setText("");
        cpConfirm.setText("");
        tfLogin.setText("");
        lblConfirmPassword.setText("");
        lblEmail.setText("");
        //Poner a los textFields en escucha
        tfEmail.textProperty().addListener(this::textPropertyChange);
        tfLogin.textProperty().addListener(this::textPropertyChange);
        tfFullName.textProperty().addListener(this::textPropertyChange);
        cpPassword.textProperty().addListener(this::textPropertyChange);
        cpConfirm.textProperty().addListener(this::textPropertyChange);
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
}
