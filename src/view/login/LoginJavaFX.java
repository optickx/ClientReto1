/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package view.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class LoginJavaFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Carga el ('DOM'--> document object model) documento xml y btiene un objeto parent
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        //Crea una escena a partir del Parent
        Scene scene = new Scene(root);
        stage.setTitle("Sign In");
        stage.setResizable(false);
        //Establece la escena en el escenario stage y la muestra
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
