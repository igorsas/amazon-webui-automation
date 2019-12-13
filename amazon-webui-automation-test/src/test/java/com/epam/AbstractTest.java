package com.epam;

import com.epam.driver.DriverManager;
import com.epam.utils.PropertyLoader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.epam.constant.GeneralConstants.DRIVER_PROPERTIES_NAME;

public abstract class AbstractTest {
    @BeforeClass
    public void createDriver() {
        DriverManager.getDriver().get(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "initial_url"));
    }

    @BeforeMethod
    public void setStartedPage() {
        DriverManager.getDriver().navigate().to(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "initial_url"));
    }

    @AfterMethod
    public void clearDriver() {
        DriverManager.clear();
    }

    @AfterClass
    public void quitDriver() {
        DriverManager.quit();
    }
}