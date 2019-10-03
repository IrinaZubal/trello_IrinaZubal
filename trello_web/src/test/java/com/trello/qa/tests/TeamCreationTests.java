package com.trello.qa.tests;
import com.trello.qa.model.TeamData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeamCreationTests extends  TestBase{
    @DataProvider
    public Iterator<Object[]> validTeams(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"name","description"});
        list.add(new Object[]{"NAME","DESC"});
        list.add(new Object[]{"12345","678991"});
        list.add(new Object[]{"&&**(","@#$%^_^"});
        list.add(new Object[]{"name",""});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> validTeamsFromcsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader =new BufferedReader (new FileReader(new File("src/test/resources/Team.csv")));
        String line = reader.readLine();
        while(line != null){
            String[] split = line.split(",");
            list.add(new Object[]{new TeamData().withTeamName(split[0]).withDescription(split[1])});

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
        if(!isTherePersonalBoards()){
            app.getTeamHelper().returnToHomePage();
        }
    }

    public boolean isTherePersonalBoards() {
        return app.getTeamHelper().isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
    }

    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        int before = app.getTeamHelper().getTeamsCount();
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        String teamName = "QA21-"+ System.currentTimeMillis();
        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName(teamName).withDescription("description QA21"));
        app.getTeamHelper().clickContinueButton();
        //  String createdTeamName = getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before+1);
        //  Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

//    @Test
//    public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
//        int before = app.getTeamHelper().getTeamsCount();
//        clickOnPlusButtonOnLeftNavMenu();
//        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName("h").withDescription("g"));
//        app.getTeamHelper().clickContinueButton();
//        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
//        app.getTeamHelper().returnToHomePage();
//        //  refreshPage();
//        int after = app.getTeamHelper().getTeamsCount();
//
//        Assert.assertEquals(after, before+1);
//        Assert.assertEquals(createdTeamName, "h");
//    }
//
//
//
//    public void clickOnPlusButtonOnLeftNavMenu() {
//        app.getTeamHelper().click(By.cssSelector(".icon-add.icon-sm"));
//    }
//
//    @Test(enabled=false)
//    public void testTeamCancelCreationFromPlusButtonOnHeader(){
//        app.getTeamHelper().clickOnPlusButtonOnHeader();
//        app.getTeamHelper().selectCreateTeamFromDropDown();
//        app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName("QA 21").withDescription("description QA21"));
//        app.getTeamHelper().clickXButton();
//
//
//
//        Assert.assertTrue(app.getSessionHelper().isUserLoggedIn());
//    }
//    @Test(dataProvider = "validTeams")
//    public void testTeamCreationFromPlusButtonOnHeaderWithDataProvider(String teamName, String description) throws InterruptedException {
//       TeamData team = new TeamData().withTeamName(teamName).withDescription(description);
//        int before = app.getTeamHelper().getTeamsCount();
//        app.getTeamHelper().clickOnPlusButtonOnHeader();
//        app.getTeamHelper().selectCreateTeamFromDropDown();
//      //  String teamName = "QA21-"+ System.currentTimeMillis();
//        app.getTeamHelper().fillTeamCreationForm(team);
//        app.getTeamHelper().clickContinueButton();
//        //  String createdTeamName = getTeamNameFromTeamPage();
//        app.getTeamHelper().returnToHomePage();
//        int after = app.getTeamHelper().getTeamsCount();
//        Assert.assertEquals(after, before+1);
//        //  Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
//    }
//    @Test(dataProvider = "validTeamsFromcsv")
//    public void testTeamCreationFromPlusButtonOnHeaderWithDataProviderFromcsv(TeamData team){
//
//       // TeamData team = new TeamData().withTeamName(teamName).withDescription(description);
//        int before = app.getTeamHelper().getTeamsCount();
//        app.getTeamHelper().clickOnPlusButtonOnHeader();
//        app.getTeamHelper().selectCreateTeamFromDropDown();
//        //  String teamName = "QA21-"+ System.currentTimeMillis();
//        app.getTeamHelper().fillTeamCreationForm(team);
//        app.getTeamHelper().clickContinueButton();
//        //  String createdTeamName = getTeamNameFromTeamPage();
//        app.getTeamHelper().returnToHomePage();
//        int after = app.getTeamHelper().getTeamsCount();
//        Assert.assertEquals(after, before+1);
//        //  Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
//    }

    @AfterMethod
    public void postAction() throws InterruptedException {
        app.getTeamHelper().cleanTeams();
    }

}
