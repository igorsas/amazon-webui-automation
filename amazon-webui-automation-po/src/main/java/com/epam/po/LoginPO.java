package com.epam.po;

import com.epam.annotation.InitializeByXpath;
import com.epam.driver.DriverManager;
import com.epam.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.PageFactory.initElements;


public class LoginPO {
    private WebDriver webDriver;

    @InitializeByXpath(xpath = "//*[@id='ap_email']")
    private WebElement email;
    @InitializeByXpath(xpath = "//*[@id='continue']")
    private WebElement submit;

    public LoginPO(){
        webDriver = DriverManager.getDriver();
        webDriver.get(PropertyLoader.getValue("initial_url"));
        initElements(webDriver, this);
    }

    public LoginPO setEmail(String email){
        this.email.sendKeys(email);
        return this;
    }

    public LoginPO submit(){
        this.submit.submit();
        return this;
    }
}
