package com.epam.widget;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;
import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.By;

import static com.epam.waiter.WaitElement.getClickableCondition;
import static com.epam.waiter.WaitElement.waitFor;

public class ShopByCategoryWidget extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='hmenu-content']//a[contains(@href,'fullstore')]")
    private Button fullStore;

    public void openMenuByName(String menuName) {
        By xpath = By.xpath(StringFormatter.format("//*[@id='hmenu-content']//*[contains(text(), $s)]/..", menuName).getValue());
        Button menu = new Button(webDriver.findElement(xpath));
        waitFor(getClickableCondition(menu));
    }
}