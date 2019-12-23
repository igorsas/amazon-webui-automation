package com.epam.widget;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Button;
import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.By;

public class ShopByCategoryWidget extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='hmenu-content']//a[contains(@href,'fullstore')]")
    private Button fullStore;

    public ShopByCategoryWidget openMenuByName(String menuName) {
        By xpath = By.xpath(StringFormatter.format("//*[@id='hmenu-content']//*[contains(text(), $s)]/..", menuName).getValue());
        Button menu = new Button(webDriver.findElement(xpath));
        if(menu.waitFor().clickable())
            menu.click();
        return this;
    }
}