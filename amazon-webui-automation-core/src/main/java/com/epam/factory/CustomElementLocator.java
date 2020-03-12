package com.epam.factory;

import com.epam.annotation.InitializeByXpath;
import com.epam.annotation.InitializeDynamicByXpath;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

public class CustomElementLocator {
    private final SearchContext searchContext;
    private final By by;

    public CustomElementLocator(SearchContext searchContext, Field field) {
        this(searchContext, getByForField(field));
    }

    public CustomElementLocator(SearchContext searchContext, By by) {
        this.searchContext = searchContext;
        this.by = by;
    }

    private static By getByForField(Field field) {
        InitializeByXpath annotation = field.getAnnotation(InitializeByXpath.class);
        InitializeDynamicByXpath dynamicXpathAnnotation = field.getAnnotation(InitializeDynamicByXpath.class);
        String xpath;

        if (Objects.isNull(annotation)) {
            if(Objects.isNull(dynamicXpathAnnotation)) {
                return null;
            } else {
                xpath = dynamicXpathAnnotation.locator();
            }
        }else {
            xpath = annotation.locator();
        }

        return By.xpath(xpath);
    }

    public WebElement findElement() {
        return searchContext.findElement(by);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " '" + by + "'";
    }
}
