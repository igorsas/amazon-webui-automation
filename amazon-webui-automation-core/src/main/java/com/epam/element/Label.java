package com.epam.element;

import org.openqa.selenium.WebElement;

public class Label extends Element {
    public Label(WebElement webElement) {
        super(webElement);
    }

    public String getText(){
        return webElement.getText();
    }
}
