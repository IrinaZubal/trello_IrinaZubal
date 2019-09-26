package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardModificationTests extends TestBase{

    @BeforeMethod
    public void preconditions() {
        if (!app.getBoardHelper().isTherePersonalBoards()) {
            app.getBoardHelper().createBoard();
        }
    }

    @Test
    public void renameBoardTests(){
        app.getBoardHelper().clickOnFirstPrivateBoard();
        String nBoardName = "NewName";
        app.getBoardHelper().changeBoardName(nBoardName);
        app.getBoardHelper().returnToHomePage();
        app.getBoardHelper().refreshPage();

        Assert.assertTrue(app.getBoardHelper().findBoardByName(nBoardName));


    }


}
