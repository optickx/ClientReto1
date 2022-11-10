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
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;
import view.logged.LoggedFXMLDocumentController;

/**
 *
 * @author 2dam
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignInFXMLDocumentControllerTest extends ApplicationTest{
    private TextField tfLogin;
    private PasswordField cpPassword;
    private Button btnAccept;
    private Button btnSignUp;
    private final String VACIO = "";
    private Pane paneLogged;
    
    @Override
    public void start(Stage stage) throws Exception{
        new App().start(stage);
        tfLogin = lookup("#tfLogin").query();
        cpPassword = lookup("#cpPassword").query();
        btnAccept = lookup("#btnAccept").query();
        btnSignUp = lookup("#btnSignUp").query();
    }
    
    @Test
    public void test1_InitialState() {
        assertEquals(VACIO, tfLogin.getText());
        assertEquals(VACIO, cpPassword.getText());
        verifyThat(btnAccept, isDisabled());
    }
    
    /**
     * Test test that button Aceptar is disabled if user or password fields are empty.
    */ 
    @Test
    public void test2_AceptarIsDisabled() {
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
     * Test test that button Aceptar is enabled when login and password fields are full.
    */
    @Test
    public void test3_AceptarIsEnabled() {
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
    public void test4_ShowsMaxCharacterError(){
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
     * Tests that Logged view opens when a user that is registered in the database
     * clicks on sign in button. 
    */ 
    
    
    @Test
    public void test5_LoggedOpenedOnAcceptClick() {
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
    public void test7_SignUpOpenedOnAcceptClick(){
        clickOn(btnSignUp);
        verifyThat("#paneSignUp", isVisible());
    }
    
    /**
     * When you tray to signIn and the server has an error
     * (the server needs to not be running)
    */ 
    
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
