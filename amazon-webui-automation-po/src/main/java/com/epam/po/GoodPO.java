package com.epam.po;

import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;
import com.epam.element.Label;

import static com.epam.waiter.WaitElement.getClickableCondition;
import static com.epam.waiter.WaitElement.waitFor;

public class GoodPO {
    @InitializeByXpath(locator = "//*[@id='add-to-cart-button']")
    private Button addToCart;

    @InitializeByXpath(locator = "//*[@id='buy-now-button']")
    private Button buyNow;

    @InitializeByXpath(locator = "//*[@id='productTitle']")
    private Label productTitle;

    public GoodPO addToCart(){
        waitFor(getClickableCondition(addToCart));
        this.addToCart.click();
        return this;
    }

    public String getProductTitle(){
        return productTitle.getText();
    }

    public GoodPO buyNow(){
        waitFor(getClickableCondition(buyNow));
        this.buyNow.click();
        return this;
    }
}
