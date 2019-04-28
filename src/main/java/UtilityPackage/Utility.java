package UtilityPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
//import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import BasePackage.DriverClass;

/*
 * Created by - SIVAKUMAR.V
 * This class is contains general Java related methods like Dates, SPlit String, Conversion, etc...
 */
public class Utility extends DriverClass{
	static File F1= null;
	static FileInputStream FIS;
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public static Calendar cal = Calendar.getInstance();
	
	//To get Today date
	public static String getTodayDate(){
		String todayDate = dateFormat.format(cal.getTime());
		System.out.println("Departure Date :"+todayDate);
		Log.info("Departure Date :"+todayDate);
		return todayDate;
	}
	
	//To get a date which will add passed days with a date.
	public static String getDestDate(String date, int incrementDays){
		String destDate="";
		cal.add(cal.DAY_OF_MONTH, incrementDays);
		destDate = dateFormat.format(cal.getTime());
		System.out.println("Return Date :"+destDate);
		Log.info("Return Date :"+destDate);
		return destDate;
	}
	
	//To split a string and get a String array
	public static String[] splitString(String content, String splitter){
		
		String[] splitt = content.split(splitter);
		return splitt;
	}
	
	//To Convert String to Integer
	public static int convertStrToInt(String str){
		int returnValue = Integer.parseInt(str);
		return returnValue;
	}
	
	//Get random number. Random number will return from 0. To exclude the '0', added '1' with return value.
	public static int getRandomNumbers(int max){
		Random random = new Random();
		return random.nextInt(max)+1;
	}
	
	//String Replacement.
	public static String stringReplacement(String str, String regExp, String replacement){
		return str.replaceAll(regExp, replacement);
	}
	
	//Get Executing Class name.
	public String getClassName(){
		String callerClassName = new Exception().getStackTrace()[1].getClassName();
		String calleeClassName = new Exception().getStackTrace()[0].getClassName();
		String className = this.getClass().getName();
		
		return callerClassName;
	}
	
	//Get executing Method name.
	public static String getmethodName(){
		
		String callerMethodName = new Exception().getStackTrace()[1].getMethodName();
		String calleeMethodName = new Exception().getStackTrace()[0].getMethodName();
		String methodName = new Object() {}
	      .getClass()
	      .getEnclosingMethod()
	      .getName();
	      System.out.println(calleeMethodName+" "+calleeMethodName+" "+methodName);
		return callerMethodName;
	}
	
	//Get current Time
	public static long getCurrentTime(){
		Date date = new Date();
		
		Calendar calendar = Calendar.getInstance();
		return date.getTime();
		
	}
	
	//To take screen shot. It will use while a test case getting failed.
	public void takeScreenShot(){
		try {
			
		String screenShotName = ".//ScreenShots//"+getmethodName()+getCurrentTime()+".jpg";
		File source = new File(screenShotName);
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screen, source);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
