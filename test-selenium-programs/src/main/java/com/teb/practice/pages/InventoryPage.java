package com.teb.practice.pages;

import static org.openqa.selenium.By.id;

import com.teb.practice.pages.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = id("inventory_container");

    public InventoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isLoaded() {

        return webDriver.findElement(inventoryContainer).isDisplayed();
    }
}
