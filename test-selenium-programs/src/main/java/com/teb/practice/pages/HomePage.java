package com.teb.practice.pages;

import static com.teb.practice.util.WaitUtil.waitForElementClickable;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

import static java.util.Objects.requireNonNull;

import com.teb.practice.pages.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    private final By rejectAllButton = cssSelector("button[aria-label*='Reject']");
    private final By shortsTab =
            xpath("//*[@id=\"tabsContent\"]/yt-tab-group-shape/div[1]/yt-tab-shape[3]/div[1]");

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void rejectConsent() {

        try {
            WebElement rejectButton = waitForElementClickable(webDriver, rejectAllButton, 10);
            rejectButton.click();
        } catch (Exception e) {
            // Ignored
        }
    }

    public void clickShortsTab() {

        WebElement shorts = waitForElementClickable(webDriver, shortsTab, 10);
        shorts.click();
    }

    public boolean isShortsTabActive() {

        return requireNonNull(webDriver.getCurrentUrl()).contains("shorts");
    }
}
