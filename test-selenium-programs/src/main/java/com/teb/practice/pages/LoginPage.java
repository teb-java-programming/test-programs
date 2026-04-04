package com.teb.practice.pages;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import com.teb.practice.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = id("user-name");
    private final By passwordInput = id("password");
    private final By loginButton = id("login-button");
    private final By errorMessage = cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterUsername(String username) {

        webDriver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {

        webDriver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {

        webDriver.findElement(loginButton).click();
    }

    public String getErrorMessage() {

        return webDriver.findElement(errorMessage).getText();
    }
}
