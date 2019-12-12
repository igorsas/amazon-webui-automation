package com.epam;

import com.epam.driver.DriverManager;
import com.epam.widget.HeaderWidget;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.assertion.CartAssertion.assertCartIsEmpty;

public class OpenPageTest extends AbstractTest {
    @BeforeMethod
    @Override
    public void setStartedPage() {
        DriverManager.getDriver().navigate().to("https://www.amazon.com/");
    }

    @Test
    public static void openCartTest() {
        String actualHeader = new HeaderWidget().clickCart().getHeader();
        assertCartIsEmpty(actualHeader);
    }

    @Test
    public static void addGoodToCartTest() {
        HeaderWidget headerWidget = new HeaderWidget();
        headerWidget.clickTodaysDeals().openFirstGood();
//        headerWidget.clickCart()

    }
}
