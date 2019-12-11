package com.epam;

import com.epam.driver.DriverManager;
import com.epam.utils.PropertyLoader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static com.epam.constant.GeneralConstants.DRIVER_PROPERTIES_NAME;
import static com.epam.driver.DriverManager.clearCache;

public abstract class AbstractTest {
    @BeforeClass
    public void setStartedPage() {
        DriverManager.getDriver().get(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "initial_url"));
    }

    @BeforeMethod
    public void clearDriver() {
        clearCache();

    }

    @AfterClass
    public void quitDriver() {
        DriverManager.quit();
    }
}
