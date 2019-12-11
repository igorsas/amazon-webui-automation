package com.epam.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoader {
    public static String getValue(final String resourceName, final String keyToFile) {
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

    public static Properties getProperties(final String resourceName){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            properties.load(Objects.requireNonNull(resourceStream));
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
