package com.onesports.resources;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties prop = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/com/onesports/resources/config.properties"
            );
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    private static String getEnv() {
        return System.getProperty("env", prop.getProperty("env"));
    }

    public static String getBaseUrl() {
        return prop.getProperty(getEnv() + ".url");
    }

    public static String getUsername() {
        return prop.getProperty(getEnv() + ".username");
    }

    public static String getPassword() {
        return prop.getProperty(getEnv() + ".password");
    }

    public static String getBrowser() {
        return System.getProperty("browser", prop.getProperty("browser", "chrome"));
    }

     public static String amazonUrl() {
       return prop.getProperty("amazon.url");
    }
}
