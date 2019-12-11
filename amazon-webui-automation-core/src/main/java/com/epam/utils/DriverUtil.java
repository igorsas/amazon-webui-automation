package com.epam.utils;

import com.epam.driver.DriverManager;

public class DriverUtil {
    public static String getTitle(){
        return DriverManager.getDriver().getTitle();
    }
}
