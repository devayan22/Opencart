package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*

Data is valid -> Login Success -> Test Pass - Logout 
Data is valid -> Login Failed -> Test Fail

Data is Invalid -> Login Success - Test Fail - Logout
Data is Invalid -> Login Failed - Test Pass

*/


public class TC_003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")
	public void verify_LoginDDT(String email, String pwd, String exp) {
		
		
		logger.info("**** Starting TC_003_LoginDDT **** ");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage myacc= new MyAccountPage(driver); 
		boolean targetPage = myacc.isMyAccountPageExists();
		
		//Validations
		
		if(exp.equalsIgnoreCase("Valid")) 
		{
			if(targetPage==true) 
			{
			  myacc.clickLogout();
		      Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid")) 
		{
			if(targetPage==true) 
			{
			  myacc.clickLogout();
		      Assert.assertTrue(false);
			}
			else 
			{
			  Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		logger.info("**** Finished TC_003_LoginDDT **** ");
		
		
		
		
	} 
	
	
	
	
	
}
