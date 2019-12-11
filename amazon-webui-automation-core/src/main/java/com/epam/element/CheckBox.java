package com.epam.element;

import org.openqa.selenium.WebElement;

public class CheckBox extends Element {
    public CheckBox(WebElement webElement) { super(webElement); }

    public void selectCheckBox(){
        if(!webElement.isSelected()){
            webElement.click();
        }
    }

    public void deselectCheckBox(){
        if(webElement.isSelected()){
            webElement.click();
        }
    }
}
