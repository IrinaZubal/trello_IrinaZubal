package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamModificationTests extends TestBase {



    @Test
    public void testRenameTeam() throws InterruptedException {
app.getTeamHelper().clickOnFirstTeam();
app.getTeamHelper().openSettings();
app.getTeamHelper().initEditTeamProfile();
app.getTeamHelper().changeTeamProfile("hh","hha");
app.getTeamHelper().confirmEditTeam();

Thread.sleep(5000);
System.out.println(app.getTeamHelper().getTeamNameFromTeamPage());
Thread.sleep(5000);
Assert.assertEquals(app.getTeamHelper().getTeamNameFromTeamPage(),"hh");

    }

}
