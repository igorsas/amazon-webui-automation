package com.epam;

import com.epam.driver.DriverManager;
import com.epam.po.LoginPO;
import com.epam.utils.GmailUtil;
import com.epam.utils.PropertyLoader;
import com.epam.utils.Url;
import com.google.inject.Guice;
import com.google.inject.Inject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.constant.GeneralConstants.LOGIN_URN_STR;
import static com.epam.constant.GeneralConstants.URL_PROPERTIES_NAME;
import static com.epam.waiter.WaitElement.pageLoaded;
import static org.testng.Assert.assertEquals;


public class LoginTest extends AbstractTest {
    @Inject
    private LoginPO loginPO;

    @Inject
    private Url url;

    private static void assertHomePageOpened() {
        String homePageTitle = "Your Amazon.com";
        String currentPageTitle = DriverManager.getDriver().getTitle();
        assertEquals(homePageTitle, currentPageTitle, String.format("Home page was not opened. Expected title %s1 But found %s2", homePageTitle, currentPageTitle));
    }

    @Test
    public void loginPositiveTest() {
        String username = "groot.epam@gmail.com";
        String password = "iamgroot";
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

    @BeforeMethod
    public void initializeFields() {
        Guice.createInjector().injectMembers(this);
    }

    @BeforeMethod
    public void setStartedPage() {
        url.setUrn(PropertyLoader.getValue(URL_PROPERTIES_NAME, LOGIN_URN_STR));
        DriverManager.getDriver().navigate().to(url.getUrl());
    }
}
