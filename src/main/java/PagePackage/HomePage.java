package PagePackage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import BasePackage.DriverClass;
import UtilityPackage.ApplicationUtill;
import UtilityPackage.Utility;

/*
 * Created by - SIVAKUMAR.V
 * 
 * This class (HomePage) is to maintain all the Element and Actions of the Element for HomePage. 
 */
public class HomePage extends DriverClass {
	
	public static FlightListPage flightSearchPage;
	
	//Define all the required element using Page Factory.
	
	@FindBy (xpath="//a[@class='mmtLogo makeFlex']")
	public static WebElement logo;
	
	@FindBy (xpath="//a[@class='active makeFlex hrtlCenter column']//span[@class='chNavText darkGreyText']")
	public static WebElement flightIcon;
	
	@FindBy (xpath = "//div[@class='makeFlex']//ul[@class='fswTabs latoBlack greyText']//li[text()='Round Trip']") 
	public static WebElement roundTripRadioBtn;
	
	@FindBy (xpath ="//div[@class='hsw_autocomplePopup autoSuggestPlugin']//input[@placeholder='From']")
	public static WebElement stationFrom;
	
	@FindBy (xpath ="//div[@class='hsw_autocomplePopup autoSuggestPlugin']//input[@placeholder='To']")
	public static WebElement stationTo;
	
	@FindBy (id = "fromCity")
	public static WebElement fromCity;
	
	@FindBy (id = "toCity")
	public static WebElement toCity;
	
	@FindBy (xpath = "//span[@class='lbl_input latoBold appendBottom10' and text()='DEPARTURE']")
	public static WebElement depatureCal;
	
	@FindBy (xpath = "//span[@class='lbl_input latoBold appendBottom10' and text()='RETURN']")
	public static WebElement retrunCal;
	
	@FindBy (xpath = "//div[@class='DayPicker-NavBar']/span[@class='DayPicker-NavButton DayPicker-NavButton--next']")
	public static WebElement navigationNext;
	
	@FindBy (xpath = "//div[@class='DayPicker-Months']//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Week']")
	public static WebElement calWeekElement;
	
	@FindBy (xpath="//a[@class='primaryBtn font24 latoBlack widgetSearchBtn ']")
	public static WebElement searchBtn;
	
	public static String calWeek = "//div[@class='DayPicker-Months']//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Week']";
	
	public static String daySecondPart = "//div[starts-with(@class,'DayPicker-Day')]";// and @aria-disabled='false']";
	//public static String daySecondPart = "//div[starts-with(@class,'DayPicker-Day') and @aria-disabled='false']";
	
	public static String dayThirdPart = "//div[@class='dateInnerCell']/p[1]";
	
	//Constructor to define Parent Class and Page Factory.
	public HomePage() {
		super();
		PageFactory.initElements(driver,this);
		
	}
	
	//Method to MakeMyTrip Logo Verification
	public static boolean logoVerification(){
		return logo.isDisplayed();
		
	}
	
	//To Click on Flight Icon
	public static void clickOnFlightIcon() throws InterruptedException{
		ApplicationUtill.clickOnElement(flightIcon);
	}
	
	//To Click on Round Trip Radio Button
	public static void clickOnRoundTripRadioBtn() throws InterruptedException{
		ApplicationUtill.clickOnElement(roundTripRadioBtn);
	}
	
	//To select Source Station as Delhi
	public static void enterSourceStation(){
		ApplicationUtill.setStringinEditBox(fromCity, stationFrom, "Delhi");
	}
	
	//To select Destination station as Bangalore
	public static void enterDestinationStation(){
		ApplicationUtill.setStringinEditBox(toCity, stationTo, "Bangalore");
	}
	
	//To Click on Search Button
	public static void clickOnSearchBtn(){
		ApplicationUtill.clickByJavaScript(searchBtn);
	}
	
	//To select Today date as Source and Destination date after 7 days
	public static FlightListPage searchFlights(){
		//Get the Today date and Destination Date then split the date to get days
		
		String today = Utility.getTodayDate();
		String destDate = Utility.getDestDate(today, 7);
		String[] sourceSplit = Utility.splitString(today, "/");
		String[] destSplit = Utility.splitString(destDate, "/");
		ApplicationUtill.clickOnElement(depatureCal);
		selectDate(sourceSplit[0]); //To select the Source Date
		ApplicationUtill.clickOnElement(retrunCal);
		
		//This part is to navigate if difference between Source and Destination is more than a month.
		int diffMonth = Integer.parseInt(destSplit[1])-Integer.parseInt(sourceSplit[1]);
		if (diffMonth>0){
			for (int diff=1;diff<=diffMonth;diff++){
				ApplicationUtill.clickOnElement(navigationNext);
			}
		}
		selectDate(destSplit[0]); //To select the Destination Date
		ApplicationUtill.clickOnElement(searchBtn);
		return flightSearchPage;
	}
	
	//To select Departure Date and Return Date. This will take some time (10sec) to select Departure Date, since excluded disabled date.
	public static void selectDate(String strDay){
		List<WebElement> allWeeks = driver.findElements(By.xpath(calWeek));
		int allWeekSize = allWeeks.size();
		boolean doneFlag = false;
		for (int j=1;j<=allWeekSize;j++){
			List<WebElement> allDays = driver.findElements(By.xpath(calWeek +"["+ j + "]" + daySecondPart));
			int allDaysSize = allDays.size();
			for (int k=1;k<=allDaysSize;k++){
				WebElement day = driver.findElement(By.xpath(calWeek + "[" + j + "]" + daySecondPart +"[" + k + "]"+dayThirdPart));
				String textInDay = day.getText();
				int intDay = Integer.parseInt(strDay);
				int intTextInDay = Integer.parseInt(textInDay);
				if (intDay == intTextInDay){									//If compare as String, it wont work for dates less than 10 whihc contains '0' as 1st digit.
					ApplicationUtill.clickByJavaScript(day);
					doneFlag=true;
					break;
				}
			}
			if (doneFlag){
				break;
			}
		}
	}
}
