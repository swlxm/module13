package com.epam.java.selenium.listeners;

import com.epam.java.selenium.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ListenerImpl implements ITestListener {

    private Logger logger = LoggerFactory.getLogger(ListenerImpl.class);

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot();
    }

    private void saveScreenshot() {
        try {
            File screen = ((TakesScreenshot)DriverSingleton.getDriver("", "")).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screen, new File(".//target/screenshots/" + getTimestamp() + ".png"));
        } catch (IOException e) {
            logger.error("Failed to take screen shot: " + e.getLocalizedMessage());
        }
    }

    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
