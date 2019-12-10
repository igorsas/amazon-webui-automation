package com.epam;

import com.epam.po.LoginPO;
import org.testng.annotations.Test;


public class LoginTest extends AbstractTest {

    @Test
    public static void test() {
        LoginPO loginPO = new LoginPO();
        loginPO.setEmail("groot.epam@gmail.com")
                .goToPassword()
                .setPassword("iamgroot")
                .signIn();
    }
}
