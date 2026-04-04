package com.teb.practice.base;

import static com.teb.practice.driver.DriverFactory.createDriver;
import static com.teb.practice.driver.DriverManager.setWebDriver;
import static com.teb.practice.driver.DriverManager.unload;

import com.teb.practice.util.TestUtil;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(TestUtil.class)
public class BaseTest {

    protected WebDriver webDriver;

    @BeforeEach
    void setup() {

        webDriver = createDriver();
        setWebDriver(webDriver);
    }

    @AfterEach
    void tearDown() {

        if (webDriver != null) {
            webDriver.quit();
            unload();
        }
    }
}
