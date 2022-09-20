package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage {

	public WebDriver driver;

	By newBtn = By.cssSelector("div[title='New']");
	By accountOwner = By.xpath("//span[@class='displayLabel']/slot");
	By accountName = By.xpath("//input[@name='Name']");
	By saveBtn = By.xpath("//button[@name='SaveEdit']");
	By accountNameInTable = By.xpath("//tbody/tr/th[1]/span[1]/a");

	By contactsection = By.xpath("//span[@title='Contacts']");
	By addNewContactButton = By.xpath("//ul[contains(@class,'branding-actions')]/li[1]/a");
	By saluteDDL = By.xpath("//button[@id='combobox-button-548']");
	By lastName = By.xpath("//input[@name='lastName']");
	By contactNameInTable = By.cssSelector("table[data-aura-rendered-by] tbody th[scope*='row'] span:nth-child(1) a");
	
	By opportunitySection = By.xpath("//span[@title='Opportunities']");
	By addNewOpportunityButton = By.xpath("//ul[contains(@class,'branding-actions')]/li[1]/a[@title='New']");
	By opportunityNameInTable = By.cssSelector("table[data-aura-rendered-by] tbody tr th[scope*='row'] span:nth-child(1) a");
	By opportunityName = By.xpath("//input[@name='Name']");
	By closeDate = By.xpath("//input[@name='CloseDate']");
	By stageDDL = By.xpath("//button[@aria-label='Stage, --None--']");
	By optionProspecting = By.xpath("//span[text()='Prospecting'][@title='Prospecting']");
	
	By caseSection = By.xpath("//span[@title='Cases']");
	By addNewCaseButton = By.xpath("//ul[contains(@class,'branding-actions')]/li[1]/a[@title='New']");
	By caseNameInTable = By.cssSelector("table[data-aura-rendered-by] tbody tr th[scope*='row'] span:nth-child(1) a");
	By statusDDL = By.xpath("//button[@aria-label='Status, New']");
	By caseOriginDDL = By.cssSelector(" div.forcePageBlockSectionRow:nth-child(3) div:nth-child(1) > a.select");
	By optionWorking = By.xpath("//span[text()='Working'][@title='Working']");
	By optionPhone = By.xpath("//a[text()='Phone'][@title='Phone']");
	By saveCaseBtn = By.xpath("//button[@title='Save']");

	public AccountPage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;

	}

	public WebElement getNewBtn() {
		return driver.findElement(newBtn);
	}

	public WebElement getAccountOwner() {
		return driver.findElement(accountOwner);
	}

	public WebElement getAccountName() {
		return driver.findElement(accountName);
	}

	public WebElement getSaveBtn() {
		return driver.findElement(saveBtn);
	}

	public List<WebElement> getAccountNameInTable() {
		return driver.findElements(accountNameInTable);
	}

	public WebElement getContactsection() {
		return driver.findElement(contactsection);
	}

	public List<WebElement> getAddNewContactButton() {
		return driver.findElements(addNewContactButton);
	}

	public WebElement getSaluteDDL() {
		return driver.findElement(saluteDDL);
	}

	public WebElement getLastName() {
		return driver.findElement(lastName);
	}

	public WebElement getOptionProspecting() {
		return driver.findElement(optionProspecting);
	}

	public List<WebElement> getContactNameInTable() {
		return driver.findElements(contactNameInTable);
	}
	
	public WebElement getOpportunitySection() {
		return driver.findElement(opportunitySection);
	}

	public List<WebElement> getAddNewOpportunityButton() {
		return driver.findElements(addNewOpportunityButton);
	}
	
	public List<WebElement> getOpportunityNameInTable() {
		return driver.findElements(opportunityNameInTable);
	}
	
	public WebElement getOpportunityName() {
		return driver.findElement(opportunityName);
	}
	
	public WebElement getCloseDate() {
		return driver.findElement(closeDate);
	}
	
	public WebElement getStageDDL() {
		return driver.findElement(stageDDL);
	}
	
	public WebElement getCaseSection() {
		return driver.findElement(caseSection);
	}
	
	public List<WebElement> getAddNewCaseButton() {
		return driver.findElements(addNewCaseButton);
	}
	
	public List<WebElement> getCaseNameInTable() {
		return driver.findElements(caseNameInTable);
	}
	
	public WebElement getStatusDDL() {
		return driver.findElement(statusDDL);
	}
	
	public WebElement getCaseOriginDDL() {
		return driver.findElement(caseOriginDDL);
	}
	
	public WebElement getOptionWorking() {
		return driver.findElement(optionWorking);
	}
	
	public WebElement getOptionPhone() {
		return driver.findElement(optionPhone);
	}
	
	public WebElement getSaveCaseBtn() {
		return driver.findElement(saveCaseBtn);
	}
}
