package com.epam.po;

import com.epam.AbstractPO;
import com.epam.annotation.InitializeByXpath;
import com.epam.driver.DriverManager;
import com.epam.element.Label;
import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPO extends AbstractPO {
    @InitializeByXpath(locator = "//*[@id='sc-active-cart']//h1[@class='sc-empty-cart-header']")
    private Label header;

    public String getHeader(){
        return header.getText();
    }

    public boolean hasGood(String expectedGoodTitle) {
        By xpath = By.xpath(StringFormatter.format("//*[contains(text(),'%s')]", expectedGoodTitle).getValue());
        Label label = new Label(new WebDriverWait(DriverManager.getDriver(), 20).until(ExpectedConditions.visibilityOfElementLocated(xpath)));
        return label.waitFor().visibility();
    }
}
