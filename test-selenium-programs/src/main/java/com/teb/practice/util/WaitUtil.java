package com.teb.practice.util;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    public static WebElement waitForElementVisible(WebDriver driver, By locator, int seconds) {

        return new WebDriverWait(driver, ofSeconds(seconds))
                .until(visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator, int seconds) {

        return new WebDriverWait(driver, ofSeconds(seconds)).until(elementToBeClickable(locator));
    }

    public static void waitForTitleContains(WebDriver driver, String text, int seconds) {

        new WebDriverWait(driver, ofSeconds(seconds)).until(titleContains(text));
    }
}
