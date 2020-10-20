package com.epam.java.selenium.pages.factory;

import com.epam.java.selenium.pages.InboxMailPage;
import com.epam.java.selenium.pages.MailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Samuel_Shen
 */
public class InboxMailPageFactory extends Factory {
    private static final Logger log = LoggerFactory.getLogger(InboxMailPageFactory.class);
    private By inboxBy = By.linkText("Inbox");

    @Override
    public MailPage getMailPage(WebDriver driver) throws InterruptedException {
        log.info("Navigated to inbox page.");
        driver.findElement(inboxBy).click();
        Thread.sleep(10*1000);
        return new InboxMailPage(driver);
    }
}
