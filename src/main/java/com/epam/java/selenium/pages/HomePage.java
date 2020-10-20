package com.epam.java.selenium.pages;

import com.epam.java.selenium.entities.Email;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Home page after login, contains common methods, like composer, navigate to different pages
 */
public class HomePage {
    private static final Logger log = LoggerFactory.getLogger(HomePage.class);
    protected static WebDriver driver;

    private By composeBy = By.xpath("//div[text()='Compose']");
    private By toBy = By.name("to");
    private By subjectBy = By.name("subjectbox");
    private By bodyBy = By.xpath("//div[@aria-label='Message Body']");
    private By closeBtnBy = By.xpath("//img[@alt='Close']");
    private By draftBy = By.linkText("Drafts");
    private By sendBy = By.xpath("//div[text()='Send']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void createDraftMail(Email email) throws InterruptedException {

        driver.findElement(composeBy).click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(toBy));
        driver.findElement(toBy).sendKeys(email.getTo());
        driver.findElement(subjectBy).sendKeys(email.getSubject());
        driver.findElement(bodyBy).sendKeys(email.getBody());
        driver.findElement(closeBtnBy).click();
        Thread.sleep(1000);
        log.info("Created a new draft mail.");
    }

    public void sendMail(Email email) throws InterruptedException {
        driver.findElement(composeBy).click();
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(toBy));
        driver.findElement(toBy).sendKeys(email.getTo());
        driver.findElement(subjectBy).sendKeys(email.getSubject());
        driver.findElement(bodyBy).sendKeys(email.getBody());
        driver.findElement(sendBy).click();
        Thread.sleep(1000*10);
        log.info("Sent a mail.");
    }

    public DraftMailPage openDraftMailPage(WebDriver driver) throws InterruptedException {
        driver.findElement(draftBy).click();
        Thread.sleep(10*1000);
        log.info("Navigated to draft mail page.");
        return new DraftMailPage(driver);
    }

}
