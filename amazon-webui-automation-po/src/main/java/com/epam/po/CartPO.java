package com.epam.po;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.annotation.InitializeDynamicByXpath;
import com.epam.element.DynamicElement;
import com.epam.element.Label;

public class CartPO extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='sc-active-cart']//h1[@class='sc-empty-cart-header']")
    private Label header;

    @InitializeDynamicByXpath(locator = "//*[contains(text(),'%s')]")
    private DynamicElement product;


    public String getHeader(){
        return header.getText();
    }

    public boolean hasProduct(String expectedProductTitle) {
        DynamicElement.resolve(this, expectedProductTitle);
        return product.waitFor().quietly().visibility();
    }
}
