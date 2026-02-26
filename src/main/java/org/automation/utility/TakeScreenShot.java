package org.automation.utility;

import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class TakeScreenShot {


    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File est=new File("./screenshots/"+fileName+".png");
        est.getParentFile().mkdirs();
        Files.copy(src.toPath(), est.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}

