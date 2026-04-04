package com.teb.practice.pages.base;

import static com.teb.practice.util.WaitUtil.waitForElementVisible;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected void type(By locator, String text) {

        waitForElementVisible(webDriver, locator, 5).sendKeys(text);
    }
}
