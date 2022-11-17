package salesForce;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
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
	
	@Test(dataProvider="accountData",priority = 0)
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
		    checkPageIsReady();
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
		
		@Test(dataProvider="accountData",priority = 2)
		public void addOpportunities(String AccountName, String AccountOwner) throws IOException, InterruptedException {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
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
				//switchToWindow(1);
				checkPageIsReady();				
				executor.executeScript("window.scrollBy(0,100)", "");
				
				accountPage.getOpportunitySection().click();	
				Thread.sleep(6000);
				for (int i = 0; i < accountPage.getAddNewOpportunityButton().size(); ++i) {
			    	boolean clickable = isClickable(accountPage.getAddNewOpportunityButton().get(i));
					String elementText = accountPage.getAddNewOpportunityButton().get(i).getText();
					System.out.println(elementText);
					if ((clickable == true) && (elementText.equalsIgnoreCase("New"))) {
						accountPage.getAddNewOpportunityButton().get(i).click();
					}
				}
				Thread.sleep(10000);
				accountPage.getOpportunityName().sendKeys("Opportunity 1");
				accountPage.getCloseDate().sendKeys("9/14/2023");
				executor.executeScript("window.scrollBy(0,450)", "");
				executor.executeScript("arguments[0].click();", accountPage.getStageDDL());
				accountPage.getOptionProspecting().click();
				accountPage.getSaveBtn().click();
				checkPageIsReady();
				/// Verify whether Opportunity is added or not
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
				//closeTheWindow(1);
		}
		
		@Test(priority = 3,dataProvider="accountData")
		public void addCases(String AccountName, String AccountOwner) throws IOException, InterruptedException {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
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
			//switchToWindow(1);
			checkPageIsReady();
			executor.executeScript("window.scrollBy(0,3200)", "");
			//scrollToButtonOfPageUsingRobot();
			checkPageIsReady();
			executor.executeScript("arguments[0].click();", accountPage.getCaseSection()); 
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
			// Verify whether Case is added or not
			for (int i = 0; i < accountPage.getCaseNameInTable().size(); ++i) {
				boolean clickable = isClickable(accountPage.getCaseNameInTable().get(i));
				String elementText = accountPage.getCaseNameInTable().get(i).getText();
				System.out.println(elementText);
				if ((clickable == true) && (accountPage.getCaseNameInTable().get(i).isDisplayed())) {
					accountPage.getCaseNameInTable().get(i).click();
					break;
				}
			}
			//closeTheWindow(1); 
		}
		
		@Test(priority = 4,dataProvider="accountData")
		public void addNotesAndAttachment(String AccountName, String AccountOwner) throws IOException, InterruptedException {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			Thread.sleep(6000);
			executor.executeScript("arguments[0].click();", landingPage.getAccountMenu());
			Thread.sleep(6000);
			for (int i =0;i<accountPage.getAccountNameInTable().size();++i) {
				String elementText = accountPage.getAccountNameInTable().get(i).getText(); 
				System.out.println(elementText);
				if(elementText.equalsIgnoreCase(AccountName)) {
					Assert.assertEquals(elementText, AccountName);
					Thread.sleep(6000);
					accountPage.getAccountNameInTable().get(i).click();
					break;
				}
			}
			executor.executeScript("window.scrollBy(0,3200)", "");
			checkPageIsReady();
			Thread.sleep(6000);
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			Thread.sleep(2000);
			accountPage.uploadFile().sendKeys("C:/Users/Skothandam/Desktop/SalesForce_Selenium/E2E/E2EProject/TestData/test.pdf");
			Thread.sleep(3000);
			if (accountPage.getDoneBtn().isDisplayed()) {
				actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				accountPage.getDoneBtn().click();	
			}
			Thread.sleep(6000);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			accountPage.getUploadedFilePresent().isDisplayed();
		}
	
		@Test(priority = 5,dataProvider="accountData")
		public void addPartners(String AccountName, String AccountOwner) throws IOException, InterruptedException {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			Thread.sleep(6000);
			executor.executeScript("arguments[0].click();", landingPage.getAccountMenu());
			Thread.sleep(6000);
			for (int i =0;i<accountPage.getAccountNameInTable().size();++i) {
				String elementText = accountPage.getAccountNameInTable().get(i).getText(); 
				System.out.println(elementText);
				if(elementText.equalsIgnoreCase(AccountName)) {
					Assert.assertEquals(elementText, AccountName);
					Thread.sleep(6000);
					accountPage.getAccountNameInTable().get(i).click();
					break;
				}
			}
			switchToWindow(1);
			checkPageIsReady();
			executor.executeScript("window.scrollBy(0,3200)", "");
			checkPageIsReady();
			Thread.sleep(2000);
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			accountPage.getPartnerSection().click();	
			Thread.sleep(1000);
			for (int i = 0; i < accountPage.getAddNewPartnerButton().size(); ++i) {
		    	boolean clickable = isClickable(accountPage.getAddNewPartnerButton().get(i));
				String elementText = accountPage.getAddNewPartnerButton().get(i).getText();
				System.out.println(elementText);
				if ((clickable == true) && (elementText.equalsIgnoreCase("New"))) {
					accountPage.getAddNewPartnerButton().get(i).click();
					Thread.sleep(6000);
				}
			}
			accountPage.getAccountInput().click();
			Thread.sleep(1000);
			for (int i = 0; i < accountPage.getAccountNameOptions().size(); ++i) {
				boolean clickable = isClickable(accountPage.getAccountNameOptions().get(i));
				System.out.println(clickable);
				String elementText = accountPage.getAccountNameOptions().get(i).getAttribute("title");
				System.out.println(elementText);
				if(elementText.equalsIgnoreCase("czxzc")) {
					System.out.println(accountPage.getAccountNameOptions().get(i).isDisplayed());
					accountPage.getNameOption().click();
					break;
				}
			}
			
			accountPage.getSaveCaseBtn().click();
			checkPageIsReady();
			// Verify whether Partner is added or not
			for (int i = 0; i < accountPage.getPartnerNameInTable().size(); ++i) {
				boolean clickable = isClickable(accountPage.getPartnerNameInTable().get(i));
				String elementText = accountPage.getPartnerNameInTable().get(i).getText();
				System.out.println(elementText);
				if(elementText.equalsIgnoreCase("czxzc")) {
					Assert.assertEquals(elementText, "czxzc");
					accountPage.getPartnerNameInTable().get(i).click();
					break;
				}
			}
			closeTheWindow(1);
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
	 
	 
}
