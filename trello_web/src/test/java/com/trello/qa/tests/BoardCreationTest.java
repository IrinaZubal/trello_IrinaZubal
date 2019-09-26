package com.trello.qa.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCreationTest extends  TestBase {
    @DataProvider
    public Iterator<Object[]> validBoards(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"board"});
        list.add(new Object[]{"BOARD"});
        list.add(new Object[]{"12346"});
        list.add(new Object[]{"*&&^%$#"});
        list.add(new Object[]{"board BOARD"});
        list.add(new Object[]{"לוח"});

        return list.iterator();
    }
    @Test(dataProvider = "validBoards")
    public void testBoardCreationWithDataProvider(String boardName) throws InterruptedException {
        BoardData board = new BoardData().withBoardName(boardName);

        int beforeCreation = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm(new BoardData().withBoardName(boardName));
        app.getBoardHelper().confirmBoardCreation();
        app.getBoardHelper().returnToHomePage();

        int afterCreation = app.getBoardHelper().getPersonalBoardsCount();

        Assert.assertEquals(afterCreation, beforeCreation + 1);

    }

    @Test
    public void testBoardCreation() throws InterruptedException {
        int beforeCreation = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm(new BoardData().withBoardName("QA 21"));
        app.getBoardHelper().confirmBoardCreation();
        app.getBoardHelper().returnToHomePage();

        int afterCreation = app.getBoardHelper().getPersonalBoardsCount();

        Assert.assertEquals(afterCreation, beforeCreation + 1);

    }



}