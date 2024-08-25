package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath = "//*[@id=\"column-right\"]/div/a[13]")
	WebElement lnkLogout;
	
	
	public boolean isMyAccountPageExists() 
	{
		try
		{
		return (msgHeading.isDisplayed());
		} 
		catch(Exception e) 
		{
		return false;
		}
	}
	
	public void clickLogout() throws InterruptedException 
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].scrollintoView()",lnkLogout);
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,3000)");
		lnkLogout.click();
		/*js.executeScript("window.scrollBy(0,-3000)");
		Thread.sleep(2000);*/
	}
	
	
	
}
