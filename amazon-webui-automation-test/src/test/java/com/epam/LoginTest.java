package com.epam;

import com.epam.assertion.HomePageAssertion;
import com.epam.po.LoginPO;
import com.epam.utils.GmailUtil;
import org.testng.annotations.Test;

import static com.epam.utils.DriverUtil.getTitle;
import static com.epam.waiter.WaitElement.pageLoaded;


public class LoginTest extends AbstractTest {

    @Test
    public static void loginPositiveTest() {
        String username = "groot.epam@gmail.com";
        String password = "iamgroot";
        LoginPO loginPO = new LoginPO();
        loginPO.setEmail(username)
                .clickContinue()
                .setPassword(password)
                .clickSelectKeepMeSignIn()
                .clickSignIn();
        if (getTitle().equals("Authentication required")) {
            loginPO.clickContinue();
            do {
                String otp = GmailUtil.getAmazonOTP(username, password);
                loginPO.setOtp(otp)
                        .clickSubmitOtp();
            } while (loginPO.isOtpIncorrect());
        }
        pageLoaded(20);
        HomePageAssertion.assertHomePageOpened();
    }
}
