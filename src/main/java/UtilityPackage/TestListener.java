package UtilityPackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import BasePackage.DriverClass;

/*
 * Created by - SIVAKUMAR.V
 * This class is created to Take Screen shot when a test case getting failed and Save that screen shot in ScreenShots folder.
 */
public class TestListener extends DriverClass implements ITestListener {
	
	Utility utill = new Utility();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onTestFailure(ITestResult result) {
		Log.error ("Test getting Failed. Refere screen shot and log for details");
		utill.takeScreenShot();
	}

	
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
