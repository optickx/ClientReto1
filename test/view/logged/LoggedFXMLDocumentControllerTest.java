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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.objects.User;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
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
        
        //Login
        clickOn(tfLogin);
        write("FBe9");
        //Password 
        clickOn(cpPassword);
        write("abcd*1234");
        
        clickOn("#btnAccept");
        lblWelcome = lookup("#lblWelcome").query();

    }

    /**
     * Test of setStage method, of class LoggedFXMLDocumentController.
     */
    public void test1_SetStage() {
        Stage stage = null;
        LoggedFXMLDocumentController instanceOf = new LoggedFXMLDocumentController();
        instanceOf.setStage(stage);
        verifyThat("#paneLogged", isVisible());
    }
    
    /**
     * Test of initLogged method, of class LoggedFXMLDocumentController.
     */
    public void test2_InitLogged() {
        Assert.assertEquals("Welcome FBe9 to our aplication",lblWelcome.getText());
    }
}
