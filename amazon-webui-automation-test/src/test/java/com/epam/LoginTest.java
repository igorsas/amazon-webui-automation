package com.epam;

import com.epam.driver.DriverManager;
import com.epam.po.LoginPO;
import com.epam.utils.PropertyLoader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest {

    @Test
    public static void test() {
        LoginPO loginPO = new LoginPO();
        loginPO.setEmail("groot.epam@gmail.com");
        loginPO.submit();
    }

    @BeforeMethod
    public void setStartedPage() {
        DriverManager.getDriver().get(PropertyLoader.getValue("initial_url"));
    }

    @AfterMethod
    public void quitDriver() {
        DriverManager.quit();
    }
}
