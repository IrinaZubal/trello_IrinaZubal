package com.trello.qa.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTest extends  TestBase {
    @Test
    public void testBoardCreation() throws InterruptedException {
        int beforeCreation = app.getBoardHelper().getPersnalBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm("QA 21", "descr QA21");
        app.getBoardHelper().confirmBoardCreation();
        app.getBoardHelper().returnToHomePage();

        int afterCreation = app.getBoardHelper().getPersnalBoardsCount();

        Assert.assertEquals(afterCreation, beforeCreation + 1);
    }



}