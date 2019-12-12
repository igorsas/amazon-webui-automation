package com.epam.widget;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;
import com.epam.po.CartPO;
import com.epam.po.SpecialOffersPO;

import static com.epam.waiter.WaitElement.getClickableCondition;
import static com.epam.waiter.WaitElement.waitFor;

//TODO: create pages for each field and change methods return type
public class HeaderWidget extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='nav-global-location-slot']//a")
    private Button deliverTo;

    @InitializeByXpath(locator = "//*[@id='nav-xshop']//a[contains(@href,'international-sales-offers')]")
    private Button todaysDeals;

    @InitializeByXpath(locator = "//*[@id='nav-xshop']//a[contains(@href,'help')]")
    private Button help;

    @InitializeByXpath(locator = "//*[@id='nav-xshop']//a[contains(@href,'buyagain')]")
    private Button buyAgain;

    @InitializeByXpath(locator = "//*[@id='nav-orders']")
    private Button orders;

    @InitializeByXpath(locator = "//*[@id='nav-cart']")
    private Button cart;

    public HeaderWidget clickDeliverTo() {
        waitFor(getClickableCondition(deliverTo));
        this.deliverTo.click();
        return this;
    }

    public SpecialOffersPO clickTodaysDeals() {
        waitFor(getClickableCondition(todaysDeals));
        this.todaysDeals.click();
        return new SpecialOffersPO();
    }

    public HeaderWidget clickHelp() {
        waitFor(getClickableCondition(help));
        this.help.click();
        return this;
    }

    public HeaderWidget clickBuyAgain() {
        waitFor(getClickableCondition(buyAgain));
        this.buyAgain.click();
        return this;
    }

    public HeaderWidget clickOrders() {
        waitFor(getClickableCondition(orders));
        this.orders.click();
        return this;
    }

    public CartPO clickCart() {
        waitFor(getClickableCondition(cart));
        this.cart.click();
        return new CartPO();
    }
}