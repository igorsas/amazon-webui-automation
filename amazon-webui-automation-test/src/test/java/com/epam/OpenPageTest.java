package com.epam;

import com.epam.driver.DriverManager;
import com.epam.po.CartPO;
import com.epam.po.GoodPO;
import com.epam.widget.HeaderWidget;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.assertion.CartAssertion.assertCartIsEmpty;
import static org.testng.Assert.assertTrue;

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
        GoodPO goodPO = headerWidget.clickTodaysDeals().openFirstGood();
        String expectedGoodTitle = goodPO.getProductTitle();
        goodPO.addToCart();
        CartPO cartPO = headerWidget.clickCart();
        assertTrue(cartPO.hasGood(expectedGoodTitle), "Good: " + expectedGoodTitle + " is not added to cart");
    }

    @Test
    public static void openCellPhonesPage(){
        HeaderWidget headerWidget = new HeaderWidget();
        headerWidget.clickCategories()
                .openMenuByName("Electronics")
                .openMenuByName("Cell Phones & Accessories");
    }

    @Test
    public static void openNotExistingPage(){
        HeaderWidget headerWidget = new HeaderWidget();
        headerWidget.clickCategories()
                .openMenuByName("Anime")
                .openMenuByName("For cool guys");
    }
}
