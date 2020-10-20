package com.epam.java.selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DraftMailPage extends MailPage {
    private static final Logger log = LoggerFactory.getLogger(DraftMailPage.class);
    private By sendBy = By.xpath("//div[text()='Send']");
    private By discardBy = By.xpath("//div[text()='Discard drafts']");

    public DraftMailPage(WebDriver driver) {
        super(driver);
    }

    public void sendMail(WebElement element) throws InterruptedException {
        element.click();
        driver.findElement(sendBy).click();
        Thread.sleep(10*1000);
        log.info("Sent a new mail.");
    }

    public void discardMail(WebElement element) throws InterruptedException {
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        action.contextClick(element).perform();
        Thread.sleep(1000);
        WebElement discardBtn = driver.findElement(discardBy);
        js.executeScript("discardBtn = arguments[0];" +
                "original_style = discardBtn.getAttribute('style');" +
                "discardBtn.setAttribute('style', original_style + \";" +
                " border: 2px solid red;\");" +
                "setTimeout(function(){discardBtn.setAttribute('style', original_style);}, 3000);", discardBtn);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);
        log.info("Discarded mail.");
    }

}
