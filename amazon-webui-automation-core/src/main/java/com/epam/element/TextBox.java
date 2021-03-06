package com.epam.element;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TextBox extends Element {
    public TextBox(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(CharSequence... var1) {
        webElement.sendKeys(var1);
    }

    public void clearAndType(String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void typeAndSubmit(String text) {
        webElement.sendKeys(text);
        webElement.sendKeys(Keys.ENTER);
    }
}
