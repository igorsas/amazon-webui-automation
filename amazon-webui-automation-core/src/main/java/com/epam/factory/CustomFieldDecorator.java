package com.epam.factory;

import com.epam.element.AbstractElement;
import com.epam.element.DynamicElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;

public class CustomFieldDecorator {

    private CustomElementLocatorFactory factory;

    public CustomFieldDecorator(SearchContext searchContext) {
        factory = new CustomElementLocatorFactory(searchContext);
    }

    public Object decorate(ClassLoader loader, Field field) {
        Class<AbstractElement> decoratableClass = decoratableClass(field);
        // if class of field is decorated
        if (!Objects.isNull(decoratableClass) && !DynamicElement.class.isAssignableFrom(field.getType())) {
            CustomElementLocator locator = factory.createLocator(field);
            return createElement(loader, locator, decoratableClass);
        }
        return null;
    }

    public Object decorateDynamicField(ClassLoader loader, Field field, String xpathLocator) {
        Class<AbstractElement> decoratableClass = decoratableClass(field);
        // if class of field is decorated
        if (!Objects.isNull(decoratableClass)) {
            CustomElementLocator locator = factory.createLocator(field, xpathLocator);
            return createElement(loader, locator, decoratableClass);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Class<AbstractElement> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (AbstractElement.class.isAssignableFrom(clazz)) {
            return (Class<AbstractElement>) clazz;
        } else {
            return null;
        }
    }

    private AbstractElement createElement(ClassLoader loader, CustomElementLocator locator,
                                          Class<AbstractElement> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }


    protected WebElement proxyForLocator(ClassLoader loader, CustomElementLocator locator) {
        InvocationHandler handler = new LocatingCustomElementHandler(locator);

        WebElement proxy;
        proxy = (WebElement) Proxy.newProxyInstance(
                loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
        return proxy;
    }
}