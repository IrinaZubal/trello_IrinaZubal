package com.trello.qa.tests;


import com.trello.qa.model.BoardData;
import com.trello.qa.model.TeamData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    @DataProvider
    public Iterator<Object[]> validBoardFromcsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader =new BufferedReader (new FileReader(new File("src/test/resources/Board.csv")));
        String line = reader.readLine();
        while(line != null){
            list.add(new Object[]{new BoardData().withBoardName(line)});
            //BoardData boardData = new BoardData();
            //list.add(boardData.withBoardName(line));
            line = reader.readLine();
        }

        return list.iterator();
    }
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
//    @Test(dataProvider = "validBoardFromcsv")
//    public void testBoardCreationWithDataProviderFromcsv(String boardName) throws InterruptedException {
//        //BoardData board = new BoardData().withBoardName(boardName);
//
//        int beforeCreation = app.getBoardHelper().getPersonalBoardsCount();
//        app.getBoardHelper().clickOnPlusButtonOnHeader();
//        app.getBoardHelper().selectCreateBoardFromDropDown();
//        app.getBoardHelper().fillBoardCreationForm(new BoardData().withBoardName(boardName));
//        app.getBoardHelper().confirmBoardCreation();
//        app.getBoardHelper().returnToHomePage();
//
//        int afterCreation = app.getBoardHelper().getPersonalBoardsCount();
//
//        Assert.assertEquals(afterCreation, beforeCreation + 1);
//
//    }

    @Test
    public void testBoardCreation() throws InterruptedException {
        int beforeCreation = app.getBoardHelper().getPersonalBoardsCount();
        app.getBoardHelper().clickOnPlusButtonOnHeader();
        app.getBoardHelper().selectCreateBoardFromDropDown();
        app.getBoardHelper().fillBoardCreationForm(new BoardData().withBoardName("Apple"));
        app.getBoardHelper().confirmBoardCreation();
        app.getBoardHelper().returnToHomePage();

        int afterCreation = app.getBoardHelper().getPersonalBoardsCount();

        Assert.assertEquals(afterCreation, beforeCreation + 1);

    }

    @AfterClass
    public void deleteBoardsTillFiveLeft () throws InterruptedException {
        app.getBoardHelper().deleteBoardsTillFiveLeft();
    }



}