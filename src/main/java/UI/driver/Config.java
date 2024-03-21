package UI.driver;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private static Properties property;

    public Config() {
        property = new Properties();
        try {
            property.load(Files.newInputStream(Paths.get("src/main/resources/driver/driver_config.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String getBrowserType() {
        return property.getProperty("BROWSER_TYPE");
    }

    public String getOsType() {
        return property.getProperty("OS_TYPE");
    }
    public String getLaunchAT() {
        return property.getProperty("LAUNCH_AT");
    }

}
