/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.logged;

import app.App;
import javafx.stage.Stage;
import junit.framework.TestCase;
import app.App;
import java.util.concurrent.TimeoutException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.objects.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author 2dam
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoggedFXMLDocumentControllerTest extends ApplicationTest {

    private TextField tfLogin;
    private TextField cpPassword;
    private Label lblWelcome;
    private Button btnAccept;
    /**
     * Starts application to be tested.
     *
     * @param stage Primary Stage object
     * @throws Exception If there is any error
     */
    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
        tfLogin = lookup("#tfLogin").query();
        cpPassword = lookup("#cpPassword").query();
        btnAccept = lookup("#btnAccept").query();
    }
    
    /**
     * Logs into the application, tests that the Logged pane is visible and verifies
     * that the Sign In pane is visible after clicking on log out.
     */
    @Test
    public void test1_OpenLogged() {
        clickOn(tfLogin);
        write("EnekoRuiz");
        clickOn(cpPassword);
        write("abcd*1234");
        clickOn(btnAccept);
        verifyThat("#paneLogged", isVisible());
        clickOn("#btnLogOut");
        verifyThat("#paneLogIn", isVisible());
    }
}
