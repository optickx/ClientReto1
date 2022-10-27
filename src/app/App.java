package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.logged.LoggedFXMLDocumentController;
import view.signIn.SignInFXMLDocumentController;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signIn/SignIn.fxml"));
                Parent root = (Parent) loader.load();
                //Crea una escena a partir del Parent 
                //Scene scene = new Scene(root);
                //stage = new Stage();
                
                //COnseguir el controlador de la ventana Logged
                SignInFXMLDocumentController controller = (SignInFXMLDocumentController) loader.getController();
                controller.setStage(stage);
                controller.initSignIn(root);
                
    }

}
