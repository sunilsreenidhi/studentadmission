package com.onesports.listeners;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String path = System.getProperty("user.dir")
                    + "/reports/screenshots/"
                    + testName + "_" + System.currentTimeMillis() + ".png";

            FileUtils.copyFile(src, new File(path));
            return path;

        } catch (Exception e) {
              e.printStackTrace();
            return null;
        }
    }
}

    

