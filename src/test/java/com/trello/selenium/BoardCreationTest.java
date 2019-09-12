package com.trello.selenium;


import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTest extends  TestBase {
    @Test
    public void testBoardCreation() throws InterruptedException {
        int beforeCreation = app.getPersnalBoardsCount();
        app.clickOnPlusButtonOnHeader();
        app.selectCreateBoardFromDropDown();
        app.fillBoardCreationForm("qa21", "descr qa 21");
        app.confirmBoardCreation();
        app.returnToHomePage();

        int afterCreation = app.getPersnalBoardsCount();

        Assert.assertEquals(afterCreation, beforeCreation + 1);
    }



}