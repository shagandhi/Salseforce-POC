package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

	
	public WebDriver driver;
	
	By appLauncher=By.xpath("//div[@class='slds-icon-waffle']");
	By searchInput=By.xpath("//input[@placeholder='Search apps and items...']");
	By options=By.xpath("//one-app-launcher-menu-item //p[@class='slds-truncate']");
	By accountMenu = By.xpath("//span[@class='slds-truncate'][normalize-space()='Accounts']");
			////one-app-nav-bar-item-root[6]/a[1]/span[1]");	
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		
	}




	public WebElement getAppLauncher()
	{
		return driver.findElement(appLauncher);
	}
	public WebElement getSearchInput()
	{
		return driver.findElement(searchInput);
	}
	public List<WebElement> getOptions()
	{
		return driver.findElements(options);
	}
	public WebElement getAccountMenu()
	{
		return driver.findElement(accountMenu);
	}
	
	
	
}
