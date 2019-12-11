package com.epam.assertion;

import com.epam.driver.DriverManager;

import static org.testng.Assert.assertEquals;

public class HomePageAssertion {
    public static void assertHomePageOpened(){
        String homePageTitle = "Your Amazon.com";
        String currentPageTitle = DriverManager.getDriver().getTitle();
        assertEquals(homePageTitle, currentPageTitle, String.format("Home page was not opened. Expected title %s1 But found %s2", homePageTitle, currentPageTitle));
    }
}
