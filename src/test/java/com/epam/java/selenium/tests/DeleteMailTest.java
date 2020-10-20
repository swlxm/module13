package com.epam.java.selenium.tests;

import com.epam.java.selenium.pages.*;
import com.epam.java.selenium.pages.factory.Factory;
import com.epam.java.selenium.pages.factory.SentMailPageFactory;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DeleteMailTest extends BaseTest {

    @Test
    public void deleteMail() throws InterruptedException {
        login();
        Factory sentMailPageFactory = new SentMailPageFactory();
        MailPage sentMailPage = sentMailPageFactory.getMailPage(driver);
        WebElement mail = sentMailPage.getLatestMail();
        String text = sentMailPage.getMailText(mail);
        sentMailPage.deleteMail(mail);
        mail = sentMailPage.getLatestMail();
        assertNotEquals(sentMailPage.getMailText(mail), text);
        logout();
    }
}
