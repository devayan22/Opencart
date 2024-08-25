package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	WebDriver driver;

	// Constructor for initiating WebDriver.
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	// PageObjects or WebElements for Declaration
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement InkMyAccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement InkRegister;
	
	@FindBy(linkText = "Login")
	WebElement InkLogin;
    
	// Action Methods for Utilisation of PageObjects created
	
	public void clickMyAccount()
	{
		InkMyAccount.click();
	}
	
	public void clickRegister()
	{
		InkRegister.click();
	}
	
	public void clickLogin()
	{
		InkLogin.click();
	}
}
