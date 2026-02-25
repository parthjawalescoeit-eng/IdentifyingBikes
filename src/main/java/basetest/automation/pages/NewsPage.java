package basetest.automation.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class NewsPage {
    WebDriver driver;
    public NewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[text()='All News']")
    WebElement newsMenuLink;
    @FindBy(xpath = "//a[@class='o-jJ o-jz']")
    List<WebElement> newsHeadlines;
    public void navigateToNews() {
        newsMenuLink.click();
    }
    public List<String> getAllHeadlines() {
        List<String> headlineTexts = new ArrayList<>();
        for (WebElement headline : newsHeadlines) {
            String text = headline.getText().trim();
            if (!text.isEmpty()) {
                headlineTexts.add(text);
            }
        }
        return headlineTexts;
    }
}