package com.trello.qa.manager;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;


public class UserHelper extends HelperBase{
    public UserHelper(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnAvatar() {
        click(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void openProfileFromDropDwn() {
        click(By.cssSelector("[data-test-id='header-member-menu-profile']"));
    }

    public void initAvatarChanging() {
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector(".rsiNque2CCqtPE"))).click().perform();
    }

    public void addPicture() throws InterruptedException {
        attach(new File("C:\\Users\\User\\Documents\\GitHub\\trello_IrinaZubal\\trello_web\\src\\test\\resources\\2014-03-22 10.57.26.jpg"));
        Thread.sleep(13000);
    }

    private void attach(File file) {
        driver.findElement(By.name("file")).sendKeys(file.getAbsolutePath());
    }
}
