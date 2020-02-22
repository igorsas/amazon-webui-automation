package com.epam;

import com.epam.driver.DriverManager;
import com.epam.po.LoginPO;
import com.epam.utils.GmailUtil;
import org.testng.annotations.Test;

import static com.epam.waiter.WaitElement.pageLoaded;
import static org.testng.Assert.assertEquals;


public class LoginTest extends AbstractTest {

    public static void assertHomePageOpened() {
        String homePageTitle = "Your Amazon.com";
        String currentPageTitle = DriverManager.getDriver().getTitle();
        assertEquals(homePageTitle, currentPageTitle, String.format("Home page was not opened. Expected title %s1 But found %s2", homePageTitle, currentPageTitle));
    }

    @Test
    public void loginPositiveTest() {
        String username = "groot.epam@gmail.com";
        String password = "iamgroot";
        LoginPO loginPO = new LoginPO();
        loginPO.setEmail(username)
                .clickContinue()
                .setPassword(password)
                .clickSelectKeepMeSignIn()
                .clickSignIn();
        if (DriverManager.getDriver().getTitle().equals("Authentication required")) {
            loginPO.clickContinue();
            do {
                String otp = GmailUtil.getAmazonOTP(username, password);
                loginPO.setOtp(otp)
                        .clickSubmitOtp();
            } while (loginPO.isOtpIncorrect());
        }
        pageLoaded(20);
        assertHomePageOpened();
    }
}
