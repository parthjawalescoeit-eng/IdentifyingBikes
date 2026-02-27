package basetest.automation.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;
    //test
    public ConfigReader() throws IOException {
       // FileInputStream fis = new FileInputStream("src/main/java/basetest/automation/resources/config.properties");
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/basetest/automation/resources/config.properties");
        properties = new Properties();
        properties.load(fis);
    }
    public String getProp(String key) {
        return properties.getProperty(key);
    }
}
