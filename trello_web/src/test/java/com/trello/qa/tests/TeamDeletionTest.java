
package com.trello.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamDeletionTest extends TestBase {

    @Test
    public void deleteTeamFromLeftNavMenu() throws InterruptedException {
        int before = app.getTeamHelper().getTeamsCount();
        clickOnFirstTeam();
       app.getTeamHelper().openSettings();
        deleteTeam();
        app.getTeamHelper().refreshPage();

        app.getTeamHelper().returnToHomePage();
        int after = app.getTeamHelper().getTeamsCount();
        Assert.assertEquals(after, before - 1);


    }

    public void deleteTeam() {
        new WebDriverWait(app.driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".quiet-button")));
        app.getTeamHelper().click(By.cssSelector(".quiet-button"));
        app.getTeamHelper().click(By.cssSelector(".js-confirm"));

    }

    public void clickOnFirstTeam() {
        app.getTeamHelper().click(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li"));
    }
}