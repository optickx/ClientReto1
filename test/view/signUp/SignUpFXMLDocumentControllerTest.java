/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.signUp;

import app.App;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Testing class for SignUp view and controller. Tests SignUp view behavior
 * using TestFX framework.
 *
 * @author Nerea
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
    public void test1_testSetStage() {
        Stage stage = null;
        SignUpFXMLDocumentController instanceOf = new SignUpFXMLDocumentController();
        instanceOf.setStage(stage);
        verifyThat("#paneSignUp", isVisible());
    }

    /**
     * Test of initSignUp method, of class SignUpFXMLDocumentController.
     */
    @Test
    public void test2_InitSignUp() {
        Assert.assertEquals(VACIO, tfLogin.getText());
        Assert.assertEquals(VACIO, tfEmail.getText());
        Assert.assertEquals(VACIO, tfFullName.getText());
        Assert.assertEquals(VACIO, cpPassword.getText());
        Assert.assertEquals(VACIO, cpConfirm.getText());
        Assert.assertThat(tfLogin, isFocused());
        verifyThat("#btnAccept", isDisabled());
    }
    
    /**
     * Test if the accept button from SignUpFXMLDocumentController is enabled
     * when it shuld.
     */
    @Test
    public void test3_BtnAcceptIsEnabled() {
        //Login
        clickOn(tfLogin);
        write("FBe9");
        //Email
        clickOn(tfEmail);
        write("nereaoceja2003@gmail.com");
        //Full Name
        clickOn(tfFullName);
        write("Nerea Oceja");
        //Password 
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
    public void test4_BtnResetBtnWorks() {
        clickOn("#btnReset");
        Assert.assertEquals(VACIO, tfLogin.getText());
        Assert.assertEquals(VACIO, tfEmail.getText());
        Assert.assertEquals(VACIO, tfFullName.getText());
        Assert.assertEquals(VACIO, cpPassword.getText());
        Assert.assertEquals(VACIO, cpConfirm.getText());
        Assert.assertThat(tfLogin, isFocused());
    }
    
    /**
     * Test if the accept button from SignUpFXMLDocumentController is enabled
     * when it shuld.
     */
    @Test
    public void test5_BtnAcceptIsDisabled() {
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

}
