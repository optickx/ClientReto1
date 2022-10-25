package view.signUp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    /**
     * Handle Action event on Accept button
     *
     * @param event The action event object
     */
    @FXML
    private void handleAcceptButtonAction(ActionEvent event) {

        //Validar que todos los campos llenos
        if (this.tfEmail.getText().isEmpty() || this.tfLogin.getText().isEmpty() || this.tfFullName.getText().isEmpty() || this.cpPassword.getText().isEmpty() || this.cpConfirm.getText().isEmpty()) {
            //alerta de que necesitan estar llenos
            new Alert(Alert.AlertType.ERROR, "1 or more empty fields", ButtonType.OK).showAndWait();
        } else {
            //Validar que el Username no exista
            if (false) {
                //Aviso de que el usuario ya existe
                lblLogin.setText("The Username is already in use");
            } else {
                //Validar que el formato del email es correcto
                boolean correcto = false;
                // Patrón para validar el email
                String pattern = "([a-z0-9]*)@([a-z]*).(com|org|cn|net|gov|eus)";
                if (Pattern.matches(pattern, tfEmail.getText())) {
                    correcto = true;
                }

                //Validar que la confirm password coincida
                if (!(cpPassword.getText().equals(cpConfirm.getText()))) {
                    //Aviso de contraseñas no coincidentes
                    lblConfirmPassword.setText("Passwords do not match");
                } else {
                    lblConfirmPassword.setText("");
                }
                if (!correcto) {
                    //Aviso de formato de Email erroneo
                    lblEmail.setText("Invalid Email format \ntry for example with @tartanga.eus/gmail.com or hotmail.com");
                } else {
                    lblEmail.setText("");

                    //Carga los datos en un objeto User (PID se genera automaticamente, necesitamos saber la cantidad de usuarios en la base de datos
                    //a continuación manda el objeto al método (sign up) de la implementación.
                }

            }
        }
    }

    /**
     * Handle Action event on Reset button
     *
     * @param event The action event object
     */
    @FXML
    private void handleResetButtonAction(ActionEvent event) {
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
