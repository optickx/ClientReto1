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
     * Metodo de inicialización de la ventana
     *
     * @param root Un objeto parent con el DOM cargado
     * @param user Un objeto user
     */
    public void initLogged(Parent root, User user) {
        LOGGER.info("Inicializando la ventana SignIn");
        //Se crea una escena a partir del parent
        Scene scene = new Scene(root);
        //Establece la escena en el escenario
        stageLogged.setScene(scene);
        //El nombre de la ventana es Logged
        stageLogged.setTitle("Logged");
        //Ventana no redimensionable
        stageLogged.setResizable(false);
        /*La ventana recibe un objeto Usuario
        y muestra el valor del campo
        Login*/
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
            //Cerrar la ventana con el metodo close()
            this.stageLogged.close();
            //Se inicia la ventana no modal SignIn
            Stage stageSignIn = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signIn/SignIn.fxml"));
            Parent root = (Parent) loader.load();
            //Conseguir el controldor de la ventana Logged
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
