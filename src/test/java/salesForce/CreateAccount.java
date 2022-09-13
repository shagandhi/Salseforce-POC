package salesForce;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.LandingPage;
import resources.base;

public class CreateAccount extends base {
	
	AccountPage accountPage;
	LandingPage landingPage;

	 public static Logger log =LogManager.getLogger(base.class.getName());
	 
	 
	@BeforeClass
	public void initialize() throws IOException
	{
		 accountPage=new AccountPage(driver);
		 landingPage = new LandingPage(driver);

	}
	
	@Test(dataProvider="accountData",priority=0)
	public void createAccount(String AccountName, String AccountOwner) throws IOException, InterruptedException
	{	
		Thread.sleep(6000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
	    executor.executeScript("arguments[0].click();", landingPage.getAccountMenu());
	    System.out.println("testet");
	    accountPage.getNewBtn().click();
		Assert.assertEquals(accountPage.getAccountOwner().getText(), AccountOwner);
		accountPage.getAccountName().sendKeys(AccountName);
		accountPage.getSaveBtn().click();
		Thread.sleep(6000);
		executor.executeScript("arguments[0].click();", landingPage.getAccountMenu());
		Thread.sleep(6000);
		for (int i =0;i<accountPage.getAccountNameInTable().size();++i) {
			 String elementText = accountPage.getAccountNameInTable().get(i).getText(); 
	            System.out.println(elementText);
	            if(elementText.equalsIgnoreCase(AccountName)) {
		            Assert.assertEquals(elementText, AccountName);
		            accountPage.getAccountNameInTable().get(i).click();
		            System.out.println("click on it");
		            break;
	            }
	        }
		
		//add Contact
		Thread.sleep(6000);
		
		}

	
	 @AfterTest
	  public void tearDown() {
	        driver.quit();
	    }

	
	 @DataProvider(name = "accountData")
	    public Object[][] accountDataProvider() throws IOException {
	        // We are creating an object from the excel sheet data by calling a method that
	        // reads data from the excel stored locally in our system
	        Object[][] arrObj = getExcelData(
	        		System.getProperty("user.dir")+"\\TestData\\Data.xlsx",
	                "AccountData");
	        return arrObj;
	    }
	 
	 
		/*
		 * @DataProvider(name = "contactData") public Object[][] contactDataProvider()
		 * throws IOException { // We are creating an object from the excel sheet data
		 * by calling a method that // reads data from the excel stored locally in our
		 * system Object[][] arrObj = getExcelData(
		 * System.getProperty("user.dir")+"\\TestData\\Data.xlsx", "ContactData");
		 * return arrObj; }
		 */
}
