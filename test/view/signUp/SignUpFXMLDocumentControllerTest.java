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
        cpPassword = lookup("#cpPassword").query();
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
     * Test if the accept button from SignUpFXMLDocumentController is enabled
     * when it shuld.
     */
    @Test
    public void test2_1_BtnAcceptIsEnabled() {
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
        verifyThat("#btnAccept", isEnabled());
    }

    /**
     * Test if the reset button from SignUpFXMLDocumentController works.
     */
    @Test
    public void test2_2_BtnResetBtnWorks() {
        clickOn("#btnReset");
        Assert.assertEquals(VACIO, tfLogin.getText());
        Assert.assertEquals(VACIO, tfEmail.getText());
        Assert.assertEquals(VACIO, tfFullName.getText());
        Assert.assertEquals(VACIO, cpPassword.getText());
        Assert.assertEquals(VACIO, cpConfirm.getText());
    }

    /**
     * Test if the accept button from SignUpFXMLDocumentController is enabled
     * when it shuld.
     */
    @Test
    public void test2_3_BtnAcceptIsDisabled() {
        //Login
        clickOn(tfLogin);
        write("FBe9");
        verifyThat("#btnAccept", isDisabled());
        eraseText(4);
        //Email
        clickOn(tfEmail);
        write("nereaoceja2003@gmail.com");
        verifyThat("#btnAccept", isDisabled());
        eraseText(24);
        //Full Name
        clickOn(tfFullName);
        write("Nerea Oceja");
        verifyThat("#btnAccept", isDisabled());
        eraseText(11);
        //Password 
        clickOn(cpPassword);
        write("abcd*1234");
        verifyThat("#btnAccept", isDisabled());
        eraseText(9);
        //Password confirm
        clickOn(cpConfirm);
        write("abcd*1234");
        verifyThat("#btnAccept", isDisabled());
        eraseText(9);
    }

    /**
     * Test that the login has not in the firt character a number or any blank
     * space
     */
    @Test
    public void test3_1_NoNumberFirstCharLoginOrBlankSpace() {
        clickOn("#btnReset");
        clickOn(tfLogin);
        write("1FBe9");
        clickOn(tfEmail);
        write("a@gmail.com");
        clickOn(tfFullName);
        write("abcd efg");
        clickOn(cpPassword);
        write("abcd*1234");
        clickOn(cpConfirm);
        write("abcd*1234");

        clickOn("#btnAccept");
        verifyThat("Error with the format of the login, can't start with a number or contain a blank space", isVisible());
        clickOn(tfLogin);
        eraseText(5);
        write("FBe 9");
        verifyThat("Error with the format of the login, can't start with a number or contain a blank space", isVisible());
        eraseText(2);
    }

    /**
     * Test that the email format is correct and that it doesn`t have any blank
     * spaces
     */
    @Test
    public void test3_2_EmailFormatOrBlankSpace() {
        clickOn("#btnReset");
        clickOn(tfLogin);
        write("FBe9");
        clickOn(tfFullName);
        write("abcd efg");
        clickOn(cpPassword);
        write("abcd*1234");
        clickOn(cpConfirm);
        write("abcd*1234");
        clickOn(tfEmail);
        eraseText(6);
        clickOn("#btnAccept");
        verifyThat("Error in the email format, must be a valid email address without blank spaces", isVisible());
        clickOn(tfEmail);
        eraseText(15);
        write("ner d@gmail.com");
        clickOn("#btnAccept");
        verifyThat("Error in the email format, must be a valid email address without blank spaces", isVisible());
        clickOn(tfEmail);
        tfEmail.clear();
        write("nerd@gmail.com");
    }

    /**
     * Test that when you put a password that doesnÂ´t mach with the confirm, a
     * label appears showing the error
     */
    @Test
    public void test3_3_PasswordsNotMatch() {
        clickOn("#btnReset");
        clickOn(tfLogin);
        write("FBe9");
        clickOn(tfEmail);
        write("a@gmail.com");
        clickOn(tfFullName);
        write("abcd efg");
        clickOn(cpPassword);
        write("abcd*1234");
        clickOn(cpConfirm);
        write("abcd*1234");
        clickOn(cpPassword);
        eraseText(5);
        clickOn("#btnAccept");
        verifyThat("Password and Confirm Password do not match or are blank spaces", isVisible());
        clickOn("#btnReset");
    }

    /**
     * Test that the Full Name has not any number
     */
    @Test
    public void test3_4_NoNumbersAtFullName() {
        clickOn(tfLogin);
        write("FBe9");
        clickOn(tfEmail);
        write("a@gmail.com");
        clickOn(cpPassword);
        write("abcd*1234");
        clickOn(cpConfirm);
        write("abcd*1234");
        clickOn(tfFullName);
        write("fiona1 7tu");
        clickOn("#btnAccept");
        verifyThat("Your name can't have digits on it.", isVisible());
        push(KeyCode.ENTER);
    }

    /**
     * Test if the max length comprobation is ok
     */
    @Test
    public void test5_MaxLength() {
        clickOn("#btnReset");
        clickOn(tfLogin);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("The maximum lenght for the login is 25 characters Can't start with a digit.", isVisible());
        push(KeyCode.ENTER);
        eraseText(1);
        clickOn(tfEmail);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("The maximum lenght for the email is 25 characters.", isVisible());
        push(KeyCode.ENTER);
        eraseText(1);
        clickOn(tfFullName);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("The maximum lenght for the Full name is 25 characters.", isVisible());
        push(KeyCode.ENTER);
        eraseText(1);
        clickOn(cpPassword);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("The maximum lenght for the password is 25 characters.", isVisible());
        push(KeyCode.ENTER);
        clickOn(cpConfirm);
        write("aaaaaaaaaaaaaaaaaaaaaaaaaa");
        verifyThat("The maximum lenght for the password confirmation is 25 characters.", isVisible());
        push(KeyCode.ENTER);
    }

    /**
     * Test that a valid user registers
     */
    @Test
    public void test6_1_Registers() {
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

    /**
     * Test that a server error alert pop on screen. The server side must be
     * turned off to succesfully test this feature.
     */
    @Ignore
    @Test
    public void test6_2_NoRegisterServerDown() {
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
        verifyThat("Error in the server side.\nTry again later.", isVisible());
    }
}
