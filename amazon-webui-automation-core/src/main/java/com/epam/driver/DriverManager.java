package com.epam.driver;

import com.epam.utils.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.epam.constant.GeneralConstants.DRIVER_PROPERTIES_NAME;

public class DriverManager {
    private static final String NAME = Objects.requireNonNull(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "name"));
    private static final String PATH = Objects.requireNonNull(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "path"));
    private static final boolean HEADLESS_MODE = Boolean.parseBoolean(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "headless"));
    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    static {
        System.setProperty(NAME, PATH);
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
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(HEADLESS_MODE);
        options.addArguments("disable-infobars");
//        uncomment when need addblock
//        options.addExtensions(new File("src/main/resources/adBlockExtension.crx"));
        ChromeDriver driver = new ChromeDriver(options);
        DRIVER_POOL.set(driver);
        DRIVER_POOL.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().window().maximize();
    }

    public static void quit() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }

    public static void clearCache() {
        DRIVER_POOL.get().manage().deleteAllCookies();
        //doesn't work
        DRIVER_POOL.get().close();
        DRIVER_POOL.get().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
        ArrayList<String> tabs = new ArrayList<String> (DRIVER_POOL.get().getWindowHandles());
        DRIVER_POOL.get().switchTo().window(tabs.get(1));
    }
}
