package com.epam.element;

import com.epam.waiter.WaitElement;
import org.openqa.selenium.WebElement;

public abstract class AbstractElement {
    protected WebElement webElement;

    AbstractElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public WaitElement waitFor(){
        return new WaitElement(webElement);
    }
}
