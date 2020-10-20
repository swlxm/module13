package com.epam.java.selenium.driver;

import com.epam.java.selenium.utils.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Samuel_Shen
 */
@Slf4j
public class DriverSingleton {

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
        }
        return driver;
    }

    public void highlight(String elementName, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(elementName + " = arguments[0];" +
                "original_style = " + elementName + ".getAttribute('style');" +
                elementName + ".setAttribute('style', original_style + \";" +
                " border: 2px solid red;\");" +
                "setTimeout(function(){" + elementName + ".setAttribute('style', original_style);}, 3000);", element);

    }

    public static void close() {
        log.info("quit web driver instance...");
        driver.quit();
        driver = null;
    }
}
