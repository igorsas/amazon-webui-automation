package com.epam.widget;

import com.epam.AbstractPO;
import com.epam.element.Button;
import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopByCategoryWidget extends AbstractPO {

    public ShopByCategoryWidget openMenuByName(String menuName) {
        By xpath = By.xpath(StringFormatter.format("//*[@id='hmenu-content']//*[contains(text(), '%s')]/..", menuName).getValue());

        List<WebElement> menuElement = webDriver.findElements(xpath);

        if (!menuElement.isEmpty()) {
            Button menu = new Button(webDriver.findElement(xpath));
            if (menu.waitFor().clickable())
                menu.click();
        }
        return this;
    }
}