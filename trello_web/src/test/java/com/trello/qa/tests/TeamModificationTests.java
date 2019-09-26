package com.trello.qa.tests;

import com.trello.qa.model.TeamData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamModificationTests extends TestBase {

    @BeforeMethod
    public void preconditions(){
        if(app.getTeamHelper().getTeamsCount()<1){
            app.getTeamHelper().clickOnPlusButtonOnHeader();
            app.getTeamHelper().fillTeamCreationForm(new TeamData().withTeamName("QA").withDescription("descr QA"));
            app.getTeamHelper().clickContinueButton();
            app.getTeamHelper().returnToHomePage();
        }
    }



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
