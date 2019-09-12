package com.trello.selenium;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTest extends  TestBase {
    @Test
    public void testBoardCreation() throws InterruptedException {
        int beforeCreation = getPersnalBoardsCount();
        clickOnPlusButtonOnHeader();
        selectCreateBoardFromDropDown();
        fillBoardCreationForm("qa21", "descr qa 21");
        confirmBoardCreation();
        returnToHomePage();

        int afterCreation = getPersnalBoardsCount();

        Assert.assertEquals(afterCreation, beforeCreation + 1);
    }



}