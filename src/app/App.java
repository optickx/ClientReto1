package app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        //Carga el ('DOM'--> document object model) documento xml y btiene un objeto parent
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/signIn/SignIn.fxml"));
            //Crea una escena a partir del Parent
            Scene scene = new Scene(root);
            stage.setTitle("Sign In");
            stage.setResizable(false);
            //Establece la escena en el escenario stage y la muestra
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}