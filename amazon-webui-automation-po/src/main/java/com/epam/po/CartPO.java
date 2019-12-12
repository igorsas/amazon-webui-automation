package com.epam.po;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Label;

public class CartPO extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='sc-active-cart']//h1[@class='sc-empty-cart-header']")
    private Label header;

    public String getHeader(){
        return header.getText();
    }
}
