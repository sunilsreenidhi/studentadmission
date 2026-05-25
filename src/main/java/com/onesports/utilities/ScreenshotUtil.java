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

                      String relativePath =
                "screenshots/"
                + testName + "_"
                + System.currentTimeMillis()
                + ".png";

        String absolutePath =
                System.getProperty("user.dir")
                + File.separator
                + "reports"
                + File.separator
                + relativePath;

        File dest = new File(absolutePath);

        dest.getParentFile().mkdirs();

        FileUtils.copyFile(src, dest);

        return relativePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

    

