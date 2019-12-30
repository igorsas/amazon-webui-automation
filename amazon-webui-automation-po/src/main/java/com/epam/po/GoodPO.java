package com.epam.po;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;
import com.epam.element.Label;

public class GoodPO extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='add-to-cart-button']")
    private Button addToCart;

    @InitializeByXpath(locator = "//*[@id=\"newAccordionRow\"]//*[contains(text(), 'Regular Price')]/ancestor::a")
    private Button switchToRegularPrice;

    @InitializeByXpath(locator = "//*[@id='buy-now-button']")
    private Button buyNow;

    @InitializeByXpath(locator = "//*[@id='productTitle']")
    private Label productTitle;

    public GoodPO addToCart() {
        if(this.switchToRegularPrice.waitFor().clickable()){
            switchToRegularPrice.click();
        }
        this.addToCart.waitFor().clickable();
        this.addToCart.click();
        return this;
    }


    public String getProductTitle() {
        return this.productTitle.getText();
    }

    public GoodPO buyNow() {
        this.buyNow.waitFor().clickable();
        this.buyNow.click();
        return this;
    }
}
