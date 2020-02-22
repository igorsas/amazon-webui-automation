package com.epam.widget;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;
import com.epam.po.CartPO;
import com.epam.po.SpecialOffersPO;

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

    @InitializeByXpath(locator = "//*[@id='nav-hamburger-menu']")
    private Button categories;

    public HeaderWidget clickDeliverTo() {
        this.deliverTo.waitFor().clickable();
        this.deliverTo.click();
        return this;
    }

    public SpecialOffersPO clickTodaysDeals() {
        this.todaysDeals.waitFor().clickable();
        this.todaysDeals.click();
        return new SpecialOffersPO();
    }

    public HeaderWidget clickHelp() {
        this.help.waitFor().clickable();
        this.help.click();
        return this;
    }

    public HeaderWidget clickBuyAgain() {
        this.buyAgain.waitFor().clickable();
        this.buyAgain.click();
        return this;
    }

    public HeaderWidget clickOrders() {
        this.orders.waitFor().clickable();
        this.orders.click();
        return this;
    }

    public CartPO clickCart() {
        this.webDriver.navigate().refresh();
        this.cart.waitFor().clickable();
        this.cart.click();
        return new CartPO();
    }

    public ShopByCategoryWidget clickCategories(){
        this.categories.waitFor().clickable();
        this.categories.click();
        return new ShopByCategoryWidget();
    }
}