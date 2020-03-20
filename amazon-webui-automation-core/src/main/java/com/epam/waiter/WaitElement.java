package com.epam.waiter;

import com.epam.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class WaitElement {
    private WebElement element;
    private final int DEFAULT_TIME_OUT = 30;
    private WebDriverWait webDriverWait;
    private boolean quietly;

    public WaitElement(WebElement webElement) { this.element = webElement; }

    public boolean clickable() {
        webDriverWait = new WebDriverWait(DriverManager.getDriver(), DEFAULT_TIME_OUT);
        try {
            webDriverWait.until(driver -> {
                WebElement visibleElement = ExpectedConditions.visibilityOf(element).apply(driver);
                return visibleElement != null && visibleElement.isEnabled() && visibleElement.isDisplayed() ? visibleElement : null;
            });
            return true;
        } catch (WebDriverException e) {
            return ifException(e);
        }
    }

    public boolean visibility() {
        webDriverWait = new WebDriverWait(DriverManager.getDriver(), DEFAULT_TIME_OUT);
        try {
            webDriverWait.until(driver -> ExpectedConditions.visibilityOf(element).apply(driver));
            return true;
        } catch (WebDriverException e) {
            return ifException(e);
        }
    }

    public WaitElement quietly(){
        quietly = true;
        return this;
    }

    private boolean ifException(WebDriverException e) {
        if(quietly){
            return false;
        }else {
            throw e;
        }
    }

    public static boolean pageLoaded(Integer seconds) {
        WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), seconds);
        try {
            webDriverWait.until(webDriver -> ((JavascriptExecutor) Objects.requireNonNull(webDriver)).executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (WebDriverException ignored) {
            return false;
        }
    }
}
