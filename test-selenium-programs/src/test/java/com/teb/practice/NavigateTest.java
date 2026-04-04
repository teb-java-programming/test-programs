package com.teb.practice;

import static com.teb.practice.config.ConfigReader.getKey;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.base.BaseTest;
import com.teb.practice.pages.HomePage;

import org.junit.jupiter.api.Test;

public class NavigateTest extends BaseTest {

    @Test
    void openAndNavigateToDownloads() {

        webDriver.get(getKey("navigate.url"));

        HomePage homePage = new HomePage(webDriver);

        homePage.rejectConsent();
        homePage.clickShortsTab();

        assertTrue(homePage.isShortsTabActive(), "URL did not navigate to Shorts section");
    }
}
