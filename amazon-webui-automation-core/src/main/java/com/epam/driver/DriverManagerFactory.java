package com.epam.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public enum DriverManagerFactory {
    CHROME {
        public WebDriver create() {
            ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
            return new ChromeDriver();
        }
    },
    IE {
        public WebDriver create() {
            EdgeDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
            return new InternetExplorerDriver();
        }
    },
    FIREFOX {
        public WebDriver create() {
            EdgeDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
            return new FirefoxDriver();
        }
    },
    OPERA {
        public WebDriver create() {
            EdgeDriverManager.getInstance(DriverManagerType.OPERA).setup();
            return new OperaDriver();
        }
    };

    public WebDriver create() {
        return null;
    }
}