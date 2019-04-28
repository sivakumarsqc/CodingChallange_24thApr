package BasePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import UtilityPackage.Log;

/*
 Created by SIVAKUMAR.V
 
 This class (DriverClass) is created for Initializing WebDriver, Property file and Log file.
 
 WebDriver will be Initialized based on OS and Browser.
 
 */

public class DriverClass {
	
	public static WebDriver driver;
	public static Properties pro;
	public static File propFile = new File(".//Config.Properties");
	
	//This Constructor is used to initialize Property file and Log file.
	public DriverClass(){
		try{
		DOMConfigurator.configure("log4j.xml"); //Logs will be written in Logs folder and get the details from this xml.
		FileInputStream FIS = new FileInputStream(propFile);
		pro = new Properties();
		pro.load(FIS);
		
		}catch(IOException e1){
			Log.fatal("IO Exception Occured " +e1);
			e1.printStackTrace();
		}
	}
	
	//This method is to Initialize the Driver as per the Browser and OS.
	public static WebDriver driverSetup(){
		String browser = pro.getProperty("BROWSER");
		String OS = System.getProperty("os.name");
		System.out.println("The Test is going to execute in "+OS+" Machine using "+browser+" Browser");
		Log.info("The Test is going to execute in "+OS+" Machine using "+browser+" Browser");
		if (browser.equalsIgnoreCase("CHROME") & OS.contains("Windows")){
			System.setProperty("webdriver.chrome.driver", pro.getProperty("CHROME_EXE_WINDOWS"));
			ChromeOptions option = new ChromeOptions();
			//option.addArguments("--incognito");
			option.addArguments("disable-infobars");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, option);
			Log.debug("Initialized CHROME Browser");
			driver=new ChromeDriver(option);
			
		}
		else if (browser.equalsIgnoreCase("FF") & OS.contains("Windows")){
			System.setProperty("webdriver.gecko.driver", pro.getProperty("GECKO_EXE_WINDOWS"));
			Log.debug("Initialized FireFox Browser");
			driver=new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("IE") & OS.contains("Windows")){
			System.setProperty("webdriver.ie.driver", pro.getProperty("IE_EXE_WINDOWS"));
			Log.debug("Initialized IE Browser");
			driver=new InternetExplorerDriver();
		}
		else if (browser.equalsIgnoreCase("CHROME") & OS.contains("Mac")){
			System.setProperty("webdriver.chrome.driver", pro.getProperty("CHROME_EXE_MAC"));
			ChromeOptions option = new ChromeOptions();
			option.addArguments("disable-infobars");
			Log.debug("Initialized CHROME Browser");
			driver=new ChromeDriver(option);
		}
		else {
			
			Log.error("Unsupported Browser Provided in Property File... Test Going to End...");
			driver=null;
			System.exit(0);
		}
		
		//URL will get from the Configuration file then will launch the URL.
		if (driver !=null){
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.get(pro.getProperty("URL"));
			Log.info("Launched URL - "+pro.getProperty("URL")); 
		}
		
		return driver;
	}
	
	//This is Tear down method. It will close all the existing browsers which are opened by this script.
	public static void driverTeardown(){
		Log.info("Driver Teardown method going to Execute");
		Set<String> allWindows = driver.getWindowHandles();
		int iSize = allWindows.size();
		Log.info(iSize+" drivers are available, all the drivers will be closed");
		if (iSize>1){
			driver.quit();
		}
		else {
			driver.close();
		}
		Log.debug("Teardown Done");
	}

}
