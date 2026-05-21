package com.onesports.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.onesports.listeners.TestListener;
import com.onesports.resources.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(TestListener.class)
public class BaseTest {

    // Thread-safe WebDriver per test thread
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setUp() {

        String browser = ConfigManager.getBrowser();
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
                
             case "firefox":
        WebDriverManager.firefoxdriver().setup();
        webDriver = new FirefoxDriver();
        break;
                
            default:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
        }

        driver.set(webDriver);
        getDriver().manage().window().maximize();
    }
    

  // @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();  // Prevent thread leak
        }
    }

    
}
