/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.signUp;

import app.App;
import javafx.stage.Stage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 * Testing class for Login view and controller. Tests login view behavior using
 * TestFX framework.
 *
 * @author Nerea
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpFXMLDocumentControllerTest extends ApplicationTest {

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
    }

    /**
     * Test of initSignUp method, of class SignUpFXMLDocumentController.
     */
    @Test
    public void test1_InitSignUp() {
        verifyThat("#tfLogin", hasText(""));
        verifyThat("#tfEmail", hasText(""));
        verifyThat("#tfFullName", hasText(""));
        verifyThat("#cpPassword", hasText(""));
        verifyThat("#cpConfirm", hasText(""));
        verifyThat("#btnAccept", isDisabled());
    }

    /**
     * Test of setStage method, of class SignUpFXMLDocumentController.
     */
    @Test
    public void testSetStage() {
        Stage stage = null;
        SignUpFXMLDocumentController instanceOf = new SignUpFXMLDocumentController();
        instanceOf.setStage(stage);
    }

    /**
     * Test if the accept button from SignUpFXMLDocumentController is enabled
     * when it shuld.
     */
   /*@Test
    public void test2_BtnAcceptIsDisabled() {
        //Login
        clickOn("#tfLogin");
        write("FBe9");
        verifyThat("#btnAccept", isDisabled());
        eraseText(4);
        //Email
        clickOn("#tfEmail");
        write("nereaoceja2003@gmail.com");
        verifyThat("#btnAccept", isDisabled());
        eraseText(24);
        //Full Name
        clickOn("#tfFullName");
        write("Nerea Oceja");
        verifyThat("#btnAccept", isDisabled());
        eraseText(11);
        //Password 
        clickOn("#cpPassword");
        write("abcd*1234");
        verifyThat("#btnAccept", isDisabled());
        eraseText(9);
        //Password confirm
        clickOn("#cpConfirm");
        write("abcd*1234");
        verifyThat("#btnAccept", isDisabled());
        eraseText(9);
    }*/

    /**
     * Test if the accept button from SignUpFXMLDocumentController is enabled
     * when it shuld.
     */
    @Test
    public void test3_BtnAcceptIsEnabled() {
        //Login
        clickOn("#tfLogin");
        write("FBe9");
        //Email
        clickOn("#tfEmail");
        write("nereaoceja2003@gmail.com");
        //Full Name
        clickOn("#tfFullName");
        write("Nerea Oceja");
        //Password 
        clickOn("#cpPassword");
        write("abcd*1234");
        //Password confirm
        clickOn("#cpConfirm");
        write("abcd*1234");
        verifyThat("#btnAccept", isEnabled());
    }

    /**
     * Test if the reset button from SignUpFXMLDocumentController works.
     */
    @Test
    public void test4_BtnResetBtnWorks() {
        clickOn("#btnReset");
        verifyThat("#tfLogin", hasText(""));
        verifyThat("#tfEmail", hasText(""));
        verifyThat("#tfFullName", hasText(""));
        verifyThat("#cpPassword", hasText(""));
        verifyThat("#cpConfirm", hasText(""));
        verifyThat("#btnAccept", isDisabled());
    }

}
