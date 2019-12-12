package com.epam;

import com.epam.assertion.HomePageAssertion;
import com.epam.po.LoginPO;
import com.epam.utils.GmailUtil;
import com.epam.widget.HeaderWidget;
import org.testng.annotations.Test;

import static com.epam.assertion.CartAssertion.assertCartIsEmpty;
import static com.epam.utils.DriverUtil.getTitle;
import static com.epam.waiter.WaitElement.getPageLoadedCondition;
import static com.epam.waiter.WaitElement.waitFor;


public class LoginTest extends AbstractTest {

    @Test
    public static void loginPositiveTest() {
        String username = "groot.epam@gmail.com";
        String password = "iamgroot";
        loginTest(username, password);
    }

    @Test
    public static void openCartTest() {
        String username = "groot.epam@gmail.com";
        String password = "iamgroot";
        loginTest(username, password);

        String actualHeader = new HeaderWidget().clickCart().getHeader();
        assertCartIsEmpty(actualHeader);
    }

    private static void loginTest(String username, String password) {
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
            }while (loginPO.isOtpIncorrect());
        }
        waitFor(getPageLoadedCondition());
        HomePageAssertion.assertHomePageOpened();
    }
}
