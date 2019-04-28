package UtilityPackage;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import BasePackage.DriverClass;

/*
 * Created by - SIVAKUMAR.V
 * 
 * This Class (ApplicationUtill) is created to maintains methods which will be involved in Application handling
 */

public class ApplicationUtill extends DriverClass {
	
	public static Wait<WebDriver> wait = new WebDriverWait(driver, 10);
	public static Select select;
	public static Actions action = new Actions(driver);
	public static JavascriptExecutor jExe = (JavascriptExecutor)driver;
		
	
	
	//To click on Element. It will check if the Element is available or not in current page. 
	//If not available, then it will check the Element availability in all the Frames.
	public static void clickOnElement(WebElement element) {
		try{
			
		if (element.isDisplayed()){
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}
		else{
			List <WebElement> allFrames = driver.findElements(By.tagName("iframe"));
			for (int i=0; i<allFrames.size();i++){
				driver.switchTo().frame(i);
				if (element.isDisplayed()){
					wait.until(ExpectedConditions.elementToBeClickable(element));
					element.click();
					break;
				}
			}
		}
		} catch (NullPointerException e1){
			Log.fatal(e1);
			e1.printStackTrace();
		}
	}
	
	//To click on Element using Java Script
	public static void clickByJavaScript(WebElement element){
		jExe.executeScript("arguments[0].click();", element);
		Log.debug("Element clicked by Java Script");		
	}
	
	//To set a string in an Edit Box. Its used set value in Source and Destination Flight
	public static void setStringinEditBox(WebElement editBox, WebElement editBox1,String content){
		editBox.isDisplayed();
		editBox.click();
		editBox1.clear();
		editBox1.sendKeys(content);
	}
	
	//To scroll end of the Page, if the page is loading dynamically while scrolling
	public static void scrollToEnd(){
		
		for (int i=0;i<15;i++){
			try {
				action.sendKeys(Keys.PAGE_DOWN).build().perform();
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Log.fatal(e);
			}
		}
	}
	
	//To move the focus to top of the page.
	public static void clickCtrlHome(){
		action.keyDown(Keys.CONTROL)
        .sendKeys(Keys.HOME)
        .keyUp(Keys.CONTROL)
        .perform();
        
	}
	
	//To move the Focus on particular element
	public static void moveToElement(WebElement targetElement){
		action.moveToElement(targetElement).build().perform();
	}
	
	

}
