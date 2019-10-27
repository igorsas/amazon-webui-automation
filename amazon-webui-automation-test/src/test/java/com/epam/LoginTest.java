package com.epam;

import com.epam.driver.DriverManager;
import com.epam.po.LoginPO;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class LoginTest {

    @AfterMethod
    public void quitDriver() {
        DriverManager.quit();
    }

    @Test
    public static void test(){
        LoginPO loginPO = new LoginPO();
        loginPO.setEmail("groot.epam@gmail.com");
        loginPO.submit();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
