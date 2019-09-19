package com.trello.qa.tests;

import org.testng.annotations.Test;

public class TeamModificationTests extends TestBase {



    @Test
    public void testRenameTeam() throws InterruptedException {
app.getTeamHelper().clickOnFirstTeam();
app.getTeamHelper().openSettings();
app.getTeamHelper().initEditTeamProfile();
app.getTeamHelper().changeTeamProfile("hh","hha");
app.getTeamHelper().confirmEditTeam();

    }

}
