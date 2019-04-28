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

public class Utility extends DriverClass{
	static File F1= null;
	static FileInputStream FIS;
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public static Calendar cal = Calendar.getInstance();
	
	public static String getTodayDate(){
		String todayDate = dateFormat.format(cal.getTime());
		System.out.println("Departure Date :"+todayDate);
		Log.info("Departure Date :"+todayDate);
		return todayDate;
	}
	
	public static String getDestDate(String date, int incrementDays){
		String destDate="";
		//try {
			//String sourceDate = dateFormat.format(dateFormat.parse(date));
			cal.add(cal.DAY_OF_MONTH, incrementDays);
			destDate = dateFormat.format(cal.getTime());
			
		//} catch (ParseException e) {
			//Log.fatal(e);
			//e.printStackTrace();
		//}
		System.out.println("Return Date :"+destDate);
		Log.info("Return Date :"+destDate);
		return destDate;
		
	}
	
	public static String[] splitString(String content, String splitter){
		
		String[] splitt = content.split(splitter);
		return splitt;
	}
	
	public static int convertStrToInt(String str){
		int returnValue = Integer.parseInt(str);
		return returnValue;
	}
	
	public static int getRandomNumbers(int max){
		Random random = new Random();
		return random.nextInt(max)+1;
	}
	
	public static String stringReplacement(String str, String regExp, String replacement){
		return str.replaceAll(regExp, replacement);
	}
	
	
	public String getClassName(){
		String callerClassName = new Exception().getStackTrace()[1].getClassName();
		String calleeClassName = new Exception().getStackTrace()[0].getClassName();
		String className = this.getClass().getName();
		
		return callerClassName;
	}
	
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
	
	public static long getCurrentTime(){
		Date date = new Date();
		
		Calendar calendar = Calendar.getInstance();
		return date.getTime();
		
	}
	
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
