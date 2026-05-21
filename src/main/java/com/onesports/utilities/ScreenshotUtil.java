package com.onesports.utilities;

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

            File screenshotDir = new File(System.getProperty("user.dir")
                    + File.separator + "reports"
                    + File.separator + "screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String path = screenshotDir.getAbsolutePath()
                    + File.separator + testName + "_" + System.currentTimeMillis() + ".png";

            FileUtils.copyFile(src, new File(path));
            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

    

