package com.teb.practice.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> WEB_DRIVER = new ThreadLocal<>();

    public static WebDriver getWebDriver() {

        return WEB_DRIVER.get();
    }

    public static void setWebDriver(WebDriver webDriver) {

        WEB_DRIVER.set(webDriver);
    }

    public static void unload() {

        WEB_DRIVER.remove();
    }
}
