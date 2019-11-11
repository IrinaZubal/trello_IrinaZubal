package com.trello.qa.manager;

import com.trello.qa.model.BoardData;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardHelper extends HelperBase{


    public BoardHelper(AppiumDriver driver) {
        super(driver);

    }

    public void fillBoardCreationForm(BoardData board) {
        type(By.cssSelector("[data-test-id='header-create-board-title-input']"), board.getBoardName());

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

    public int getPersonalBoardsCount() {
        return driver.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size()-1;
    }

    public void clickOnFirstPrivateBoard() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")));
        click(By.xpath("//*[@class='icon-lg icon-member']/../../..//li"));


    }

    public void changeBoardName(String BoardName) {
        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".js-board-editing-target board-header-btn-text")));

      //  click(By.cssSelector(".js-board-editing-target board-header-btn-text"));
        click(By.xpath("//span[@class='js-board-editing-target board-header-btn-text']"));

        typeBoardModification(By.xpath("//input[@class='board-name-input js-board-name-input']"),BoardName);

    }

    private void typeBoardModification(By locator, String text) {

            driver.findElement(locator);
            //driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);

    }

    public void createBoard() {
        clickOnPlusButtonOnHeader();
        selectCreateBoardFromDropDown();
        fillBoardCreationForm(new BoardData().withBoardName("QA 21"));
        confirmBoardCreation();
        returnToHomePage();
        refreshPage();
        takeScreenshot();
    }

    public boolean findBoardByName(String nBoardName) {
        return driver.findElement(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).getText().equals(nBoardName);
    }

    public void clickOnMoreButtonInBoardMenu() {
        WebElement menuButton = driver.findElement(By.cssSelector(".board-header-btn.mod-show-menu"));
        System.out.println(menuButton.getCssValue("visibility"));
        if(menuButton.getCssValue("visibility").equals("visible")){
            click(By.cssSelector(".mod-show-menu"));
            click(By.cssSelector(".js-open-more"));
        } else{
            click(By.cssSelector(".js-open-more"));
        }

    }

    public void deleteBoardsTillFiveLeft() throws InterruptedException {
        int before = getPersonalBoardsCount();
        while (before > 5) {
            clickOnFirstPrivateBoard();
            Thread.sleep(3000);
            clickOnMoreButtonInBoardMenu();
            closeBoard();
            deleteBoard();
            returnToHomePage();
            before = getPersonalBoardsCount();


    }
}

    public void closeBoard() {
        click(By.cssSelector("[class='board-menu-navigation-item-link js-close-board']"));
        click(By.cssSelector("[class='js-confirm full negate']"));
    }

    public void deleteBoard() throws InterruptedException {
        refreshPage();
        Thread.sleep(4000);
        waitForElementAndClick(By.xpath("//a[@class='quiet js-delete']"), 20);
        click(By.xpath("//input[@class='js-confirm full negate']"));
    }
//****************************************************************************************
    public void clickOnPlusButton() {

        click(By.id("add_fab"));
    }
}
