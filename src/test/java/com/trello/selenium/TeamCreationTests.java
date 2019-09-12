package com.trello.selenium;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends  TestBase{
    @BeforeClass
    public void ensurePreconditionsLogin(){
        if(!app.isUserLoggedIn()){
            app.login("irinaz.inbox@gmail.com", "kh17rina91");
        }
    }

    @BeforeMethod
    public void isOnHomePage(){
        if(!isTherePersonalBoards()){
            app.returnToHomePage();
        }
    }

    public boolean isTherePersonalBoards() {
        return app.isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
    }

    @Test
    public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
        int before = app.getTeamsCount();
        app.clickOnPlusButtonOnHeader();
        app.selectCreateTeamFromDropDown();
        String teamName = "qa21-"+ System.currentTimeMillis();
        app.fillTeamCreationForm(teamName, "descr qa 21");
        app.clickContinueButton();
        //  String createdTeamName = getTeamNameFromTeamPage();
        app.returnToHomePage();
        int after = app.getTeamsCount();
        Assert.assertEquals(after, before+1);
        //  Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
    }

    @Test
    public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
        int before = app.getTeamsCount();
        clickOnPlusButtonOnLeftNavMenu();
        app.fillTeamCreationForm("h", "g");
        app.clickContinueButton();
        String createdTeamName = app.getTeamNameFromTeamPage();
        app.returnToHomePage();
        //  refreshPage();
        int after = app.getTeamsCount();

        Assert.assertEquals(after, before+1);
        Assert.assertEquals(createdTeamName, "h");
    }

    public void refreshPage() {
        app.driver.navigate().refresh();
    }

    public void clickOnPlusButtonOnLeftNavMenu() {
        app.click(By.cssSelector(".icon-add.icon-sm"));
    }

    @Test(enabled=false)
    public void testTeamCuncellCreationFromPlusButtonOnHeader(){
        app.clickOnPlusButtonOnHeader();
        app.selectCreateTeamFromDropDown();
        app.fillTeamCreationForm("qa21", "descr qa 21");
        app.clickXButton();
        //Assert


        Assert.assertTrue(app.isUserLoggedIn());
    }


}
