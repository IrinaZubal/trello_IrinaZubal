package com.trello.qa.manager;

import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class HelperBase {
   AppiumDriver driver;

    public HelperBase(AppiumDriver driver) {

        this.driver = driver;
    }

    public void waitForElementAndClick(By locator, int time){
        new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text){
        if(text != null){
            driver.findElement(locator).click();
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }



    }
    public boolean isTherePersonalBoards() {
        return isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
    }
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size()>0;
    }

    public void clickOnPlusButtonOnHeader() {
        waitForElementAndClick(By.cssSelector("[data-test-id='header-create-menu-button']"),15);
    }

    public void returnToHomePage() {
        if(isElementPresent(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))){
            new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))));
            click(By.cssSelector("a[href='/']"));
            click(By.cssSelector("a[href='/']"));
        } else
            waitForElementAndClick(By.cssSelector("a[href='/']"),15);
    }
    public void refreshPage() {

        driver.navigate().refresh();
    }
     public void takeScreenshot() {
         File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
         File screen = new File("src/test/resources/Screenshots/screen" + System.currentTimeMillis() + ".png");
         try {
             Files.copy(tmp, screen);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public void swipeLeft(By locator){
         TouchAction touch = new TouchAction(driver);

         WebElement element = driver.findElement(locator);

         int leftX = element.getLocation().getX(); //get left point
         int rightX = leftX + element.getSize().getWidth()-10; // get right point

         int upperY = element.getLocation().getY();
         int lowerY = upperY + element.getSize().getHeight();
         int middleY = (upperY + lowerY)/2;

         touch.
                 longPress(PointOption.point(rightX,middleY)).
                 moveTo(PointOption.point(leftX,middleY)).
                 release().
                 perform();



     }



}





