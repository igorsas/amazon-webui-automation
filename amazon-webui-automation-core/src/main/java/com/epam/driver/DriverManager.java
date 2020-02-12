package com.epam.driver;

import com.epam.utils.PropertyLoader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.epam.constant.GeneralConstants.DRIVER_PROPERTIES_NAME;
import static com.epam.constant.GeneralConstants.WAIT_TIMERS_PROPERTIES_NAME;

public class DriverManager {
    private static final String NAME = Objects.requireNonNull(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "name"));
    private static final String PATH = Objects.requireNonNull(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "path"));
    private static final boolean HEADLESS_MODE = Boolean.parseBoolean(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "headless"));
    private static final boolean RUN_ON_HUB = Boolean.parseBoolean(PropertyLoader.getValue(DRIVER_PROPERTIES_NAME, "hub"));
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

        WebDriver driver = null;
        if (RUN_ON_HUB) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            try {
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(HEADLESS_MODE);
            options.addArguments("disable-infobars");
//        uncomment when need addblock
//        options.addExtensions(new File("src/main/resources/adBlockExtension.crx"));
            driver = new ChromeDriver(options);
        }
        long implicitlyWait = Long.parseLong(Objects.requireNonNull(PropertyLoader.getValue(WAIT_TIMERS_PROPERTIES_NAME, "implicitlyWait")));
        long pageLoadTimeout = Long.parseLong(Objects.requireNonNull(PropertyLoader.getValue(WAIT_TIMERS_PROPERTIES_NAME, "pageLoadTimeout")));
        long scriptTimeout = Long.parseLong(Objects.requireNonNull(PropertyLoader.getValue(WAIT_TIMERS_PROPERTIES_NAME, "scriptTimeout")));

        DRIVER_POOL.set(driver);
        DRIVER_POOL.get().manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().timeouts().setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().window().maximize();
    }

    public static void quit() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }

    public static void clear() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DRIVER_POOL.get();
        Long hostnameLength = (Long) jsExecutor.executeScript("return window.location.hostname.length;");
        if (hostnameLength > 0) {
            jsExecutor.executeScript("window.sessionStorage.clear();");
            jsExecutor.executeScript("window.localStorage.clear();");
        }
        DRIVER_POOL.get().manage().deleteAllCookies();
    }
}
