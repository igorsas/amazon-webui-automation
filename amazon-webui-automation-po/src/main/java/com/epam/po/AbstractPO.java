package com.epam.po;

import com.epam.driver.DriverManager;
import org.openqa.selenium.WebDriver;

import static com.epam.factory.PageFactory.initElements;

public abstract class AbstractPO {
    protected WebDriver webDriver;
    public AbstractPO(){
        webDriver = DriverManager.getDriver();
        initElements(webDriver, this);
    }
}
