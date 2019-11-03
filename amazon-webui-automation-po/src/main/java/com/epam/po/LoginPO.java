package com.epam.po;

import com.epam.annotation.InitializeByXpath;
import com.epam.driver.DriverManager;
import com.epam.element.Button;
import com.epam.element.TextArea;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.epam.factory.PageFactory.initElements;


public class LoginPO {
    private WebDriver webDriver;

    @InitializeByXpath(xpath = "//*[@id='ap_email']")
    private TextArea email;
    @InitializeByXpath(xpath = "//*[@id='continue']")
    private Button submit;

    public LoginPO(){
        webDriver = DriverManager.getDriver();
        initElements(webDriver, this);
    }

    public LoginPO setEmail(String email){
        this.email.sendKeys(email);
        return this;
    }

    public LoginPO submit(){
        this.submit.click();
        return this;
    }
}
