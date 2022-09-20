package salesForce;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		            break;
	            }
	        }
		
		}

		@Test(priority = 1)
		public void addContact() throws IOException, InterruptedException {
			checkPageIsReady();
			accountPage.getContactsection().click();	
			checkPageIsReady();
			System.out.println(accountPage.getAddNewContactButton().size());
		    for (int i = 0; i < accountPage.getAddNewContactButton().size(); ++i) {
		    	boolean clickable = isClickable(accountPage.getAddNewContactButton().get(i));
				String elementText = accountPage.getAddNewContactButton().get(i).getText();
				System.out.println(elementText);
				if ((clickable == true) && (elementText.equalsIgnoreCase("New"))) {
					accountPage.getAddNewContactButton().get(i).click();
					Thread.sleep(6000);
				}
			}
			
			accountPage.getLastName().sendKeys("Jake");
			accountPage.getSaveBtn().click();
			checkPageIsReady();
			// Verify whether contact is added or not
			for (int i = 0; i < accountPage.getContactNameInTable().size(); ++i) {
				boolean clickable = isClickable(accountPage.getContactNameInTable().get(i));
				String elementText = accountPage.getContactNameInTable().get(i).getText();
				System.out.println(elementText);
				if ((clickable == true) && (elementText.equalsIgnoreCase("Jake"))) {
					Assert.assertEquals(elementText, "Jake");
					accountPage.getContactNameInTable().get(i).click();
					break;
				}
			}
			 
		}
		
		@Test(priority = 2)
		public void addOpportunities() throws IOException, InterruptedException {
			checkPageIsReady();
			accountPage.getOpportunitySection().click();	
			Thread.sleep(6000);
			for (int i = 0; i < accountPage.getAddNewOpportunityButton().size(); ++i) {
		    	boolean clickable = isClickable(accountPage.getAddNewOpportunityButton().get(i));
				String elementText = accountPage.getAddNewOpportunityButton().get(i).getText();
				System.out.println(elementText);
				if ((clickable == true) && (elementText.equalsIgnoreCase("New"))) {
					accountPage.getAddNewOpportunityButton().get(i).click();
					Thread.sleep(6000);
				}
			}
			
			accountPage.getOpportunityName().sendKeys("Opportunity 1");
			accountPage.getCloseDate().sendKeys("9/14/2022");
			accountPage.getStageDDL().click();
			Thread.sleep(1000);
			accountPage.getOptionProspecting().click();
					
			accountPage.getSaveBtn().click();
			checkPageIsReady();
			// Verify whether contact is added or not
			for (int i = 0; i < accountPage.getOpportunityNameInTable().size(); ++i) {
				boolean clickable = isClickable(accountPage.getOpportunityNameInTable().get(i));
				String elementText = accountPage.getOpportunityNameInTable().get(i).getText();
				System.out.println(elementText);
				if ((clickable == true) && (elementText.equalsIgnoreCase("Opportunity 1"))) {
					Assert.assertEquals(elementText, "Opportunity 1");
					accountPage.getOpportunityNameInTable().get(i).click();
					break;
				}
			}
			 
		}
		
		@Test(priority = 3)
		public void addCases() throws IOException, InterruptedException {
			checkPageIsReady();
			accountPage.getCaseSection().click();	
			Thread.sleep(2000);
			for (int i = 0; i < accountPage.getAddNewCaseButton().size(); ++i) {
		    	boolean clickable = isClickable(accountPage.getAddNewCaseButton().get(i));
				String elementText = accountPage.getAddNewCaseButton().get(i).getText();
				System.out.println(elementText);
				if ((clickable == true) && (elementText.equalsIgnoreCase("New"))) {
					accountPage.getAddNewCaseButton().get(i).click();
					Thread.sleep(6000);
				}
			}
			
			accountPage.getStatusDDL().click();
			Thread.sleep(1000);
			accountPage.getOptionWorking().click();
			accountPage.getCaseOriginDDL().click();
			Thread.sleep(1000);
			accountPage.getOptionPhone().click();		
			accountPage.getSaveCaseBtn().click();
			checkPageIsReady();
			// Verify whether contact is added or not
			for (int i = 0; i < accountPage.getCaseNameInTable().size(); ++i) {
				boolean clickable = isClickable(accountPage.getCaseNameInTable().get(i));
				String elementText = accountPage.getCaseNameInTable().get(i).getText();
				System.out.println(elementText);
				if ((clickable == true) && (accountPage.getCaseNameInTable().get(i).isDisplayed())) {
					accountPage.getCaseNameInTable().get(i).click();
					break;
				}
			}
			 
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
