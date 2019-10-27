package com.epam.element;

import org.openqa.selenium.WebElement;

public class Button extends Element {
    public Button(WebElement webElement) {
        super(webElement);
    }

    public boolean isClickable() {
        return webElement.isDisplayed() && webElement.isEnabled();
    }

    public void click() {
        webElement.click();
    }
}
