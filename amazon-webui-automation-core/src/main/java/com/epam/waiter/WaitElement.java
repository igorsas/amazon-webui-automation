package com.epam.waiter;

import com.epam.driver.DriverManager;
import com.epam.element.AbstractElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitElement {
    private static final int DEFAULT_TIME_OUT = 10;
    private static WebDriverWait webDriverWait;

    public static void waitFor(final ExpectedCondition<WebElement> condition, int timeOutInSeconds) {
        webDriverWait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
        try {
            webDriverWait.until(condition);
        } catch (WebDriverException ignored) {
        }
    }

    public static ExpectedCondition<WebElement> getClickableCondition(AbstractElement element) {
        return driver -> {
            WebElement visibleElement = ExpectedConditions.visibilityOf(element.getWebElement()).apply(driver);

            try {
                return visibleElement != null && visibleElement.isEnabled() && visibleElement.isDisplayed() ? visibleElement : null;
            } catch (StaleElementReferenceException arg3) {
                return null;
            }
        };
    }
}
