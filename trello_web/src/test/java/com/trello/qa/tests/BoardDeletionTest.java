package com.trello.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTest extends  TestBase{
    @BeforeClass
    public void ensurePreconditionsLogin(){
        if(!app.getSessionHelper().isUserLoggedIn()){
            app.getSessionHelper().login("irinaz.inbox@gmail.com", "kh17rina91");
        }
    }

    @BeforeMethod
    public void isOnHomePage(){
        if(!app.getBoardHelper().isTherePersonalBoards()){
            app.getBoardHelper().returnToHomePage();
        }
    }

    @Test
    public void deletionBoardTest() throws InterruptedException {
        int before = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().clickOnFirstPrivateBoard();
        Thread.sleep(10000);
        app.getBoardHelper().clickOnMoreButtonInBoardMenu();
        app.getBoardHelper().closeBoard();
        app.getBoardHelper().deleteBoard();
        app.getBoardHelper().returnToHomePage();
        app.getBoardHelper().refreshPage();
        int after = app.getBoardHelper().getPersonalBoardsCount();
        Assert.assertEquals(after, before-1);


    }


//
//    public void clickOnFirstPrivateBoard() {
//        app.getSessionHelper().click(By.xpath("//*[@class='icon-lg icon-member']/../../..//li"));
//    }
//

}