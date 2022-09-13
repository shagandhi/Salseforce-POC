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
	By addNewContactButton = By.cssSelector(
			"div.listWrapper:nth-child(1) article  lst-list-view-manager-header:nth-child(1) > div.slds-page-header div.actionsWrapper div[title*='New']");
	By saluteDDL = By.xpath("//button[@id='combobox-button-548']");
	By optionList = By.cssSelector("lightning-base-combobox-item span.slds-truncate");
	By lastName = By.xpath("//input[@name='lastName']");
	By contactNameInTable = By
			.xpath("table[data-aura-rendered-by='8439:0'] tbody th[scope*='row'] span:nth-child(1) a");

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

	public WebElement getAddNewContactButton() {
		return driver.findElement(addNewContactButton);
	}

	public WebElement getSaluteDDL() {
		return driver.findElement(saluteDDL);
	}

	public WebElement getLastName() {
		return driver.findElement(lastName);
	}

	public List<WebElement> getOptionList() {
		return driver.findElements(optionList);
	}

	public List<WebElement> getContactNameInTable() {
		return driver.findElements(contactNameInTable);
	}

}
