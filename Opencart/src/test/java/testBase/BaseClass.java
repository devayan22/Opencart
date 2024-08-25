package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;




//Re-Usable Methods present here. We can add it under TestBase package if u want.

public class BaseClass {

	public static WebDriver driver;
	public Properties p;
	public Logger logger;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String  br) throws IOException 
	{
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());		
		
		//OS
		if(p.getProperty("execution_Env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac")){
				cap.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("Linux")){
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No Matching os");
				return;
			}
		
		//BROWSER
			
		switch(br.toLowerCase())
		{ 
		case "chrome" : cap.setBrowserName("chrome"); break;
		case "firefox" :cap.setBrowserName("firefox"); break; 
		case "edge" :  cap.setBrowserName("MicrosoftEdge"); break; 
		default : System.out.println("Invalid browser name..."); return; 
		}
		
		 //driver = new RemoteWebDriver(new URL("http://192.168.0.132:4444"),cap);
		 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		 //http://localhost:4444/wd/hub
   }
		
	if(p.getProperty("execution_Env").equalsIgnoreCase("local"))	
	{
		switch(br.toLowerCase())
		{ 
		case "chrome" : driver=new ChromeDriver(); break;
		case "firefox" : driver=new FirefoxDriver(); break; 
		case "edge" : driver=new EdgeDriver(); break; 
		default : System.out.println("Invalid browser name..."); return; 
		}
	}	
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	

		driver.get("https://tutorialsninja.com/demo/");
		//driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
			
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() 
	{
	 	driver.quit();
	}
	
	//For dynamic names generation. 
	public String randomString() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	//For dynamic telephoneNumbers generation.
	public String randomNumber() 
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	//For Dynamic AphaNumeric Passwords generation.
	public String randomAlphaNumber() 
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return (generatedString+"@"+generatedNumber);
	}
	
	// Capture Screenshots
	
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + " "+ timeStamp;
	    File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
	
	
}
