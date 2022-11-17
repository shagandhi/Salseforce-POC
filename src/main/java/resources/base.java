package resources;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

public class base {

	public  static WebDriver driver;
	public static Properties prop;
		 
	public  static WebDriver initializeDriver() throws IOException
	{

		prop= new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");

		prop.load(fis);
		String browserName=prop.getProperty("browser");
		System.out.println(browserName);

		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("test-type");
            options.addArguments("window-size=1920,1080");
            options.addArguments("--disable-web-security");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--disable-notifications");
            //capabilities.setCapability("chrome.binary","./src//lib//chromedriver");
            //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
         
			driver= new ChromeDriver(options);
			//execute in chrome driver
		}
		else if (browserName.equals("firefox"))
		{
			driver= new FirefoxDriver();
			//firefox code
		}
		else if (browserName.equals("IE"))
		{
			//	IE code
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}

	public void getScreenshot(String result) throws IOException
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C://test//"+result+"screenshot.png"));

	}

	 // This method handles the excel - opens it and reads the data from the
    // respective cells using a for-loop & returns it in the form of a string array
    public String[][] getExcelData(String fileName, String sheetName) throws IOException {
        String[][] data = null;
        try {
             
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(0);
            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRows - 1][noOfCols];
 
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);
                    data[i - 1][j] = cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            System.out.println("The exception is: " + e.getMessage());
        }
        return data;
    }
    
    public static void checkPageIsReady() {

    	  JavascriptExecutor js = (JavascriptExecutor) driver;

    	  // Initially bellow given if condition will check ready state of page.
    	  if (js.executeScript("return document.readyState").toString().equals("complete")) {
    	   System.out.println("Page Is loaded.");
    	   return;
    	  }

    	  // This loop will iterate for 25 times to check If page Is ready after
    	  // every 1 second.
    	  // If the page loaded successfully, it will terminate the for loop
    	  for (int i = 0; i < 25; i++) {
    	   try {
    	    Thread.sleep(5000);
    	   } catch (InterruptedException e) {
    	   }

    	   // To check page ready state.
    	   if (js.executeScript("return document.readyState").toString().equals("complete")) {
    	    break;
    	   }
    	  }
    	 } 
    public static boolean isClickable(WebElement webe)      
    {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(webe));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public static boolean switchToWindow(int n)      
    {
        try
        {
        	 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        	    driver.switchTo().window(tabs2.get(n));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public static boolean closeTheWindow(int n)      
    {
        try
        {
        	 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        	 driver.switchTo().window(tabs2.get(n));
        	    driver.close();
        	    driver.switchTo().window(tabs2.get(0));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static void scrollToButtonOfPage() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	try {
    	    long lastHeight=((Number)js.executeScript("return document.body.scrollHeight")).longValue();
    	    while (true) {
    	        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    	        Thread.sleep(2000);

    	        long newHeight = ((Number)js.executeScript("return document.body.scrollHeight")).longValue();
    	        if (newHeight == lastHeight) {
    	            break;
    	        }
    	        lastHeight = newHeight;
    	    }
    	} catch (InterruptedException e) {
    	    e.printStackTrace();
    	}
    }
	
    public static void scrollToButtonOfPageUsingRobot() {
    	Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			//robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}

