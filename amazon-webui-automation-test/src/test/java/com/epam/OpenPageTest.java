package com.epam;

import com.epam.driver.DriverManager;
import com.epam.po.CartPO;
import com.epam.po.ProductPO;
import com.epam.utils.Url;
import com.epam.widget.HeaderWidget;
import com.google.inject.Guice;
import com.google.inject.Inject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.constant.GeneralConstants.CART_IS_EMPTY;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OpenPageTest extends AbstractTest {
    @Inject
    private HeaderWidget headerWidget;

    @Inject
    private Url url;

    private static void assertCartIsEmpty(String header) {
        assertEquals(CART_IS_EMPTY, header, String.format("Cart is not empty. Expected header %s1 But found %s2", CART_IS_EMPTY, header));
    }

    @Test
    public void openCartTest() {
        String actualHeader = headerWidget.clickCart().getHeader();
        assertCartIsEmpty(actualHeader);
    }

    @Test
    public void addGoodToCartTest() {
        ProductPO productPO = headerWidget.clickTodaysDeals().openFirstGood();
        String expectedProductTitle = productPO.getProductTitle();
        productPO.addToCart();
        CartPO cartPO = headerWidget.clickCart();
        assertTrue(cartPO.hasProduct(expectedProductTitle), "Product: " + expectedProductTitle + " is not added to cart");
    }

    @Test
    public void openCellPhonesPage() {
        headerWidget.clickCategories()
                .openMenuByName("Electronics")
                .openMenuByName("Cell Phones & Accessories");
    }

    @Test
    public void openNotExistingPage() {
        headerWidget.clickCategories()
                .openMenuByName("Anime")
                .openMenuByName("For cool guys");
    }

    @BeforeMethod
    public void initializeFields() {
        Guice.createInjector().injectMembers(this);
    }

    @BeforeMethod
    public void setStartedPage() {
        DriverManager.getDriver().navigate().to(url.getUrl());
    }
}
