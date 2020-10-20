package com.epam.java.selenium.tests;

import com.epam.java.selenium.entities.Email;
import com.epam.java.selenium.entities.EmailBuilder;
import com.epam.java.selenium.pages.*;
import com.epam.java.selenium.pages.factory.Factory;
import com.epam.java.selenium.pages.factory.InboxMailPageFactory;
import com.epam.java.selenium.pages.factory.SentMailPageFactory;
import com.epam.java.selenium.utils.Utils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ReplyMailTest extends BaseTest {

    private Utils utils = new Utils();
    private HomePage homePage;
    private String subject;

    @Test
    public void replyMail() throws InterruptedException {
        subject = utils.genTimestamp();
        homePage = login();
        Email email = new EmailBuilder().setBody("send by selenium").setTo("module7.epam@gmail.com").setSubject(subject).build();
        homePage.sendMail(email);
        Factory inboxPageFactory = new InboxMailPageFactory();
        MailPage inboxPage = inboxPageFactory.getMailPage(driver);
        WebElement mail = inboxPage.getLatestMail();
        String text = inboxPage.getMailText(mail);
        String subject = text.split("\n")[1];
        assertTrue(text.contains(subject));
        email.setBody("reply by selenium");
        inboxPage.reply(mail, email.getBody());
        Factory sendMailPageFactory = new SentMailPageFactory();
        MailPage sentMailPage = sendMailPageFactory.getMailPage(driver);
        mail = sentMailPage.getLatestMail();
        text = sentMailPage.getMailText(mail);
        assertTrue(text.contains(subject));
        logout();
    }

}
