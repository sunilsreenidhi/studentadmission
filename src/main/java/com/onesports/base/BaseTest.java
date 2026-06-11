package com.onesports.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.onesports.listeners.TestListener;
import com.onesports.resources.ConfigManager;
import io.github.bonigarcia.wdm.WebDriverManager;


@Listeners(TestListener.class)
public class BaseTest {

       protected static final Logger logger =
            LogManager.getLogger(BaseTest.class);

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
                ChromeOptions options = new ChromeOptions();

if(System.getProperty("headless") != null) {
    options.addArguments("--headless=new");
    options.addArguments("--window-size=1920,1080");


}

options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");
options.addArguments("--remote-allow-origins=*");
options.addArguments("--disable-notifications");
options.addArguments("--disable-popup-blocking");
options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());

                webDriver = new ChromeDriver(options);
        }

        driver.set(webDriver);
     //   getDriver().manage().window().setSize(new Dimension(1920,1080));
     getDriver().manage().window().maximize();
           getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
                getDriver().manage().timeouts()
        .pageLoadTimeout(Duration.ofSeconds(60));
      //  getDriver().manage().window().maximize();
    }
    

 //  @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();  // Prevent thread leak
        }
    }

    
}
