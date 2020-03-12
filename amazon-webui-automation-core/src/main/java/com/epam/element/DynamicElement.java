package com.epam.element;

import com.epam.annotation.InitializeDynamicByXpath;
import com.epam.driver.DriverManager;
import com.epam.factory.PageFactory;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Objects;

public class DynamicElement extends AbstractElement {

    DynamicElement(WebElement webElement) {
        super(webElement);
    }

    public static void resolve(Object page, String... params) {
        Field[] fields = page.getClass().getFields();
        for (Field field : fields) {
            InitializeDynamicByXpath annotation = field.getAnnotation(InitializeDynamicByXpath.class);
            if (Objects.isNull(annotation)) {
                continue;
            }
            //set params in annotation
        }
        PageFactory.initElements(DriverManager.getDriver(), page);
    }
}
