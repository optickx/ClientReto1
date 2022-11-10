/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package view.logged;

import app.App;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import logic.objects.*;
import view.signIn.SignInFXMLDocumentController;

/**
 *
 * @author Nerea and Dani
 */
public class LoggedFXMLDocumentController {

    @FXML
    private Label lblWelcome;
    @FXML
    private Button btnLogOut;
    @FXML
    private ImageView imageViewLogged;

    private Stage stageLogged;
    private static final Logger LOGGER = Logger.getLogger("package view.logged;");

    /**
     * Method of initializing the window
     *
     * @param root Parent object with the DOM charged
     * @param user User object
     */
    public void initLogged(Parent root, User user) {
        LOGGER.info("Initializing SignIn window");
        //Creates an scene
        Scene scene = new Scene(root);
        //Establishes the scenary
        stageLogged.setScene(scene);
        //Set window title
        stageLogged.setTitle("Logged");
        //Set resizability
        stageLogged.setResizable(false);
        //The windows shows a welcome to the user logged with its login value
        lblWelcome.setText("Welcome " + user.getLogin() + " to our aplication");
        stageLogged.show();
    }
    /**
     * Cuando clicas en el boton btnLogOut te cierra la
     * @param event evento de clicar en el boton de LogOut
     */
    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {
        try {
            //Closes the window
            this.stageLogged.close();
            //Initializes the not modal Sign In window
            Stage stageSignIn = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signIn/SignIn.fxml"));
            Parent root = (Parent) loader.load();
            //Obtains the controller of the logged window
            SignInFXMLDocumentController controller = (SignInFXMLDocumentController) loader.getController();
            controller.setStage(stageSignIn);
            controller.initSignIn(root);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * Declarar que el stage de la clase controlladora 
     * es el mismo al que le han pasado
     * @param stage donde se muestra la ventana
     */
    public void setStage(Stage stage) {
        this.stageLogged = stage;
    }

}
