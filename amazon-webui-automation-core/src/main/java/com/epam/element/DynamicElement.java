package com.epam.element;

import com.epam.annotation.InitializeDynamicByXpath;
import com.epam.driver.DriverManager;
import com.epam.factory.PageFactory;
import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Objects;

public class DynamicElement extends AbstractElement {

    public DynamicElement(WebElement webElement) {
        super(webElement);
    }

    public static void resolve(Object page, String... params) {
        Field[] fields = page.getClass().getDeclaredFields();
        for (Field field : fields) {
            InitializeDynamicByXpath annotation = field.getAnnotation(InitializeDynamicByXpath.class);
            if (Objects.isNull(annotation)) {
                continue;
            }
            String xpath = StringFormatter.format(annotation.locator(), params).getValue();
            PageFactory.proxyDynamicField(DriverManager.getDriver(), page, field, xpath);
        }
    }
}
