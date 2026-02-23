package org.automation.pages;

import org.automation.utility.JavaScriptUtil;
import org.automation.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TogglePage {

    private final WebDriver driver;
    private final WaitUtils wait;
    private final JavaScriptUtil js;

    public TogglePage(WebDriver driver , WaitUtils wait){
        this.driver = driver;
        this.wait = wait;
        this.js = new JavaScriptUtil(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='header__menu-icon']")
    WebElement sidebar;

    @FindBy(xpath = "//div[@role='switch']//span[2][@theme='light']")
    WebElement toggleButton;

    @FindBy(xpath = "//a[@title='Home']")
    WebElement homeButton;


    public void openSideBar(){

        //wait.pageReady();

        wait.clickWhenClickable(sidebar);
        js.scrollIntoView(toggleButton);
        wait.safeClickToWebElement(toggleButton);

        wait.clickWhenClickable(homeButton);



    }

}
