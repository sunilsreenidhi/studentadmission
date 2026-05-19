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

    public static String getStudentApplyUrl() {
        return prop.getProperty(getEnv() + ".studentapply.url");
    }

    public static String getStudentEnquiryUrl() {
        return prop.getProperty(getEnv() + ".studentenquiry.url");
    }

    public static String getAdminUrl() {
        return prop.getProperty(getEnv() + ".admin.url");
    }

    public static String getAdminEmail() {
        return prop.getProperty(getEnv() + ".admin.email");
    }

    public static String getAdminPassword() {
        return prop.getProperty(getEnv() + ".admin.password");
    }

    public static String getBrowser() {
        return System.getProperty("browser", prop.getProperty("browser", "chrome"));
    }

     public static String getStudentMobile() {
       return prop.getProperty(getEnv() + ".student.mobile");
    }

    public static String getStudentPassword() {
        return prop.getProperty(getEnv() + ".student.password");
    }
}
