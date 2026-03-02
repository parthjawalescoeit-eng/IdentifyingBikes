package org.automation.utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;

public class TakeScreenShot {

    private final WebDriver driver;
    private final String outDir;

    public TakeScreenShot(WebDriver driver, String outDir) {
        this.driver = driver;
        this.outDir = outDir == null ? "screenshots" : outDir;
    }

    public void take(String name) {
        String path = System.getProperty("user.dir") + File.separator + outDir + File.separator + name  + ".png";
        try {
            new File(System.getProperty("user.dir") + File.separator + outDir).mkdirs();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
            System.out.println("Screenshot saved: " + path);
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}


