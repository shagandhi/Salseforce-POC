package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base{
	
	 public static Logger log =LogManager.getLogger(base.class.getName());
	@BeforeTest
	public void initialize() throws IOException
	{
		 
		 driver =initializeDriver();

	}
	@Test(dataProvider="getData")
	
	public void basePageNavigation(String Username,String Password) throws IOException, InterruptedException
	{

		//one is inheritance

		// creating object to that class and invoke methods of it
		driver.get(prop.getProperty("url"));
		//driver.findElement(By.xpath("//*[@id='homepage']/div[4]/div[2]/div/div/div/span/div/div[7]/div/div/div[2])").click();
		/*LandingPage l=new LandingPage(driver);
		Thread.sleep(3000);
		l.getLogin().click();
		Thread.sleep(3000);*/
		//driver.findElement(By.css()
		LoginPage lp=new LoginPage(driver);
		lp.getEmail().sendKeys(Username);
		Thread.sleep(3000);
		lp.getPassword().sendKeys(Password);
		Thread.sleep(3000);

		
		
		lp.getLogin().click();
		Thread.sleep(3000);
		
		}

	@AfterTest
	public void teardown()
	{
		
		driver.close();
		driver=null;
		
	}

	
	@DataProvider
	public Object[][] getData()
	{
		// Row stands for how many different data types test should run
		//coloumn stands for how many values per each test
		
		// Array size is 2
		// 0,1
		Object[][] data=new Object[2][2];
		//0th row
		data[0][0]="ANJANIMAKER";
		data[0][1]="Maker@0710";
		
		//1st row
		data[1][0]="ANJANICHECKER";
		data[1][1]="Checker@0710";
		 
		
		return data;
		
		
	}
	
}
