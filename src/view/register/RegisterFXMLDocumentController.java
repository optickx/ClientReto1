package view.register;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegisterFXMLDocumentController implements Initializable {

    /**
     * @author Eneko and Roke
     */
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField pfPassword;
    @FXML
    private TextField pfConfirmPassword;
    @FXML
    private TextField tfUsername;
    @FXML
    private Button btAccept;
    @FXML
    private Button btReset;
    @FXML
    private Label lblUsername;
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
        if (this.tfEmail.getText().isEmpty() || this.tfUsername.getText().isEmpty() || this.tfFullName.getText().isEmpty() || this.pfPassword.getText().isEmpty() || this.pfConfirmPassword.getText().isEmpty()) {
            //alerta de que necesitan estar llenos
            new Alert(Alert.AlertType.ERROR, "1 or more empty fields", ButtonType.OK).showAndWait();
        } else {
            //Validar que el Username no exista
            if (false) {
                //Aviso de que el usuario ya existe
                lblUsername.setText("The Username is already in use");
            } else {
                //Validar que el formato del email es correcto
                boolean correcto = false;
                // Patrón para validar el email
                String pattern = "([a-z0-9]*)@([a-z]*).(com|org|cn|net|gov|eus)";
                if (Pattern.matches(pattern, tfEmail.getText())) {
                    correcto = true;
                }

                //Validar que la confirm password coincida
                if (!(pfPassword.getText().equals(pfConfirmPassword.getText()))) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //El foco se centra en el Username.
        tfUsername.requestFocus();
        //Ventana no redimensionable (Hecho en la clase RegisterJavaFX)

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
        pfPassword.setText("");
        pfConfirmPassword.setText("");
        tfUsername.setText("");
        lblConfirmPassword.setText("");
        lblEmail.setText("");
        // enfoca el campo username.
        tfUsername.requestFocus();
    }
    private static final Logger logger = Logger.getLogger("package basicloginjavafxapplication1");

}