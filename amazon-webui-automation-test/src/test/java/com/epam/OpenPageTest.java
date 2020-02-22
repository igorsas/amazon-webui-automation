package com.epam;

import com.epam.driver.DriverManager;
import com.epam.po.CartPO;
import com.epam.po.ProductPO;
import com.epam.widget.HeaderWidget;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.constant.GeneralConstants.CART_IS_EMPTY;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OpenPageTest extends AbstractTest {
    @Test
    public void openCartTest() {
        String actualHeader = new HeaderWidget().clickCart().getHeader();
        assertCartIsEmpty(actualHeader);
    }

    @Test
    public void addGoodToCartTest() {
        HeaderWidget headerWidget = new HeaderWidget();
        ProductPO productPO = headerWidget.clickTodaysDeals().openFirstGood();
        String expectedGoodTitle = productPO.getProductTitle();
        productPO.addToCart();
        CartPO cartPO = headerWidget.clickCart();
        assertTrue(cartPO.hasGood(expectedGoodTitle), "Product: " + expectedGoodTitle + " is not added to cart");
    }

    @Test
    public void openCellPhonesPage() {
        HeaderWidget headerWidget = new HeaderWidget();
        headerWidget.clickCategories()
                .openMenuByName("Electronics")
                .openMenuByName("Cell Phones & Accessories");
    }

    @Test
    public void openNotExistingPage() {
        HeaderWidget headerWidget = new HeaderWidget();
        headerWidget.clickCategories()
                .openMenuByName("Anime")
                .openMenuByName("For cool guys");
    }

    @BeforeMethod
    @Override
    public void setStartedPage() {
        DriverManager.getDriver().navigate().to("https://www.amazon.com/");
    }

    public static void assertCartIsEmpty(String header){
        assertEquals(CART_IS_EMPTY, header, String.format("Cart is not empty. Expected header %s1 But found %s2", CART_IS_EMPTY, header));
    }
}
