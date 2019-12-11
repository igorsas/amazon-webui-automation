package com.epam.driver;

import com.epam.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.epam.constant.GeneralConstants.DRIVER_PROPERTIES_NAME;

public class DriverManager {
    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();
    private static final String NAME = Objects.requireNonNull(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME,"name"));
    private static final String PATH = Objects.requireNonNull(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME,"path"));
    private static final boolean HEADLESS_MODE = Boolean.parseBoolean(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "headless"));

    static {
        System.setProperty(NAME, PATH);
    }

    private DriverManager() { }

    public static WebDriver getDriver()
    {
        if(Objects.isNull(DRIVER_POOL.get())) {
            initializeDriver();
        }
        return DRIVER_POOL.get();
    }

    private static void initializeDriver(){
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
}
