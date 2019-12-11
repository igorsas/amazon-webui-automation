package com.epam.po;

import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;
import com.epam.element.CheckBox;
import com.epam.element.TextBox;

import static com.epam.waiter.WaitElement.getClickableCondition;
import static com.epam.waiter.WaitElement.waitFor;


public class LoginPO extends AbstractPO{

    @InitializeByXpath(locator = "//*[@id='ap_email']")
    private TextBox email;
    @InitializeByXpath(locator = "//*[@id='continue']")
    private Button continuE;
    @InitializeByXpath(locator = "//*[@id='ap_password']")
    private TextBox password;
    @InitializeByXpath(locator = "//*[@id='signInSubmit']")
    private Button signIn;
    @InitializeByXpath(locator = "//*[@name='code']")
    private TextBox otp;
    @InitializeByXpath(locator = "//*[@id='a-autoid-0']//input[@type='submit']")
    private Button submitOtp;
    @InitializeByXpath(locator = "//*[@name='rememberMe']")
    private CheckBox keepMeSignedIn;

    public LoginPO setEmail(String email){
        this.email.sendKeys(email);
        return this;
    }

    public LoginPO setPassword(String password){
        this.password.sendKeys(password);
        return this;
    }

    public LoginPO setOtp(String otp){
        this.otp.sendKeys(otp);
        return this;
    }

    public LoginPO continuE(){
        waitFor(getClickableCondition(continuE));
        this.continuE.click();
        return this;
    }

    public LoginPO selectKeepMeSignIn(){
        waitFor(getClickableCondition(keepMeSignedIn));
        this.keepMeSignedIn.selectCheckBox();
        return this;
    }

    public LoginPO signIn(){
        waitFor(getClickableCondition(signIn));
        this.signIn.click();
        return this;
    }

    public LoginPO submitOtp(){
        waitFor(getClickableCondition(submitOtp));
        this.submitOtp.click();
        return this;
    }
}
