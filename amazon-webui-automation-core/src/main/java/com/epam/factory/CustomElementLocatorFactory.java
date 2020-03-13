package com.epam.factory;

import org.openqa.selenium.SearchContext;

import java.lang.reflect.Field;

public final class CustomElementLocatorFactory {
    private final SearchContext searchContext;

    public CustomElementLocatorFactory(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    public CustomElementLocator createLocator(Field field) {
        return new CustomElementLocator(searchContext, field);
    }

    public CustomElementLocator createLocator(Field field, String xpathLocator) {
        return new CustomElementLocator(searchContext, field, xpathLocator);
    }
}
