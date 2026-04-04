package com.teb.practice;

import static com.teb.practice.config.ConfigReader.getKey;
import static com.teb.practice.util.JsonUtil.getNestedValue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.base.BaseTest;
import com.teb.practice.pages.InventoryPage;
import com.teb.practice.pages.LoginPage;

import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    void validLoginTest() {

        webDriver.get(getKey("login.url"));

        String username = getNestedValue("validUser", "username");
        String password = getNestedValue("validUser", "password");
        LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = new InventoryPage(webDriver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        assertTrue(inventoryPage.isLoaded(), "Inventory page should be visible after login");
    }

    @Test
    void invalidLoginTest() {

        webDriver.get(getKey("login.url"));

        String username = getNestedValue("invalidUser", "username");
        String password = getNestedValue("invalidUser", "password");
        LoginPage loginPage = new LoginPage(webDriver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String errorText = loginPage.getErrorMessage();

        assertTrue(
                errorText.contains("Username and password do not match"),
                "Error message should indicate invalid login");
    }
}
