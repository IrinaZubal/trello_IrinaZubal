package com.trello.selenium;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends  TestBase{
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
        app.getTeamHelper().fillTeamCreationForm(teamName, "description QA21");
        app.getTeamHelper().clickContinueButton();
        //  String createdTeamName = getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before+1);
        //  Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

    @Test
    public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
        int before = app.getTeamHelper().getTeamsCount();
        clickOnPlusButtonOnLeftNavMenu();
        app.getTeamHelper().fillTeamCreationForm("h", "g");
        app.getTeamHelper().clickContinueButton();
        String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
        app.getTeamHelper().returnToHomePage();
        //  refreshPage();
        int after = app.getTeamHelper().getTeamsCount();

        Assert.assertEquals(after, before+1);
        Assert.assertEquals(createdTeamName, "h");
    }

    public void refreshPage() {
        app.driver.navigate().refresh();
    }

    public void clickOnPlusButtonOnLeftNavMenu() {
        app.getTeamHelper().click(By.cssSelector(".icon-add.icon-sm"));
    }

    @Test(enabled=false)
    public void testTeamCancelCreationFromPlusButtonOnHeader(){
        app.getTeamHelper().clickOnPlusButtonOnHeader();
        app.getTeamHelper().selectCreateTeamFromDropDown();
        app.getTeamHelper().fillTeamCreationForm("QA 21", "description QA21");
        app.getTeamHelper().clickXButton();



        Assert.assertTrue(app.getSessionHelper().isUserLoggedIn());
    }


}
