package org.automation.pages;
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
    WebElement menuIcn;

    @FindBy(xpath = "//span[text()='News and Reviews']")
    WebElement reviewSection;

    @FindBy(xpath = "//span[text()='Reviews']")
    WebElement review;

    @FindBy(xpath = "//input[@id='nonUpcomingBikes']")
    WebElement searchBox;

    @FindBy(xpath ="//li[@data-testing-id='global-search-result-list']")
    WebElement suggestionLst;

    public void navigateToReviews() {
        wait.until(ExpectedConditions.elementToBeClickable(menuIcn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(reviewSection)).click();
        wait.until(ExpectedConditions.elementToBeClickable(review)).click();
    }

    public void searchForBike(String bikeName) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(bikeName);
        wait.until(ExpectedConditions.elementToBeClickable(suggestionLst));
        suggestionLst.click();
    }

    public String getPageTitle() {
        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs("Bike Reviews")));
        return driver.getTitle();
    }
}