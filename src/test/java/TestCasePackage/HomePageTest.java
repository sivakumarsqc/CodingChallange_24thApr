package TestCasePackage;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import BasePackage.DriverClass;
import PagePackage.FlightListPage;
import PagePackage.HomePage;
import UtilityPackage.Log;

/*
 * Created by - SIVAKUMAR.V
 *
 * This Class (HomePageTest) is created to Cover all the Test Cases for Home Page
 */

public class HomePageTest extends DriverClass {
	
	public static DriverClass base = new DriverClass();
	
	public HomePageTest(){
		super();
	}
	
	//This method is to Initialize Driver. It will execute before each Test case.
	@BeforeMethod (enabled = true)
	public static void driverInitialization(){
		driverSetup();
		new HomePage();
	}
	
	//Title Verification for Home Page
	@Test (enabled = false)
	public static void homeTitleVerification(){
		try{
			String title = driver.getTitle();
			Log.info(title);
			Assert.assertTrue(title.contains(pro.getProperty("HomePageTitle")));
					
		} catch (Exception e){
			Log.fatal(e);
		}
		Log.info("Title Verification Passed");
	}
	
	//Logo Verification in Home Page
	@Test (enabled = false)
	public static void logoVerification(){
		Assert.assertTrue(HomePage.logoVerification());
		Log.info("LogoVerification Passed");
	}
	
	//This method contains cases for End to End
	@Test (enabled = true)
	public static void endToEndCases() throws InterruptedException{
		
		//Cases will be do in HomePage
		HomePage.clickOnFlightIcon();
		HomePage.clickOnRoundTripRadioBtn();
		HomePage.enterSourceStation();
		HomePage.enterDestinationStation();
		driver.manage().deleteAllCookies();
		HomePage.searchFlights();
		//driver.manage().deleteAllCookies();
		
		//Cases will be do in Flight list. This part list all the Flights after selecting 'Non Stop' and 'One Stop'. 
		FlightListPage.getDepatureFlights();
		FlightListPage.getReturnFlights();
		FlightListPage.selectNonStop();
		FlightListPage.getDepatureFlights();
		FlightListPage.getReturnFlights();
		FlightListPage.selectNonStop();
		FlightListPage.selectOneStop();
		FlightListPage.getDepatureFlights();
		FlightListPage.getReturnFlights();
		FlightListPage.selectOneStop();
		
		//Assertion Part. It will select Departure and Return Flight 5 times randomly and verify the fares.
		for (int iteration = 1;iteration<=5;iteration++){
		String depFlyPrice = FlightListPage.selectRandomDepatureFlight();
		String retFlyPrice = FlightListPage.selectRandomReturnFlight();
		String depFlyPriceFromFooter = FlightListPage.depatureFlyPriceFromFooter();
		String retFlyPriceFromFooter = FlightListPage.retrunFlyPriceFromFooter();
		String totalPrice = FlightListPage.totalPriceFromFooter();
		
		//Assertion for both Departure and Return Flight Fares(String Comparison).
		Assert.assertEquals(depFlyPrice, depFlyPriceFromFooter);
		Assert.assertEquals(retFlyPrice, retFlyPriceFromFooter);
		System.out.println("Assertion getting passed for both Departure and Return flight fare comparison in Iteration "+iteration);
		Log.info("Assertion getting passed for both Departure and Return flight fare comparison in Iteration "+iteration);
		
		//Assertion for Total price (integer Comparison) got from the Footer. Here reduced Discounted Price if any.
		int discountedFareIfAny = FlightListPage.getIfAnyDiscountAvailable();
		int totalPriceCalculated = FlightListPage.convertStringPriceToInt(depFlyPrice)+FlightListPage.convertStringPriceToInt(retFlyPrice)-discountedFareIfAny;
		int totalPriceFromFooter = FlightListPage.convertStringPriceToInt(totalPrice);
		Assert.assertEquals(totalPriceCalculated, totalPriceFromFooter);
		System.out.println("Assertion getting passed for Toral Fare comparison in Iteration "+iteration+". Discounted Amount is Rs. "+discountedFareIfAny);
		Log.info("Assertion getting passed for Toral Fare comparison in Iteration "+iteration+". Discounted Amount is Rs. "+discountedFareIfAny);
		System.out.println("********************Iteration "+iteration+" Completed *******************");
		}
	}
	
	//This method is to close all the Drivers. It will execute after each test case.
	@AfterMethod (enabled = false)
	public static void tearDown(){
		driverTeardown(); //This method is created in Driver Class
		Log.info("All the Drivers are closed and Cleared");
	}
}
