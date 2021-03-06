package com.epam.factory;

import com.epam.element.AbstractElement;
import org.openqa.selenium.WebElement;

public class WrapperFactory {

    /**
     * Create instance,
     * implemented IElement,
     * invoke constructor with arguments WebElement
     */
    public static AbstractElement createInstance(Class<AbstractElement> clazz,
                                                 WebElement element) {
        try {
            AbstractElement a = clazz.getConstructor(WebElement.class).
                    newInstance(element);
            return a;
        } catch (Exception e) {
            throw new AssertionError(
                    "WebElement can't be represented as " + clazz
            );
        }
    }
}