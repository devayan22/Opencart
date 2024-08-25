package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("***** Starting TC_001_AccountRegistrationTest ******* ");
		
		try 
		{
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		
		hp.clickRegister();
        logger.info("Clicked on Register link");
		
		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);

		logger.info("Providing Customer details.");
		// regPage.setFirstName("Devayan");
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString() + "@gmail.com");
		regPage.setTelephone(randomNumber());

		/*
		 * randomAlphaNumber() will generate random values But password & confirm
		 * password has to be same so we first store it in a variable & then send it.
		 * Instead of directly call & pass. 
		 */

		String password = randomAlphaNumber();

		regPage.setPassword(password);
		regPage.setConfirmPassword(password);
		regPage.setPrivacyPolicy();
		regPage.clickContinue();

		logger.info("Validating expected message");
		String confmsg = regPage.getConfirmationMsg();

		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		
		// Validations
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		{
		  Assert.fail();
		}
		
		logger.info("Finished Execution");
	}

}
