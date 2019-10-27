package com.epam.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoader {
    public static String getValue(final String keyToFile) {
        String resourceName = "driver.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            properties.load(Objects.requireNonNull(resourceStream));
            return properties.getProperty(keyToFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
