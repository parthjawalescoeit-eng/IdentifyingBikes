package basetest;

import org.automation.utility.ConfigReader;
import org.automation.utility.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    static protected WebDriver driver;
    static protected WaitUtils wait;
    @BeforeClass
    public void driverSetup() throws IOException {
        ConfigReader configReader=new ConfigReader();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(configReader.getProp("baseUrl"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait=new WaitUtils(driver,Duration.ofSeconds(10));
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver=null;
        }
    }
}
