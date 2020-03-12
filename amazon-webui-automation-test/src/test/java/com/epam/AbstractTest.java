package com.epam;

import com.epam.driver.DriverManager;
import org.testng.annotations.AfterClass;

public abstract class AbstractTest {
    @AfterClass
    public void quitDriver() {
        DriverManager.quit();
    }
}
