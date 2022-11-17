package salesForce;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;



public class HomePage extends base{
		
	LoginPage loginPage;
	LandingPage landingPage;
	
	 public static Logger log =LogManager.getLogger(base.class.getName());
	 
	@BeforeClass
	public void setUp() throws IOException
	{
		 
		 driver = initializeDriver();
		 loginPage=new LoginPage(driver);
		 landingPage = new LandingPage(driver);

	}
	
	
	@Test(dataProvider="loginData",priority=0)
	public void loginToSalesForce(String Email,String Password) throws IOException, InterruptedException
	{
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		loginPage.getLogin().click();
		loginPage.getLinkedInIcon().click();
		loginPage.getEmail().sendKeys(Email);
		loginPage.getPassword().sendKeys(Password);
		loginPage.getSignInBtn().click();
	
		}

	@Test(priority=1)
	public void launchPlayGround() throws IOException, InterruptedException
	{
		String launchPlayground = "https://trailhead.salesforce.com/launch_org/10185101";
		driver.navigate().to(launchPlayground);;
		//Wait for the new tab to finish loading content
		driver.getTitle().contains("Home | SalesForce");
		
		
		}

	@Test(priority=2)
	public void navigateToSales() throws IOException, InterruptedException
	{
		landingPage.getAppLauncher().click();
		landingPage.getSearchInput().sendKeys("Sales");
		 for (int i =0;i<landingPage.getOptions().size();++i) {
			 String elementText = landingPage.getOptions().get(i).getText(); 
	           
	            if(elementText.contentEquals("Sales")) {
	            	System.out.println(elementText);
	            	landingPage.getOptions().get(i).click();
	            	break;
	            }
	        }
				
	}
	
	  @AfterTest
	  public void tearDown() {
	        driver.quit();
	    }

	
		/*
		 * @DataProvider public Object[][] getData() { return new Object[][]
		 * {{"automationsalesforce1@gmail.com", "Test@123" }}; }
		 */
	  @DataProvider(name = "loginData")
	    public Object[][] loginDataProvider() throws IOException {
	        // We are creating an object from the excel sheet data by calling a method that
	        // reads data from the excel stored locally in our system
	        Object[][] arrObj = getExcelData(
	        		System.getProperty("user.dir")+"\\TestData\\Data.xlsx",
	                "LoginData");
	        return arrObj;
	    }
}
