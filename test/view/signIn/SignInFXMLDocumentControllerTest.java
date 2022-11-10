/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.signIn;

import app.App;
import except.ServerException;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import view.logged.LoggedFXMLDocumentController;

/**
 *
 * @author Eneko
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInFXMLDocumentControllerTest extends ApplicationTest{
    private TextField tfLogin;
    private PasswordField cpPassword;
    private Button btnAccept;
    private Button btnSignUp;
    private final String VACIO = "";
    
    @Override
    public void start(Stage stage) throws Exception{
        new App().start(stage);
        tfLogin = lookup("#tfLogin").query();
        cpPassword = lookup("#cpPassword").query();
        btnAccept = lookup("#btnAccept").query();
        btnSignUp = lookup("#btnSignUp").query();
    }
    
   /**
     * Tests that Login text field is focused and
     * the Accept button is disabled when the
     * user starts the application
     */
    @Test
    public void test0_InitialState() {
        verifyThat(tfLogin, isFocused());
        assertEquals(VACIO, tfLogin.getText());
        assertEquals(VACIO, cpPassword.getText());
        verifyThat(btnAccept, isDisabled());
    }
    
    /**
     * Tests that button Accept is disabled if user or password fields are empty.
    */ 
    @Test
    public void test1_AceptarIsDisabled() {
        clickOn(tfLogin);
        write("username");
        verifyThat(btnAccept, isDisabled());
        eraseText(8);
        clickOn(cpPassword);
        write("password");
        verifyThat(btnAccept, isDisabled());
        eraseText(8);
        verifyThat(btnAccept, isDisabled());
    }
    
    /**
     * Test test that button Accept is enabled when login and password fields are full.
    */
    @Test
    public void test2_AceptarIsEnabled() {
        clickOn(tfLogin);
        write("username");
        clickOn(cpPassword);
        write("password");
        verifyThat(btnAccept, isEnabled());
    }
    
    /**
     * Tests that an alert pops when the user writes more than 25 characters in 
     * either login or password field.
    */
    @Test
    public void test3_ShowsMaxCharacterError(){
        clickOn(tfLogin);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("The maximum lenght for the login is 25 characters.", isVisible());
        push(KeyCode.ENTER);
        eraseText(1);
        clickOn(cpPassword);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("The maximum lenght for the password is 25 characters.", isVisible());
        push(KeyCode.ENTER);
    }
    
    /**
     * Tests that an alert is shown when the user tries to log with and existing
     * username that doesn´t match the password in the database
     */
    @Test
    public void test4_IncorrectPasswordError(){
        clickOn(tfLogin);
        write("EnekoRuiz");
        clickOn(cpPassword);
        write("tortillas");
        clickOn(btnAccept);
        verifyThat("Try again", isVisible());
        push(KeyCode.ENTER);
    }
    
    /**
     * Tests that an error is shown to the user when he starts the Login field 
     * with a number.
     */
    @Test
    public void test5_LoginFormatError(){
        clickOn(tfLogin);
        write("1EnekoRuiz");
        clickOn(cpPassword);
        write("abcd*1234");
        clickOn(btnAccept);
        verifyThat("Error with the format of the login, can't start with a number or contain a blank space", isVisible());
        push(KeyCode.ENTER);
        clickOn(tfLogin);
        eraseText(10);
        write("Eneko Ruiz");
        clickOn(btnAccept);
        verifyThat("Error with the format of the login, can't start with a number or contain a blank space", isVisible());
        push(KeyCode.ENTER);
    }
    
    /**
     * Tests that an error is shown to the user when he writes a blank space
     * in the password field
     */
    @Test
    public void test6_PasswordFormatError(){
        clickOn(tfLogin);
        write("EnekoRuiz");
        clickOn(cpPassword);
        write("abcd 1234");
        clickOn(btnAccept);
        verifyThat("Password can't contain blank spaces", isVisible());
        push(KeyCode.ENTER);
    }
    
    /**
     * Tests that Logged view opens when a user that is registered in the database
     * clicks on sign in button. 
    */
    @Test
    public void test7_LoggedOpenedOnAcceptClick() {
        clickOn(tfLogin);
        write("EnekoRuiz");
        clickOn(cpPassword);
        write("abcd*1234");
        clickOn(btnAccept);
        verifyThat("#paneLogged", isVisible());
        clickOn("#btnLogOut");
    }    
    
    /**
     * Tests that Sign Up view is opened when button Aceptar is 
     * clicked
    */
    @Test
    public void test8_SignUpOpenedOnAcceptClick(){
        clickOn(btnSignUp);
        verifyThat("#paneSignUp", isVisible());
    }
    
    /**
     * This test works only when the server side is off. To test this, make sure
     * that the server side of the application isn't working.
     */
    @Ignore
    @Test
    public void test9_ServerError(){
        clickOn(tfLogin);
        write("EnekoRuiz");
        clickOn(cpPassword);
        write("abcd*1234");
        clickOn(btnAccept);
        verifyThat("Error in the server side.\nTry again later.", isVisible());
        push(KeyCode.ENTER);
    }

}
