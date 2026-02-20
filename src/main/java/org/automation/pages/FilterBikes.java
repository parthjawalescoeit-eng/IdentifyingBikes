// FilterBikes.java (By-based version, robust against stale)
package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FilterBikes {

    private final WebDriver driver;
    private final WaitUtils wait;
    private final JavaScriptUtil js;

    private final By budgetOption = By.xpath("//span[@data-testing-id='budget-tab']");
    private final By twoLacksOption = By.xpath("//a[@data-testid='budget-under-₹2-lakh' or contains(text(),'Under ₹2 lakh')]");
    private final By brandOption = By.xpath("//div[@data-display-name='Brand' or contains(text(), 'Brand')]//span");
    private final By bajajCheckbox = By.xpath("//label[.//span[normalize-space(.)='Bajaj']]//input[@type='checkbox']");
    private final By applyFilterButton = By.xpath("//button[@type='button' and contains(text(), 'Apply Filters')]");
    private final By bikeCountOnPage = By.xpath("//header[contains(@class,'o-bO') and .//h2[@data-skin='title']]//h2[@data-skin='title']");
    private final By cards = By.cssSelector("ul[data-skin='bg-transparent'] li.o-em.o-hk"); // if you need it

    public FilterBikes(WebDriver driver, WaitUtils wait){
        this.driver = driver;
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
    }

    public void filterBikesUnderTwoLacks() {

        wait.pageReady();

//        js.scrollIntoView(wait.visible(budgetOption));
        wait.safeClick(budgetOption);

        wait.safeClick(twoLacksOption);

        wait.safeClick(brandOption);

        wait.safeClick(bajajCheckbox);

        js.scrollIntoView(wait.visible(applyFilterButton));
        // Capture old count text (if present) to detect refresh
        String oldCount = "";
        try {
            oldCount = driver.findElement(bikeCountOnPage).getText().trim();
        } catch (Exception ignored) {}

        wait.safeClick(applyFilterButton);

    }

    public String getCount() {
        return driver.findElement(bikeCountOnPage).getText().trim();
    }
}