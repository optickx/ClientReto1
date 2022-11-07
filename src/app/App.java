package app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import view.signIn.SignInFXMLDocumentController;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signIn/SignIn.fxml"));
            Parent root = (Parent) loader.load();
            //Conseguir el controldor de la ventana SignIn
            SignInFXMLDocumentController controller = (SignInFXMLDocumentController) loader.getController();
            controller.setStage(stage);
            controller.initSignIn(root);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
