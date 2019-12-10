package com.epam.po;

import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;
import com.epam.element.TextBox;
import com.epam.waiter.WaitElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.annotation.Nullable;

import static com.epam.factory.PageFactory.initElements;
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

    public LoginPO setEmail(String email){
        this.email.sendKeys(email);
        return this;
    }

    public LoginPO setPassword(String password){
        this.password.sendKeys(password);
        return this;
    }

    public LoginPO goToPassword(){
        waitFor(getClickableCondition(continuE), 20);
        this.continuE.click();
        return this;
    }

    public LoginPO signIn(){
        waitFor(getClickableCondition(signIn), 20);
        this.signIn.click();
        return this;
    }
}
