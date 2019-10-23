package com.epam.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoader {
    public static String getValue(final String keyToFile) {
        Properties properties = new Properties();
        try (InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream("driver.properties")) {
            properties.load(Objects.requireNonNull(input));
            return properties.getProperty(keyToFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
