package com.epam.java.selenium.listeners;

import com.epam.java.selenium.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ListenerImpl implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(ListenerImpl.class);

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot();
    }

    private void saveScreenshot() {
        try {
            String timestamp = getTimestamp();
            File screen = ((TakesScreenshot)DriverSingleton.getDriver("", "")).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screen, new File(".//target/screenshots/" + timestamp + ".png"));
            String path = new File("target/screenshots/" + timestamp + ".png").getAbsolutePath();
            log.debug(path);
            log.info("RP_MESSAGE#FILE#{}#{}", path, "Screenshot file " + path + " was saved");
        } catch (IOException e) {
            log.error("Failed to take screen shot: {}", e.getLocalizedMessage());
        }
    }

    private String getTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
