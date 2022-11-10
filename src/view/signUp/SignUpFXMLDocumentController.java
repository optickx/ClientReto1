package view.signUp;

import except.EmailErrorException;
import except.FullNameFormatErrorException;
import except.LoginFormatException;
import except.ServerException;
import except.PasswordErrorException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    @FXML
    private Pane paneSignUp;

    private Stage stage;
    private static final Logger LOGGER = Logger.getLogger("package view.signUp;");

    /**
     * Handle Action event on Accept button, if all goes well sends the
     * information to the server
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {
        try {
            Response response = null;
            //Validates format of the login
            if (Character.isDigit(tfLogin.getText().charAt(0)) || tfLogin.getText().contains(" ")) {
                throw new LoginFormatException();
            }
            //Validates the format of the email
            String patternEmail = "([a-z0-9]*)@([a-z]*).(com|org|cn|net|gov|eus)";
            if (!Pattern.matches(patternEmail, tfEmail.getText()) || tfEmail.getText().contains(" ")) {
                throw new EmailErrorException();
            }

            //Validates both passwords
            if (!(cpPassword.getText().equals(cpConfirm.getText())) || cpPassword.getText().contains(" ")) {
                throw new PasswordErrorException();
            }

            //Validates that the fullName doesn't have numbers
            for (int i = 0; i < tfFullName.getText().length(); i++) {
                if (Character.isDigit(tfFullName.getText().charAt(i))) {
                    throw new FullNameFormatErrorException();
                }
            }
            //As all the data format is correct, introduce it on the Data model object
            User user = new User();
            user.setLogin(tfLogin.getText());
            user.setEmail(tfEmail.getText());
            user.setPassword(cpPassword.getText());
            user.setFullName(tfFullName.getText());
            //The object is sent to the implementation
            response = UserManagerFactory.getAccess().signUp(user);

            if (response.getResponseType() != ResponseType.OK) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(response.getResponseType().name());
                alert.setContentText("Try again");
                alert.showAndWait();
            } else {
                //The user is succesfully created
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("SignUp");
                alert.setHeaderText(null);
                alert.setContentText("Signed Up succesfully, try logging in");
                alert.showAndWait();
                //The window SignUp closes, you return to the SignIn window
                Stage stageN = (Stage) btnAccept.getScene().getWindow();
                stageN.close();
            }

        } catch (EmailErrorException e) {
            lblEmail.setText(e.getMessage());
        } catch (PasswordErrorException e) {
            lblConfirmPassword.setText(e.getMessage());
        } catch (LoginFormatException e) {
            lblLogin.setText(e.getMessage());
        } catch (FullNameFormatErrorException | ServerException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
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
        //Empty all the fields 
        tfEmail.setText("");
        tfFullName.setText("");
        cpPassword.setText("");
        cpConfirm.setText("");
        tfLogin.setText("");
        lblConfirmPassword.setText("");
        lblEmail.setText("");
        //Focuses the login field
        tfLogin.requestFocus();
    }

    /**
     * Initializing method
     *
     * @param root root object with the DOM charged
     */
    public void initSignUp(Parent root) {
        LOGGER.info("Initializing SignUp window");
        Scene scene = new Scene(root);
        //Establishes the scene in the stage
        stage.setScene(scene);
        //Window title
        stage.setTitle("Sign Up");
        //Modal window
        stage.initModality(Modality.APPLICATION_MODAL);
        //Not resizable window
        stage.setResizable(false);
        //Set the Event handlers
        stage.setOnShowing(this::handlerWindowShowing);
        //Set the textfields with a listener
        tfEmail.textProperty().addListener(this::textPropertyChange);
        tfLogin.textProperty().addListener(this::textPropertyChange);
        tfFullName.textProperty().addListener(this::textPropertyChange);
        cpPassword.textProperty().addListener(this::textPropertyChange);
        cpConfirm.textProperty().addListener(this::textPropertyChange);
        stage.showAndWait();
    }

    private void handlerWindowShowing(WindowEvent event) {
        LOGGER.info("Starting SignUpFXMLDocumentController::handlerWindowShowing");
        //Accept button is disabled
        btnAccept.setDisable(true);
        //Login field is focused
        tfLogin.requestFocus();
        /*Hide the labels which show the exceptions*/
        tfEmail.setText("");
        tfFullName.setText("");
        cpPassword.setText("");
        cpConfirm.setText("");
        tfLogin.setText("");
        lblConfirmPassword.setText("");
        lblEmail.setText("");
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
    private void textPropertyChange(ObservableValue observable,
            String oldValue,
            String newValue) {
        //If text fields values are longer than 25 (max value in Database), show error message and disable 
        //accept button
        if (tfLogin.getText().trim().length() > 25) {
            tfLogin.setText(tfLogin.getText().substring(0, 25));
            new Alert(Alert.AlertType.ERROR, "The maximum lenght for the login is 25 characters\nCan't start with a digit.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }
        if (tfEmail.getText().trim().length() > 25) {
            tfEmail.setText(tfEmail.getText().substring(0, 25));
            new Alert(Alert.AlertType.ERROR, "The maximum lenght for the email is 25 characters.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }
        if (tfFullName.getText().trim().length() > 25) {
            tfFullName.setText(tfFullName.getText().substring(0, 25));
            new Alert(Alert.AlertType.ERROR, "The maximum lenght for the Full name is 25 characters.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }
        if (cpPassword.getText().trim().length() > 25) {
            cpPassword.setText(cpPassword.getText().substring(0, 25));
            new Alert(Alert.AlertType.ERROR, "The maximum lenght for the password is 25 characters.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }
        if (cpConfirm.getText().trim().length() > 25) {
            cpConfirm.setText(cpConfirm.getText().substring(0, 25));
            new Alert(Alert.AlertType.ERROR, "The maximum lenght for the password confirmation is 25 characters.", ButtonType.OK).showAndWait();
            btnAccept.setDisable(true);
        }
        //If text fields are empty disable accept buttton
        if (tfLogin.getText().trim().isEmpty()
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

    /**
     * Declarar que el stage de la clase controlladora es el mismo al que le han
     * pasado
     *
     * @param stage donde se muestra la ventana
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
