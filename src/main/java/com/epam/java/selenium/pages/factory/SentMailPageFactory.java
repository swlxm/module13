package com.epam.java.selenium.pages.factory;

import com.epam.java.selenium.pages.MailPage;
import com.epam.java.selenium.pages.SentMailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Samuel_Shen
 */
public class SentMailPageFactory extends Factory {
    private static final Logger log = LoggerFactory.getLogger(SentMailPageFactory.class);
    private By sentBy = By.linkText("Sent");

    @Override
    public MailPage getMailPage(WebDriver driver) throws InterruptedException {
        log.info("Navigated to sent page.");
        driver.findElement(sentBy).click();
        Thread.sleep(10*1000);
        return new SentMailPage(driver);
    }
}
