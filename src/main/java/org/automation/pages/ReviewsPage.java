package org.automation.pages;

import org.automation.utility.TakeScreenShot;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReviewsPage {
    WebDriver driver;
    WebDriverWait wait;

    public ReviewsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='header__menu-icon']")
    WebElement menuIcon;
    @FindBy(xpath = "//span[text()='News and Reviews']")
    WebElement reviewSection;
    @FindBy(xpath = "//span[text()='Reviews']")
    WebElement review;
    @FindBy(xpath = "//input[@id='nonUpcomingBikes']")
    WebElement searchBox;
    @FindBy(xpath = "//input[@id='btnSearch']")
    WebElement searchButton;
    @FindBy(xpath ="//li[@data-testing-id='global-search-result-list']")
    WebElement suggestionList;
    public void navigateToReviews() {
        wait.until(ExpectedConditions.elementToBeClickable(menuIcon)).click();
        wait.until(ExpectedConditions.elementToBeClickable(reviewSection)).click();
        wait.until(ExpectedConditions.elementToBeClickable(review)).click();
    }
    public void searchForBike(String bikeName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(bikeName);
        wait.until(ExpectedConditions.elementToBeClickable(suggestionList));
        suggestionList.click();
    }
    public String getPageTitle() {
        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("Bike Reviews")));
        return driver.getTitle();
    }
}