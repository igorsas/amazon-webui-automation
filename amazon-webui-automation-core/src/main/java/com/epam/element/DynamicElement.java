package com.epam.element;

import com.epam.driver.DriverManager;
import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicElement extends AbstractElement {


    public DynamicElement(String xpath, String... params) {
        this(new WebDriverWait(DriverManager.getDriver(), 20)
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath(StringFormatter.format(xpath, params).getValue()))));
    }

    DynamicElement(WebElement webElement) {
        super(webElement);
    }
}
