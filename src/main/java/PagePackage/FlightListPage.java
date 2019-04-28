package PagePackage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import BasePackage.DriverClass;
import UtilityPackage.ApplicationUtill;
import UtilityPackage.Log;
import UtilityPackage.Utility;

/*
 * Created by - SIVAKUMAR.V
 * This Class (FlightListPage) contains all the required Elements and corresponding actions from the Page 
 */

public class FlightListPage extends DriverClass {
	
	public FlightListPage(){
		super();
	}
	//xpath declared as String
	public static String strDepatureFlight ="//div[@id='ow_domrt-jrny']//div[@class='fli-list splitVw-listing']";
	public static String strFlightName = "//span[@class='font12 inlineB append_bottom7 insertSep']";
	public static String strReturnFlight ="//div[@id='rt-domrt-jrny']//div[@class='fli-list splitVw-listing']";
	public static String depatFlightRadioBtn1st = "//div[@id='ow_domrt-jrny']//div[@class='fli-list splitVw-listing'][";
	public static String depatFlightRadioBtn2nd = "]//span[@class='splitVw-outer append_right9']";
	public static String returnFlightRadioBtn1st = "//div[@id='rt-domrt-jrny']//div[@class='fli-list splitVw-listing'][";
	
	//xpath defined.
	public static By allFlightsElement = By.xpath(strDepatureFlight);
	public static By allReturnFlyElement = By.xpath(strReturnFlight);
	public static By nonStopCheckBox = By.xpath("//label[@for='filter_stop0']//span[@class='check']"); 
	public static By oneStopCheckBox = By.xpath("//label[@for='filter_stop1']//span[@class='check']");
	public static By departureFlyPriceElem = By.xpath("//div[@class='splitVw-footer-left ']//p[@class='actual-price']");
	public static By returnFlyPriceElem = By.xpath("//div[@class='splitVw-footer-right ']//p[@class='actual-price']");
	public static By totalPriceElem = By.xpath("//span[@class='splitVw-total-fare']");
	public static By discountPrice = By.xpath("//div[@class='footer-fare']//p[@class='disc-applied']//span[2]");
	
	public static WebElement targetDepartureFlight;
	public static WebElement targetReturnFlight;
	
	//This method to get list of Departure Flight and print in Console and also in Log file.
	public static void getDepatureFlights() {
		Log.info("Get Departure Flight Method");
		ApplicationUtill.scrollToEnd();		//The page is loading dynamically. So scroll down to end of the page to load all the flights.	
		List <WebElement> allDepFlight = driver.findElements(allFlightsElement);
		int numOfFlights = allDepFlight.size();
		System.out.println("***********************************************************************");
		System.out.println("Number of Depature Flights : "+numOfFlights);
		System.out.println("***********************************************************************");
		Log.info("*************************************************************************");
		Log.info("Number of Depature Flights : "+numOfFlights);
		Log.info("*************************************************************************");
		for (int j=1;j<=numOfFlights;j++){
			String flightName = driver.findElement(By.xpath(strDepatureFlight+"["+j+"]"+strFlightName)).getText();
			System.out.println(flightName);
			Log.info(flightName);
		}
	}
	
	//This method to get list of Return Flight and print in Console and also in Log file.
	public static void getReturnFlights() {
		Log.info("GetReturnFlight method");
		List <WebElement> allReturnFlight = driver.findElements(allReturnFlyElement);
		int numOfFlights = allReturnFlight.size();
		System.out.println("***********************************************************************");
		System.out.println("Number of Return Flights : "+numOfFlights);
		System.out.println("***********************************************************************");
		Log.info("*************************************************************************");
		Log.info("Number of Return Flights : "+numOfFlights);
		Log.info("*************************************************************************");
		for (int j=1;j<=numOfFlights;j++){
			String flightName = driver.findElement(By.xpath(strReturnFlight+"["+j+"]"+strFlightName)).getText();
			System.out.println(flightName);
			Log.info(flightName);
		}
	}
	
	//This method is to Select/Unselect NonStop Filter
	public static void  selectNonStop(){
		Log.info("SelectNonStop method");
		ApplicationUtill.clickCtrlHome();
		WebElement nonStop = driver.findElement(nonStopCheckBox);
		ApplicationUtill.clickByJavaScript(nonStop);
	}
	
	//This method is to Select/Unselect OneStop Filter
	public static void  selectOneStop(){
		Log.info("SelectOneStop method");
		ApplicationUtill.clickCtrlHome();
		WebElement oneStop = driver.findElement(oneStopCheckBox);
		ApplicationUtill.clickByJavaScript(oneStop);
	}
	
	//This method will select Random Departure flight and get the Ticket Fair
	public static String selectRandomDepatureFlight(){
		Log.info("selectRandomDepatureFlight method");
		int random = Utility.getRandomNumbers(10);
		targetDepartureFlight = driver.findElement(By.xpath(depatFlightRadioBtn1st + random + depatFlightRadioBtn2nd));
		System.out.println("Randomly Selected Departure Flight Number "+random);
		Log.info("Randomly Selected Departure Flight Number "+random);
		ApplicationUtill.clickByJavaScript(targetDepartureFlight);
		String actPrice = driver.findElement(By.xpath(depatFlightRadioBtn1st + random +"]//p[@class='actual-price']")).getText();
		System.out.println("Departure Flight Ticket Fair :"+actPrice);
		Log.info("Departure Flight Ticket Fair :"+actPrice);
		return actPrice;
	}
	
	//This method will select Random Return flight and get the Ticket Fair
	public static String selectRandomReturnFlight(){
		Log.info("selectRandomReturnFlight method");
		int random = Utility.getRandomNumbers(10);
		targetReturnFlight = driver.findElement(By.xpath(returnFlightRadioBtn1st + random + depatFlightRadioBtn2nd));
		System.out.println("Randomly Selected Return Flight Number "+random);
		Log.info("Randomly Selected Return Flight Number "+random);
		ApplicationUtill.clickByJavaScript(targetReturnFlight);
		String actPrice = driver.findElement(By.xpath(returnFlightRadioBtn1st + random +"]//p[@class='actual-price']")).getText();
		System.out.println("Return Flight Ticket Fair :"+actPrice);
		Log.info("Return Flight Ticket Fair :"+actPrice);
		return actPrice;
	}
	
	//To get Price in Integer, converted the String to Integer
	public static int convertStringPriceToInt(String depStrPrice){
		String price = Utility.stringReplacement(depStrPrice,",","");
		price = Utility.stringReplacement(price,"Rs ","");
		price = price.replaceAll("Rs ", "");
		return Utility.convertStrToInt(price);
	}
	
	//To get the Departure Flight Fare from Footer
	public static String depatureFlyPriceFromFooter(){
		return driver.findElement(departureFlyPriceElem).getText();
	}
	
	//To get the Return Flight Fare from Footer
	public static String retrunFlyPriceFromFooter(){
		return driver.findElement(returnFlyPriceElem).getText();
	}
	
	//To get Discount Amount as Integer if any. This amount should be reduced from the total fare.
	public static int getIfAnyDiscountAvailable(){
		int intDiscountPrice = 0;
		if ((driver.findElements(discountPrice)).size() !=0){
			String discountFare = driver.findElement(discountPrice).getText();
			String[] discountPriceArray = discountFare.split(" ");
			intDiscountPrice=Integer.parseInt(discountPriceArray[1]);
			System.out.println("Discounted Price :"+intDiscountPrice);
		}
		return intDiscountPrice;
	}
	
	//To get the total fare from the footer.
	public static String totalPriceFromFooter(){
		return driver.findElement(totalPriceElem).getText();
	}
	
	

}
