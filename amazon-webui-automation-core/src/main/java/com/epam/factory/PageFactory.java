package com.epam.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;
import java.util.Objects;

public class PageFactory {
    public static void initElements(WebDriver driver, Object page) {
        initElements(new CustomFieldDecorator(driver), page);
    }

    public static void initElements(CustomFieldDecorator decorator, Object page) {
        Class<?> proxyIn = page.getClass();
        proxyFields(decorator, page, proxyIn);
    }

    private static void proxyFields(CustomFieldDecorator decorator, Object page, Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();
        for (Field field : fields) {
            Object value = decorator.decorate(page.getClass().getClassLoader(), field);
            if (Objects.nonNull(value)) {
                try {
                    field.setAccessible(true);
                    field.set(page, value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
