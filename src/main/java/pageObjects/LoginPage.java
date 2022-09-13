package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	
	public WebDriver driver;
	
	By email=By.xpath("//input[@id='username']");
	By password=By.xpath("//input[@id='password']");
	By login=By.xpath("//a[contains(text(),'Login')]");
	By linkedInIcon=By.xpath("//div[2]/div[1]/span[1]/div[3]/span[3]/div[1]/button[1]");
	By signInBtn = By.xpath("//button[contains(text(),'Sign in')]");
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		
	}

	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	
	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement getLogin()
	{
		return driver.findElement(login);
	}
	
	public WebElement getLinkedInIcon()
	{
		return driver.findElement(linkedInIcon);
	}
	
	public WebElement getSignInBtn()
	{
		return driver.findElement(signInBtn);
	}
	
	
}
