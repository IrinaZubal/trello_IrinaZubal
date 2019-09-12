package com.trello.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BoardDeletionTest extends  TestBase{
    @Test
    public void deletionBoardTest() throws InterruptedException {
        int before = app.getPersnalBoardsCount();
        clickOnFirstPrivateBoard();
        Thread.sleep(10000);
        clickOnMoreButtonInBoardMenu();
        //  initCloseBoard();
        //..


        int after = app.getPersnalBoardsCount();
    }

    public void clickOnMoreButtonInBoardMenu() {
        WebElement menuButton = app.driver.findElement(By.cssSelector(".board-header-btn.mod-show-menu"));
        System.out.println(menuButton.getCssValue("visibility"));
        if(menuButton.getCssValue("visibility").equals("visible")){
            app.click(By.cssSelector(".mod-show-menu"));
            app.click(By.cssSelector(".js-open-more"));
        } else{
            app.click(By.cssSelector(".js-open-more"));
        }

    }

    public void clickOnFirstPrivateBoard() {
        app.click(By.xpath("//*[@class='icon-lg icon-member']/../../..//li"));
    }


}