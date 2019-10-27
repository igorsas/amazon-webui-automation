package com.epam.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;

public class PageFactory {
    public static void initElements(WebDriver driver, Object page) {
        initElements(new CustomFieldDecorator(driver), page);
    }

    public static void initElements(FieldDecorator decorator, Object page) {
        Class<?> proxyIn = page.getClass();
        while (proxyIn != Object.class) {
            proxyFields(decorator, page, proxyIn);
            proxyIn = proxyIn.getSuperclass();
        }
    }

    private static void proxyFields(FieldDecorator decorator, Object page, Class<?> proxyIn) {
        Field[] fields = proxyIn.getDeclaredFields();
        for (Field field : fields) {
            Object value = decorator.decorate(page.getClass().getClassLoader(), field);
            if (value != null) {
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
