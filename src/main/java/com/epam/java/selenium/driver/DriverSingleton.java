package com.epam.java.selenium.driver;

import com.epam.java.selenium.utils.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Samuel_Shen
 */
public class DriverSingleton {
    private static final Logger log = LoggerFactory.getLogger(DriverSingleton.class);
    private static WebDriver driver;
    private DriverSingleton() {}

    public static WebDriver getDriver(String env, String browser) throws IOException {
        if(null == driver) {
            if(null == browser)
                browser = PropertiesUtil.getInstance(env).getProperty("browser");
            switch (browser) {
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                default:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--lang=en-us");
                    options.addArguments("--start-maximized");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);

            }
            log.info("Get the web driver instance of browser {}", browser);
        }
        return driver;
    }

    public static void close() {
        log.info("quit web driver instance...");
        driver.quit();
        driver = null;
    }
}
