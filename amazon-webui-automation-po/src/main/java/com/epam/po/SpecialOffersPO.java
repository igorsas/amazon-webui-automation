package com.epam.po;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;

import static com.epam.waiter.WaitElement.getClickableCondition;
import static com.epam.waiter.WaitElement.waitFor;

public class SpecialOffersPO extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='widgetContent']//button[contains(text(),'Add to Cart')]/ancestor::div[contains(@class, 'dealDetailContainer')]/preceding-sibling::a[@id='dealImage']")
    private Button firstGood;

    public GoodPO openFirstGood(){
        waitFor(getClickableCondition(firstGood));
        this.firstGood.click();
        return new GoodPO();
    }
}