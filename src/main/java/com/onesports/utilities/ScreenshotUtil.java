package com.onesports.utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

   public static String takeScreenshot(WebDriver driver,
                                    String testName) {

    String relativePath = null;

    try {

        File src =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        String fileName =
                testName + "_"
                + System.currentTimeMillis()
                + ".png";

        relativePath =
                "screenshots/" + fileName;

        String absolutePath =
                System.getProperty("user.dir")
                + File.separator
                + "reports"
                + File.separator
                + relativePath;

        File dest = new File(absolutePath);

        dest.getParentFile().mkdirs();

        FileUtils.copyFile(src, dest);

        System.out.println("Screenshot saved at : "
                + absolutePath);

    } catch (Exception e) {

        e.printStackTrace();
    }

    return relativePath;
}
}

    

