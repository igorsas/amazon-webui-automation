package com.epam.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static com.epam.constant.GeneralConstants.*;
import static com.epam.utils.PropertyLoader.getValue;

public class DriverManager {
    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    static {
        System.setProperty(getValue(DRIVER_PROPERTIES_NAME, DRIVER_NAME_STR), getValue(DRIVER_PROPERTIES_NAME, PATH_STR));
    }

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (Objects.isNull(DRIVER_POOL.get())) {
            initializeDriver();
        }
        return DRIVER_POOL.get();
    }

    private static void initializeDriver() {
        AtomicReference<WebDriver> driver = null;
        if (Boolean.parseBoolean(getValue(DRIVER_PROPERTIES_NAME, HUB_STR))) {
            getRemoteDriver().ifPresent(driver::set);
        } else {
            driver.set(getStandardDriver());
        }
        setTimeouts(driver.get());
        DRIVER_POOL.set(driver.get());
    }

    private static WebDriver getStandardDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(Boolean.parseBoolean(getValue(DRIVER_PROPERTIES_NAME, HEADLESS_MODE_STR)));
        options.addArguments(DISABLE_INFOBARS_STR);
        return new ChromeDriver(options);
    }

    private static Optional<WebDriver> getRemoteDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        try {
            return Optional.of(new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), capabilities));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static void setTimeouts(WebDriver driver) {
        long implicitlyWait = Long.parseLong(getValue(WAIT_TIMERS_PROPERTIES_NAME, IMPLICITRY_WAIT_STR));
        long pageLoadTimeout = Long.parseLong(getValue(WAIT_TIMERS_PROPERTIES_NAME, PAGE_LOAD_TIMEOUT_STR));
        long scriptTimeout = Long.parseLong(getValue(WAIT_TIMERS_PROPERTIES_NAME, SCRIPT_TIMEOUT_STR));

        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS)
                .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS)
                .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void quit() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }
}
