package com.epam.po;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;

public class SpecialOffersPO extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='widgetContent']//button[contains(text(),'Add to Cart')]/ancestor::div[contains(@class, 'dealDetailContainer')]/preceding-sibling::a[@id='dealImage']")
    private Button firstProduct;

    public ProductPO openFirstGood(){
        this.firstProduct.waitFor().clickable();
        this.firstProduct.click();
        return new ProductPO();
    }
}