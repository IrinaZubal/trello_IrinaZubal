package com.trello.qa.manager;

import com.trello.qa.model.TeamData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class TeamHelper extends HelperBase {

    public TeamHelper(WebDriver driver) {
        super(driver);
    }

    public void clickContinueButton() {
        click(By.cssSelector("[type=submit]"));
    }

    public void fillTeamCreationForm(TeamData team) {
        type(By.cssSelector("[data-test-id='header-create-team-name-input']"), team.getTeamName());
        type(By.cssSelector("textarea"), team.getDescription());
    }

    public void selectCreateTeamFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-team-button']"));
    }

    public String getTeamNameFromTeamPage() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
        return driver.findElement(By.cssSelector("h1")).getText();
    }

    public int getTeamsCount()  {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
        return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
    }

    public void clickXButton() {

    }
    public void clickOnFirstTeam() {
        click(By.cssSelector("[data-test-id^='home-team-tab-section-']"));
    }


    public void deleteTeam() throws InterruptedException {
        Thread.sleep(5000);
        click(By.cssSelector(".quiet-button"));
        click(By.cssSelector(".js-confirm"));
    }

    public void cleanTeams() throws InterruptedException{
        int count = getTeamsCount();
        while(count > 5){
            clickOnFirstTeam();
            openSettings();
            deleteTeam();
            //returnToHomePage();
            refreshPage();
            count = getTeamsCount();
            System.out.println(count);

        }
   }

    public void initEditTeamProfile() {
        click(By.cssSelector(".js-edit-profile"));
       // waitForElementAndClick(By.cssSelector(".js-edit-profile"),10);
    }

    public void changeTeamProfile(String name, String desc) {
        type(By.name("displayName"),name);
        type(By.name("desc"),desc);

    }

    public void confirmEditTeam() {
        click(By.cssSelector(".js-submit-profile"));
    }

    public void openSettings() throws InterruptedException {

        Thread.sleep(5000);
        click(By.cssSelector("ul .icon-gear.icon-sm"));

        //app.getTeamHelper().click(By.cssSelector(".icon-gear icon-sm OiX3P2i2J92Xat"));
        //app.getTeamHelper().click(By.cssSelector(".icon-gear.icon-sm.OiX3P2i2J92Xat"));
    }

}
