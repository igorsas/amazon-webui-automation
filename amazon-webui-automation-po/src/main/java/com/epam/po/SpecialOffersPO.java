package com.epam.po;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;

import static com.epam.waiter.WaitElement.getClickableCondition;
import static com.epam.waiter.WaitElement.waitFor;

public class SpecialOffersPO extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='widgetContent']//button[text()='Add to Cart']::preceding //*[@id='dealImage']")
    private Button firstGood;


//    <button id="100 d77b6b84-announce" aria-label="Add to Cart Save up to 30% on Bluetooth Vehicle Scanner" class="a-button-text a-text-center" type="button">
//    Add to Cart
//            </button>
//            */

    //
    public SpecialOffersPO openFirstGood(){
        waitFor(getClickableCondition(firstGood));
        this.firstGood.click();
        return this;
    }
}
//*[@id="dealImage"]

//div[contains(@class, 'dealDetailContainer')]/preceding-sibling::div
////div/a/ancestor::div/preceding-sibling::div