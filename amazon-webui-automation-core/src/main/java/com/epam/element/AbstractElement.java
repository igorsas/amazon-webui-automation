package com.epam.element;

import org.openqa.selenium.WebElement;

public abstract class AbstractElement {
    protected WebElement webElement;

    AbstractElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElement getWebElement() {
        return webElement;
    }
}
