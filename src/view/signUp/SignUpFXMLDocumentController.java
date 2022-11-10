package view.signUp;

import except.EmailErrorException;
import except.UnmatchedPasswordsException;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.UserManagerFactory;
import logic.objects.User;

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

            //Carga los datos en un objeto User (PID se genera automaticamente, necesitamos saber la cantidad de usuarios en la base de datos
            //a continuación manda el objeto al método (sign up) de la implementación.
            String hola = "Hola";
            User user = new User();
            user.setLogin(tfLogin.getText());
            user.setEmail(tfEmail.getText());
            user.setPassword(cpPassword.getText());
            user.setFullName(tfFullName.getText());
            //0, hola, tfEmail.getText(), tfFullName.getText(), cpPassword.getText(), 0, 1, 2, null);
            UserManagerFactory.getAccess().signUp(user);

        } catch (EmailErrorException e) {
            lblEmail.setText(e.getMessage());
        } catch (UnmatchedPasswordsException e) {
            lblConfirmPassword.setText(e.getMessage());
        }/*catch (EmailExistsException e) {
            lblEmail.setText(e.getMessage());
        }*/
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
    
    public void initSignUp(Parent root) {
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
    private void handlerWindowShowing(WindowEvent event){
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
     /**
     * Declarar que el stage de la clase controlladora 
     * es el mismo al que le han pasado
     * @param stage donde se muestra la ventana
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}