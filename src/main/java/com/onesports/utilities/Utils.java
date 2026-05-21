package com.onesports.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Utils  {
public static	WebDriver  driver;
public static String ModuleName;
public static DateFormat df=new SimpleDateFormat("dd MMM YYYY");
public static Date d=new Date();
public static String time=df.format(d); 
public static  Sheet rsh;
public static  File file;
public static Sheet sheet;

static DataFormatter formatter= new DataFormatter();

//public static WebDriver getDriverInstance() {
//    return BaseTest.getDriver();  // Ensure consistent driver fetching
//}

private static final String[] FIRST_NAMES = {
            "Sunil", "Ravi", "Amit", "Karan", "Vikram", "Rahul",
            "Arjun", "Manish", "Vijay", "Suresh", "Anil", "Prakash", "Sanjay", "Rohit", "Varun", "Tejas",
"Shiva", "Gopi", "Harish", "Lokesh", "Mohan", "Nitin",
"Ganesh", "Yash", "Ritesh", "Sameer", "Tarun", "Ajay",
"Dinesh", "Abhishek", "Kishore", "Pavan", "Sharath", "Rakesh",
"Bharath", "Jayant", "Hemant", "Naveen", "Praveen", "Satish",
"Vinod", "Kailash", "Jagadeesh", "Siddharth", "Deepesh", "Chandran",
"Saurabh", "Ankit", "Jatin", "Umesh", "Ashwin", "Gautam",
"Lakshman", "Balaji", "Kamal", "Amar", "Dev", "Omkar",
"Rajiv", "Ashok"

    };

    private static final String[] LAST_NAMES = {
           "Reddy", "Sharma", "Verma", "Gupta", "Rao", "Patel",
"Naidu", "Iyer", "Shetty", "Mishra", "Yadav", "Singh",
"Chowdary", "Kumar", "Agarwal", "Bhat", "Gowda", "Nair",
"Jaiswal", "Pandey", "Tripathi", "Kulkarni", "Desai", "Mehta",
"Joshi", "Saxena", "Chauhan", "Bhatt", "Srinivas", "Gopal",
"Menon", "Rajput", "Pathak", "Mahajan", "Sethi", "Chatterjee",
"Mukherjee", "Banerjee", "Das", "Rastogi", "Khatri", "Pillai",
"	", "Sawant", "Gaikwad", "Tiwari", "Bansal", "Chhabra",
"Kapoor", "Malhotra"

    };

    private static final Random RANDOM = new Random();

    public static String generateFirstName() {
        return FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
    }

    public static String generateLastName() {
        return LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
    }

    // Combined helper
    public static String generateFullName() {
        return generateFirstName() + " " + generateLastName();
    }

public static WebElement waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutSeconds) {
    return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
        .until(ExpectedConditions.elementToBeClickable(element));
    
}

public static void waitForSpinnerToDisappear(WebDriver driver, By spinnerLocator, int timeoutSeconds) {
    new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
        .until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
}

	public static void scrollTOElement(WebDriver driver, WebElement el) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
	}
	
	public static void scrollToBottomOfPage(WebDriver driver) {
	    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public static void scrollToBottomOfPageFully(WebDriver driver)
        throws InterruptedException {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    // Wait until page fully loads
    wait.until(webDriver ->
            ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState")
                    .equals("complete"));

    JavascriptExecutor js = (JavascriptExecutor) driver;

    long lastHeight = ((Number) js.executeScript(
            "return document.body.scrollHeight")).longValue();

    while (true) {

        js.executeScript(
                "window.scrollTo(0, document.body.scrollHeight);");

        Thread.sleep(2000);

        long newHeight = ((Number) js.executeScript(
                "return document.body.scrollHeight")).longValue();

        if (newHeight == lastHeight) {
            break;
        }

        lastHeight = newHeight;
    }
}

	public static void scrollToTop(WebDriver driver)
	{

    JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("window.scrollTo({top: 0, behavior: 'smooth'});");
	}

	public static void verifyToastMessage(WebDriver driver, String expected, int timeoutSeconds) {
	    By toastLocator = By.cssSelector("div.toast-message[role='alert']");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	    String toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator)).getText().trim();
	    Assert.assertEquals(toast, expected, "Something Wrong in creating error");
	}

	
	public static void waitForElementToBeVisible(WebDriver driver, By webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(webElement));
	}

		
	public static void waitForElementToBeVisiblee(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
		
	public static void presenceOfElementLocated(WebDriver driver, By webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(webElement)).click();;
	}
	
	public static void waitForElementVisiblility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public static void waitForListElementVisiblility(WebDriver driver, List<WebElement> list) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
		
	}
	

	public static void waitforVisibilityOfAllElementsLocated(WebDriver driver, By element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	}
	
	public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
	    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds)).until(
	        webDriver -> ((JavascriptExecutor) webDriver)
	            .executeScript("return document.readyState").equals("complete"));
	}

	
	public static void waitForDropdownOption(WebDriver driver, By dropdownLocator, String optionText, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.textToBePresentInElementValue(dropdownLocator, optionText));
    }

	
    public static void waitForElementToBeClickable(WebDriver driver,WebElement element) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public static void clickElementusingJavascript(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }
    
//    public static void clickElementUsingJavascript(WebElement element) {
//        WebDriver driver = getDriverInstance(); // Fetch the correct driver
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].click();", element);
//    }
    public static String getrandomString() {
		return RandomStringUtils.randomNumeric(3);
    
    }
    
	public static String get_random_string() {
		return RandomStringUtils.random(10, true, false);
	}

	public static String get_random_alphaNumericString() {
		return RandomStringUtils.randomAlphanumeric(5); 
		

	}
	
	public static boolean isFileDownloaded(String downloadDir, String fileName) {
	        File dir = new File(downloadDir);
	        
	        if (!dir.exists() || !dir.isDirectory()) {
	            System.out.println("The specified download directory does not exist: " + downloadDir);
	            return false;  // Directory does not exist
	        }
	        File[] files = dir.listFiles();
	        
	        // Loop to check if the file exists in the directory
	        for (int i = 0; i < 10; i++) {
	            if (files != null) {
	                for (File file : files) {
	                    if (file.getName().contains(fileName)) {
	                        return true;
	                    }
	                }
	            }
	            // Sleep for a short while before re-checking
	            try {
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            files = dir.listFiles(); // Re-check the directory
	        }
	     //   System.out.println("File not found: " + fileName);
	        Assert.fail("File was not downloaded: " + fileName);
	        return false;
	    }


	
	public static long get_randomNumber() {
		 Random rand = new Random();
	        long nineDigits = 100000000L + (long) (rand.nextDouble() * 900000000L);	      
	        return Long.parseLong("9" + nineDigits);
	}
	
	 public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
	        FileInputStream fis = new FileInputStream(filePath);
	        XSSFWorkbook wb = new XSSFWorkbook(fis);
	        XSSFSheet sheet = wb.getSheet(sheetName);
	        DataFormatter formatter = new DataFormatter();  // For formatting cell values as strings

	        int rowCount = sheet.getPhysicalNumberOfRows();
	        XSSFRow row = sheet.getRow(0);
	        int colCount = row.getLastCellNum();
	        Object data[][] = new Object[rowCount - 1][colCount];

	        for (int i = 0; i < rowCount - 1; i++) {
	            row = sheet.getRow(i + 1);
	            for (int j = 0; j < colCount; j++) {
	                XSSFCell cell = row.getCell(j);
	                data[i][j] = formatter.formatCellValue(cell);
	            }
	        }
	        wb.close();  // Close the workbook to avoid memory leaks
	        return data;
	    }
	

    public static Sheet readExcelSheet(String fileLocation, int sheetNumber) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(fileLocation));
        Workbook workbook = WorkbookFactory.create(inputStream);
        return workbook.getSheetAt(sheetNumber);
    }

    public static String getExcelData(int position1, int position2, Sheet sheet) {
        Row row = sheet.getRow(position1);
        Cell cell = row.getCell(position2);
        return cell.toString();
    }
    
    public static void takeScreenshot(WebDriver driver, String methodName) {
        // Take screenshot and store it as a file format
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "C:\\Users\\sunil\\screenshots\\" + methodName + ".png"; // Using method name
        try {
            // Copy the screenshot to the specified file location
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            System.out.println("Screenshot saved at: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
    
    public static String getFormattedDate() {
        LocalDate currentDate = LocalDate.now();
        // Define a custom date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Corrected "YY" to "yyyy"
        // Format the current date
        return currentDate.format(formatter);
    }

	public static void uploadfilesUsingRobotClass(String filePath) {
		
		  try {
	            // Copy the file path to clipboard
	            StringSelection stringSelection = new StringSelection(filePath);
	            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	            // Use Robot class to paste the file path and press Enter
	            Robot robot = new Robot();
	            robot.keyPress(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_V);
	            robot.keyRelease(KeyEvent.VK_CONTROL);
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	        } catch (AWTException e) {
	            e.printStackTrace();
	        }
	
	
	}

	private void verifySuccessfulBookingEmail(WebDriver driver, String email) throws InterruptedException

	{
		driver.get("https://yopmail.com/");
		Thread.sleep(5000);
		WebElement emailInput = driver.findElement(By.id("login"));
		emailInput.sendKeys(email);
		driver.findElement(By.cssSelector("button[title='Check Inbox @yopmail.com']")).click();
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ifmail"));

		WebElement emailSubject = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("mail")));

		String emailverify = emailSubject.getText();
		Assert.assertTrue(emailverify.contains("Thank you for booking your adventure sports experience with us!"));

	}
	
	 public static String uniqueEmail() {
	        return "test" + System.currentTimeMillis() + "@yopmail.com";
	    }

	    public static String randomPhone() throws InterruptedException {
			Thread.sleep(500);
	        return "9" + (int)(Math.random() * 1_000_000_000);
	    }

	    public static String randomName() {
	        return "Staff_" + UUID.randomUUID().toString().substring(0, 3);
	    }
	    
	    

	
	
}
