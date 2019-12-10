package com.epam;

import com.epam.driver.DriverManager;
import com.epam.utils.PropertyLoader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {
    @BeforeMethod
    public void setStartedPage() {
        DriverManager.getDriver().get(PropertyLoader.getValue("initial_url"));
    }

    @AfterMethod
    public void quitDriver() {
        DriverManager.quit();
    }
}
