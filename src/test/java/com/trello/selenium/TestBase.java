package com.trello.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

WebDriver driver;




@BeforeMethod
public void setUp(){
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    openSite("https://trello.com");
    login("irinaz.inbox@gmail.com","kh17rina91");

}

    public void openSite(String url) {
    driver.get(url);
    }


    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }


    public void login(String email, String password){
    click(By.cssSelector("[href='/login']"));
        type(By.cssSelector("[type=email]"),email);
        type(By.cssSelector("[type=password]"),password);
        click(By.id("login"));

    }

    @AfterClass
    public void tearDown(){

        driver.quit();
    }
}
