package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Carga el ('DOM'--> document object model) documento xml y btiene un objeto parent
        Parent root = FXMLLoader.load(getClass().getResource("../view/SignIn/SignIn.fxml"));

        //Crea una escena a partir del Parent
        Scene scene = new Scene(root);
        stage.setTitle("Sign In");
        stage.setResizable(false);
        //Establece la escena en el escenario stage y la muestra
        stage.setScene(scene);
        stage.show();
    }

}
