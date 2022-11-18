/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.signUp;

import app.App;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isFocused;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * Testing class for SignUp view and controller. Tests SignUp view behavior
 * using TestFX framework.
 *
 * @author Nerea,Eneko
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpFXMLDocumentControllerTest extends ApplicationTest {

    private TextField tfLogin;
    private TextField tfEmail;
    private TextField tfFullName;
    private TextField cpPassword;
    private TextField cpConfirm;
    private Button btnAccept;
    private Button btnReset;
    private final String VACIO = "";

    /**
     * Starts application to be tested.
     *
     * @param stage Primary Stage object
     * @throws Exception If there is any error
     */
    @Override
    public void start(Stage stage) throws Exception {
        new App().start(stage);
        clickOn("#btnSignUp");
        tfLogin = lookup("#tfLogin").query();
        tfEmail = lookup("#tfEmail").query();
        tfFullName = lookup("#tfFullName").query();
        cpPassword = lookup("#cpPass").query();
        cpConfirm = lookup("#cpConfirm").query();
        btnAccept = lookup("#btnAccept").query();
        btnReset = lookup("#btnReset").query();

    }

    /**
     * Test of setStage method, of class SignUpFXMLDocumentController.
     */
    @Test
    public void test1_1_testSetStage() {
        Stage stage = null;
        SignUpFXMLDocumentController instanceOf = new SignUpFXMLDocumentController();
        instanceOf.setStage(stage);
        verifyThat("#paneSignUp", isVisible());
    }

    /**
     * Test of initSignUp method, of class SignUpFXMLDocumentController.
     */
    @Ignore
    @Test
    public void test1_2_InitSignUp() {
        Assert.assertEquals(VACIO, tfLogin.getText());
        Assert.assertEquals(VACIO, tfEmail.getText());
        Assert.assertEquals(VACIO, tfFullName.getText());
        Assert.assertEquals(VACIO, cpPassword.getText());
        Assert.assertEquals(VACIO, cpConfirm.getText());
        verifyThat("#btnAccept", isDisabled());
    }

    /**
     * Test that a valid user registers
     */
    @Test
    public void test2_3_Registers() {
        push(KeyCode.ENTER);
        clickOn("#btnReset");
        //Login
        write("FBe9");
        //Email
        clickOn(tfEmail);
        write("nereaoceja2003@gmail.com");
        //Full Name
        clickOn(tfFullName);
        write("Nerea Oceja");
        //Password 
        clickOn("#cpPassword");
        clickOn(cpPassword);
        write("abcd*1234");
        //Password confirm
        clickOn(cpConfirm);
        write("abcd*1234");
        clickOn(btnAccept);
        verifyThat("Signed Up succesfully, try logging in", isVisible());
        
    }

    
    @Test
    public void test2_1_LoginExistsException(){    
        clickOn("#btnReset");
        //Login
        write("roke");
        //Email
        clickOn(tfEmail);
        write("rokeitumendi@gmail.com");
        //Full Name
        clickOn(tfFullName);
        write("Nerea Oceja");
        //Password 
        clickOn("#cpPassword");
        clickOn(cpPassword);
        write("abcd*1234");
        //Password confirm
        clickOn(cpConfirm);
        write("abcd*1234");
        clickOn(btnAccept);
        verifyThat("LOGIN_EXISTS_ERROR", isVisible());
        
    }
    
    @Test
    public void test2_2_EmailExistsException(){
        push(KeyCode.ENTER);
        clickOn("#btnReset");
        //Login
        write("elKiko");
        //Email
        clickOn(tfEmail);
        write("roke@gmail.com");
        //Full Name
        clickOn(tfFullName);
        write("Nerea Oceja");
        //Password 
        clickOn("#cpPassword");
        clickOn(cpPassword);
        write("abcd*1234");
        //Password confirm
        clickOn(cpConfirm);
        write("abcd*1234");
        clickOn(btnAccept);
        verifyThat("EMAIL_EXISTS_ERROR", isVisible());
        
    }
    
}
