package com.epam;

import com.epam.assertion.HomePageAssertion;
import com.epam.po.LoginPO;
import com.epam.utils.GmailUtil;
import org.testng.annotations.Test;

import static com.epam.utils.DriverUtil.getTitle;
import static com.epam.waiter.WaitElement.getPageLoadedCondition;
import static com.epam.waiter.WaitElement.waitFor;


public class LoginTest extends AbstractTest {

    @Test
    public static void test() {
        String username = "groot.epam@gmail.com";
        String password = "iamgroot";
        LoginPO loginPO = new LoginPO();
        loginPO.setEmail(username)
                .continuE()
                .setPassword(password)
                .selectKeepMeSignIn()
                .signIn();
        if (getTitle().equals("Authentication required")) {
            String otp = GmailUtil.getAmazonOTP(username, password);
            loginPO.continuE()
                    .setOtp(otp)
                    .submitOtp();
        }
        waitFor(getPageLoadedCondition());
        HomePageAssertion.assertHomePageOpened();
    }
}
