package com.trello.qa.tests;

import org.testng.annotations.Test;

public class BoardModificationTests extends TestBase{

    @Test
    public void renameBoardTests(){
        app.getBoardHelper().clickOnFirstPrivateBoard();
        app.getBoardHelper().changeBoardName("NewBoBoardName");
        app.getBoardHelper().returnToHomePage();
        app.getBoardHelper().refreshPage();


    }


}
