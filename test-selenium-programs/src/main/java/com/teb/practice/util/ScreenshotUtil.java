package com.teb.practice.util;

import static com.teb.practice.driver.DriverManager.getWebDriver;

import static org.openqa.selenium.OutputType.FILE;

import static java.nio.file.Files.copy;
import static java.nio.file.Files.createDirectories;
import static java.time.LocalDateTime.now;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static void takeScreenshot(String testName) {

        WebDriver webDriver = getWebDriver();

        if (webDriver == null) return;

        String fileName = testName + "_" + now().toString().replace(":", "-") + ".png";
        File destination = new File("screenshots/" + fileName);
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(FILE);

        try {
            createDirectories(destination.getParentFile().toPath());
            copy(screenshot.toPath(), destination.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
