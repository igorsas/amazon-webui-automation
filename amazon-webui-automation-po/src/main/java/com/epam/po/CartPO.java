package com.epam.po;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.element.Label;
import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPO extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='sc-active-cart']//h1[@class='sc-empty-cart-header']")
    private Label header;

    public String getHeader(){
        return header.getText();
    }

    public boolean hasGood(String expectedGoodTitle) {
        By xpath = By.xpath(StringFormatter.format("//*[contains(text(),'%s')]", expectedGoodTitle).getValue());
        List<WebElement> titles = webDriver.findElements(xpath);
        return !titles.isEmpty();
    }
}
