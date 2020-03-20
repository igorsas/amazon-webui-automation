package com.epam;

import com.epam.driver.DriverManager;
import org.testng.annotations.AfterMethod;

public abstract class AbstractTest {
    @AfterMethod
    public void tearDown() {
        DriverManager.quit();
    }
}
