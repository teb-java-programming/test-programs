package com.teb.practice.util;

import static com.teb.practice.util.ScreenshotUtil.takeScreenshot;

import lombok.extern.java.Log;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

@Log
public class TestUtil implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {

        takeScreenshot(context.getDisplayName());
    }

    @Override
    public void testSuccessful(ExtensionContext context) {

        log.info("PASS: " + context.getDisplayName());
    }
}
