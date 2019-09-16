package com.trello.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoardHelper extends HelperBase{
    public BoardHelper(WebDriver driver) {
        super(driver);
    }

    public void fillBoardCreationForm(String boardName, String s) {
        type(By.cssSelector("[data-test-id='header-create-board-title-input']"), boardName);

        if(isElementPresent(By.cssSelector(".W6rMLOx8U0MrPx"))){
            click(By.cssSelector(".W6rMLOx8U0MrPx"));
            click(By.xpath("//nav[@class='SdlcRrTVPA8Y3K']//li[1]"));//no team
        }

    }

    public void selectCreateBoardFromDropDown() {
        click(By.cssSelector("[data-test-id='header-create-board-button']"));
    }

    public void confirmBoardCreation() {
        click(By.cssSelector("[data-test-id='header-create-board-submit-button']"));
    }

    public int getPersnalBoardsCount() {
        return driver.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size()-1;
    }
}
